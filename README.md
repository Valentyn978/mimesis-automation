# mimesis-automation
Frameworks facilitating the hard life of an Q&amp;A engineers

<H3>Best practices for used:</H3>

<p>This framework is best used for creating/writing automated tests to cover different services which use <b>REST API</b>.</p>
<p>Framework generates the <b>report</b> in HTML format suitable for using in CI (Continuous Integration system).</p>
<p>It can easily be integrated into different <b>CI</b> systems.</p>
<p>It is also used for further extension to support interaction with <b>Asynchronous</b> data transmission systems, <b>Databases</b> and Web <b>User Interfaces</b>.</p>

<H3>Release 0.0.4</H3>
<ol type=1>Added:
<li>Add integration with **SreenPlay** pattern</li>
<li>Add integration with **Cucumber** and **Serenity**</li>
<li>Add integration with Feature files</li>
</ol>

<H3>What's planned:</H3>
<ol type=1>Create ability for:
<li>Reporting for performing testing</li>
</ol>

### Preparation for start WD TESTS
* Rename base.properties.example to base.properties in the root directory and put valid data (for example 'API_ID' should contains valid ID from https://openweathermap.org site)
* Run build Maven project (mv clean install)
* For run new scope 'ScreenPlay' use 'src/test/resources/features/FrontAndBackAnd.feature'
* Run modify is needed Suit xml file in the ${basedir}/target/run/WDSuiteTest.xml (in this case you have ability to modify type of Browser and Http client

* Run project using the next command from the ${basedir}/target/run folder
'java -jar mimesis-0.0.4-mimesis-jar-with-dependencies.jar WDSuiteTest.xml'

* Report will be generated in folder Report, for open use report/html_report/index.html

* Tests classes here mimesis-automation/src/main/java/com/tests/wd/Test**.java

#### What's planned:
* Add sonarqube
* Add User stories
* Add integration with cucumber
