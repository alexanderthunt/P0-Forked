httpCodes=$(grep Response: ./logs/staticLogs.log | cut -f 1 -d , | cut -f 2 -d : | cut -f 2 -d [ | cut -f 1 -d O)

httpRequestTotal=0
httpFailures=0

for code in $httpCodes
do
	((httpRequestTotal++))
	if [ $code -gt 499 ]
	then
		((httpFailures++))
	fi
done

httpSuccess=$(($httpRequestTotal - $httpFailures))

result=$(awk "BEGIN {print $httpSuccess / $httpRequestTotal * 100; exit}")

echo "HTTP Status success rate: $result%"


httpResponseTime=$(grep Response: ./logs/staticLogs.log | cut -f 2 -d , | cut -f 2 -d k | cut -f 1 -d .)

httpResponseTotal=0
httpResponseOverTime=0

for responseTime in $httpResponseTime
do
	((httpResponseTotal++))
	if [ $responseTime -gt 200 ]
	then
		((httpResponseOverTime++))
	fi
done

httpResponseSuccess=$(($httpResponseTotal - $httpResponseOverTime))

responseResult=$(awk "BEGIN {print $httpResponseSuccess / $httpResponseTotal * 100; exit}")

echo "HTTP Response Time success rate: $responseResult%"

