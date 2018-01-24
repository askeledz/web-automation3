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


### Remote configuration for Chrome and Firefox
- You don't have to change anything in project, simply:

- Hub

java -jar selenium-server-standalone-3.8.1.jar -role hub -hubConfig DefaultHub.json

- Then register the nodes:

- Nodes:

java -Dwebdriver.chrome.driver=chromedriver -Dwebdriver.firefox.driver=geckodriver -jar selenium-server-standalone-3.8.1.jar -role node -nodeConfig DefaultNodeWebDriver.json

- NOTE: Make sure you execute those commands under dir where chromedriver and geckodriver are located and also .json files should be there.


### Remote configuration for Microsoft Edge (How to set up Selenium Grid to test Microsoft Edge from MacOS)
- Launch your Windows VM. Make sure the Windows VM and your Mac can ping each other over the network.
- Install Java on both your host computer and the Windows VM.
- Download the Selenium Grid binary jar file (selenium-server-standalone-3.8.1)to both your Mac and your Windows VM. 
- Download the Internet Explorer or Edge Driver to your Windows VM only. It is in the same folder as the Selenium Grid binary.
- Place the .exe and .jar files that comes out in a directory in the Windows PATH. One such directory is C:\Windows\.
- Now we need one selenium grid hub, and one node. The Mac will be the hub, and Windows will be the node. 
  On the Mac go to the directory with the selenium-server-standalone file you downloaded earlier (elenium-server-standalone-3.8.1).
  Then run (replacing the selenium-server stuff with the actual filename):

- Hub on Mac:

java -jar selenium-server-standalone-3.8.1.jar -role hub -hubConfig DefaultHub.json

- You will see some console output about “Launching a selenium grid server”. Now go to the url http://localhost:4444/grid/console. You should see that your hub is up and running, but not offering any browsers yet.

- Before you begin this step, make a note of your Mac’s IP address. Let’s say it is 192.168.1.5. 
  Now go to your Windows VM and open a command prompt. Navigate to the selenium-server-standalone-????.jar file directory, and execute the following command, replacing the ???? with the proper version, and  with your actual Mac’s IP address (192.168.1.5).

- Node on PC (Windows):

java -Dwebdriver.edge.driver=C:\Windows\MicrosoftWebDriver.exe -jar selenium-server-standalone-3.8.1.jar -role node -hub http://192.168.1.5:4444/grid/register -browser "browserName=MicrosoftEdge,platform=WINDOWS,maxInstances=5"

- You should see some terminal output about “Launching a selenium grid node”. Now go back to your grid console webpage and refresh it. The one at http://localhost:4444/grid/console. You should see some web browsers on offer! You now have a selenium grid running.

- Now it is time to run a test over the grid. Go to your project and edit TestNG-Remote.xml:
  <parameter name="browserName" value="edge" />


## How to run REMOTE tests from InteliJ IDEA
- Simply right click on the "TestNG-Remote.xml" for remote config usage and "Run As....".
Please be sure that your HUB and NODES are up&running.
It os the same for Local, just need to run "TestNG-Local.xml"

## How to run LOCAL tests from IDE
- Simply right click on the "TestNG-Local.xml" and chose "Run".

# Tests run from command line
- $mvn -DtestSuite=testnglocal.xml test
or
- $mvn -DtestSuite=testngremote.xml test

# Reports
- /target/surefire-reports/index.html

## Known issues

