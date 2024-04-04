# R3

# Architecture Base
    - RestAssured, TestNG & Maven
# Reporting
    - Implemented Extent Reporting for result visualization and Run Summary
# Logging
    - Utilized log4j 2 for logging utility
# Components
    - Tests
        - ExchangeTest (Test Class containing the required test cases)
    - Utilities
        - Extent Report Manager (Class definition for extent reporting utility)
        - BaseTest (Test Class containing common initilaization to be used by all tests)
        - log4j2.xml (Configuration for logging framework)
        - currConversionSchema.json (Json Schema for the API under test)
    - EndPoints
        - Routes (URIs for API Endpoints)
        - Endpoints (Endpoint Implementation to execute API and return response if successful)
    - Logs
        - Logger implemented using log4j2 utility 
    - Reports
        - Implemented Extent report to genefrate a standalone report summary
        - Implemented Allure reporting as well, which can be viewed by runnig command "allure serve allure-results" 
    - pom.xml
        - To take care of all dependencies required for project
    - testng.xml
        - To define run configuration and configure entent reporting as well

# How to run the tests & verify results
    - Open the project in any Java IDE, right click on the testng.xml file and select run from the drop down
    - Test Execution progress can be monitored in the console
    - Logs can be verified from console as well as through log file in 'logs' folder.
    - Extent report can be veiwed under Reports folder (Open with any browser available)
