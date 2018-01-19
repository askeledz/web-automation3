# Selenium Grid (PageObjectModel)

- An example project that runs tests using TestNG.
- To drive the browsers I use Selenium WebDriver.
- Tests can be executed locally or remotely.
- Project is created on MacBook Pro (macOS, High Sierrs).

# Files to download:
*****************************
1. firefox 57.x (https://www.mozilla.org/en-US/firefox/new/)
2. geckodriver 0.19.x (https://github.com/mozilla/geckodriver/releases)
3. chromedriver 2.35 (https://sites.google.com/a/chromium.org/chromedriver/downloads)
3. selenium-server-standalone-3.8.1.jar (http://www.seleniumhq.org/download/)

- put those files in the project root folder (except firefox which should be installed by default). 

# IMPORTING PROJECT TO IntelliJ IDEA
1. Import Project --> Select project root dir --> Import project from external model - Maven (leave everything by default)
2. This is Maven project and you should import dependencies.

## Configuration
Before you run your tests locally or remotely, you need to:

* Decide in what browsers you want to run them (I use Chrome, Firefox and Safari) 
* Configure TestNG XML suites accordingly (they are in root dir).
* If you want use Chrome, type "chrome", for Firefox, type "firefox" for parameter browserName, etc...
* To run tests on Internet Explorer use Virtual Machine if you use MacOS.
* In order to Use Safari, driver needs to be enabled --> Execute following command:
  $/usr/bin/safaridriver --enable

## maven-surefire-plugin (Using TestNG)
- http://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html
* Edit maven-surefire-plugin in POM.xml (<suiteXmlFile>TestNG-Local.xml</suiteXmlFile>) or (<suiteXmlFile>TestNG-Remote.xml</suiteXmlFile>).


### Remote configuration
- You don't have to change anything in project, simply:

- Hub

    java -jar selenium-server-standalone-3.8.1.jar -role hub -hubConfig DefaultHub.json

- Then register the nodes:

- Nodes:

    java -Dwebdriver.chrome.driver=chromedriver -jar selenium-server-standalone-3.8.1.jar -role node -nodeConfig DefaultNodeWebDriver.json

- NOTE: Make sure you execute those commands under dir where chromedriver and geckodriver are located.


## How to run REMOTE tests from InteliJ IDEA
- Simply right click on the "TestNG-Remote.xml" for remote config usage and "Run As....".
Please be sure that your HUB and NODES are up&running.
It os the same for Local, just need to run "TestNG-Local.xml"

## How to run LOCAL tests from IDE
- Simply right click on the "TestNG-Local.xml" and chose "Run".

# Tests run from command line
- $mvn clean test -am -DtestSuite=TestNG-Local.xml 
or
- $mvn clean test -am -DtestSuite=TestNG-Remote.xml 

# Reports
- /target/surefire-reports/index.html

## Known issues

