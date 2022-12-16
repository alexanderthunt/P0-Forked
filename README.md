## Complete Function of Classes:
  - src\main\java\com\revature\repository\UserDao.java <!--COMPELTE-->
  - src\main\java\com\revature\repository\PlanetDao.java <!--COMPELTE-->
  - src\main\java\com\revature\repository\MoonDao.java <!--COMPELTE-->
  - src\main\java\com\revature\service\UserService.java <!--COMPELTE-->
  - src\main\java\com\revature\service\PlanetService.java <!--COMPELTE-->
  - src\main\java\com\revature\service\MoonService.java <!--COMPELTE-->

## Site Reliability Engineering (SRE) Requirements
  - Implement SLF4j and Logback to capture relevant events and their associated data in real time.<!--COMPELTE-->
  - Create a script OUTSIDE of the project to aggregate the data to measure your SLIs and achieve MVP requirements. <!--Need to figure out how to print approriate data to log-->

### Service Level Objects (SLO)
- 99.8% of requests should complete successfully within 200 milliseconds<!--COMPLETE-->

### Service Level Indicators (SLI)
- Latency: you should track how long it takes for the Planetarium app to handle requests made to the system<!--nanotime-->
- Error Rate: Find the percentage of errors that are 500 error codes<!--Need to create log parse script-->

### Service Level Indicator Exposure (SLIE)
Create bash scripts that read the log file and parse relevant data<!--NOT COMPLETE-->

# Minimum Viable Product (MVP)
- Development: All unimplemented methods should be completed<!--COMPELTE-->
- SRE: Logging should be added to the project, scripts should parse relevant data<!--NOT COMPLETE-->


<!--
# Stretch Goal
Stretch goals are things to work on ONLY when all MVP requirements have been accomplished: listed below is an optional feature you can add to the project to enhance it further:
- Create a way for the application to return the SLI metrics for the lifespan of the application via an http request
    - This will require you to create your own custom classes and integrate them into the project
-->