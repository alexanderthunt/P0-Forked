## Complete Function of Classes:
  - src\main\java\com\revature\repository\UserDao.java
  - src\main\java\com\revature\repository\PlanetDao.java
  - src\main\java\com\revature\repository\MoonDao.java
  - src\main\java\com\revature\service\UserService.java
  - src\main\java\com\revature\service\PlanetService.java
  - src\main\java\com\revature\service\MoonService.java

## Site Reliability Engineering (SRE) Requirements
  - Implement SLF4j and Logback to capture relevant events and their associated data in real time. 
  - Create a script OUTSIDE of the project to aggregate the data to measure your SLIs and achieve MVP requirements. 

### Service Level Objects
- 99.8% of requests should complete successfully within 200 milliseconds

### Service Level Indicators
- Latency
    - you should track how long it takes for the Planetarium app to handle requests made to the system
- Error Rate
    - you should track the percentage of how many http requests return a non-500 status code

**NOTE: you are free to edit the pre-provided methods to add your logging, but be careful of making sweeping changes to the code: edit, don't recreate**

### Service Level Indicator Exposure
You will need to create one or more bash scripts that read the log file of the project and parse the relevant data

# MVP Requirements Rundown
- Development
    - All unimplemented methods should be completed
- SRE
    - Logging should be added to the project
    - One or more scripts should handle measuring your SLIs by reading/parsing the log file

# Stretch Goal
Stretch goals are things to work on ONLY when all MVP requirements have been accomplished: listed below is an optional feature you can add to the project to enhance it further:
- Create a way for the application to return the SLI metrics for the lifespan of the application via an http request
    - This will require you to create your own custom classes and integrate them into the project