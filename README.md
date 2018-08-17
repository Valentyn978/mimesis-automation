## Preparation for start
* Rename base.properties.example to base.properties in the root directory and put valid data (for example 'API_ID' should contains valid ID from https://openweathermap.org site)
* Run build Maven project (mv clean install)
* Run modify is needed Suit xml file in the ${basedir}/target/run/WDSuiteTest.xml (in this case you have ability to modify type of Browser and Http client

* Run project using the next command from the ${basedir}/target/run folder
'java -jar mimesis-0.0.3-mimesis-jar-with-dependencies.jar WDSuiteTest.xml'

* Report will be generated in folder Report, for open use report/html_report/index.html

* Tests classes here mimesis-automation/src/main/java/com/tests/wd/Test**.java

### What's planned:
* Add sonarqube
* Add User stories
* Add integration with cucumber
