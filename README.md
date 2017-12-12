# mimesis-automation
Frameworks facilitating the hard life of an Q&amp;A engineers

<H3>Best practices for used:</H3>

<p>This framework is best used for creating/writing automated tests to cover different services which use <b>REST API</b>.</p>
<p>Framework generates the <b>report</b> in HTML format suitable for using in CI (Continuous Integration system).</p>
<p>It can easily be integrated into different <b>CI</b> systems.</p>
<p>It is also used for further extension to support interaction with <b>Asynchronous</b> data transmission systems, <b>Databases</b> and Web <b>User Interfaces</b>.</p>


<H3>Intellij IDEA cucumber set up<H3>
- Install Cucumber for java
- In set Run/Debug config:
- set main class - "cucumber.RunMe"
- set "glue" as "UserStepDefinitions"
- "feature or folder path" - "${user.dir}/src/test/resources"
