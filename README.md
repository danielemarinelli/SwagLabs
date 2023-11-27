# SwagLabs


-- -- Tools Used for testing -- --

TestNG Framework

-- -- How to execute -- --

This is a maven project. It can be tested in multibrowsing and it can be run in the following environments:

DEV INT PROD PREPROD

The test can be triggered with the following command:

mvn clean test -Denv=[ENVIRONMENT] -Dbrowser=[BROWSER NAME]
(f.e.: mvn clean test -Denv=int -Dbrowser=edge)

A report TestReport.html will be generated under folder ./src/main/test-output
Screenshots are collected, only if test fails, under folder ./screenshots
