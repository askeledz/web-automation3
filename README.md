# Selenium Grid (POM - PageObjectModel)

- An example project that runs tests using TestNG.
- To drive the browsers I use Selenium WebDriver.
- Tests can be executed locally or remotely.
- Project is created on MacBook Pro (macOS, High Sierrs).

## Files to download:
*****************************
1. geckodriver 0.19.x (https://github.com/mozilla/geckodriver/releases/tag/v0.19.1)
2. chromedriver 2.35 (https://chromedriver.storage.googleapis.com/index.html?path=2.35/)


- put files (1-2) in the project root folder
- make sure you download files for appropriate platform
- make sure that appropriate browsers are installed too

## Importing Project (IntelliJ IDEA)
1. Import Project --> Select project root dir --> Import project from external model - Maven (leave everything by default)
2. Set project name (You can choose the same as artifactid in pom.xml file)
2. This is Maven project and you should import dependencies.

## Configuration
Before you run your tests locally or remotely, you need to:

* Decide in what browsers you want to run them (I use Chrome, Firefox and Safari) 
* Configure TestNG XML suites accordingly (they are in root dir).
* If you want use Chrome, type "chrome", for Firefox, type "firefox" for parameter browserName, etc...
* To run tests on Internet Explorer use Virtual Machine if you use MacOS.
* In order to Use Safari, driver needs to be enabled --> Execute following command:
  
  - $/usr/bin/safaridriver --enable

## maven-surefire-plugin (Using TestNG)
- http://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html
* Edit maven-surefire-plugin in POM.xml (<suiteXmlFile>testnglocal.xml</suiteXmlFile>) or (<suiteXmlFile>testngremote.xml</suiteXmlFile>).


## Remote configuration for Chrome and Firefox

1. selenium-server-standalone-3.8.1.jar (http://selenium-release.storage.googleapis.com/index.html?path=3.8/)

- put file in the project root folder
- You don't have to change anything in project, simply:

- Hub

  - $java -jar selenium-server-standalone-3.8.1.jar -role hub -hubConfig DefaultHub.json

- Then register the nodes:

- Nodes:

  - $java -Dwebdriver.chrome.driver=chromedriver -Dwebdriver.firefox.driver=geckodriver -jar selenium-server-standalone-3.8.1.jar -role node -nodeConfig DefaultNodeWebDriver.json

- NOTE: Make sure you execute those commands under dir where chromedriver and geckodriver are located and also .json files should be there.


## Remote configuration for Microsoft Edge (How to set up Selenium Grid to test Microsoft Edge from MacOS)
- Launch your Windows VM. Make sure the Windows VM and your Mac can ping each other over the network.
- Install Java on both your host computer and the Windows VM.
- Download the Selenium Grid binary jar file (selenium-server-standalone-3.8.1)to both your Mac and your Windows VM. 
- Download the Internet Explorer or Edge Driver to your Windows VM only. It is in the same folder as the Selenium Grid binary.
- Place the .exe and .jar files that comes out in a directory in the Windows PATH. One such directory is C:\Windows\.
- Now we need one selenium grid hub, and one node. The Mac will be the hub, and Windows will be the node. 
  On the Mac go to the directory with the selenium-server-standalone file you downloaded earlier (elenium-server-standalone-3.8.1).
  Then run (replacing the selenium-server stuff with the actual filename):

- Hub on Mac:

  - $java -jar selenium-server-standalone-3.8.1.jar -role hub -hubConfig DefaultHub.json

- You will see some console output about “Launching a selenium grid server”. Now go to the url http://localhost:4444/grid/console. You should see that your hub is up and running, but not offering any browsers yet.

- Before you begin this step, make a note of your Mac’s IP address. Let’s say it is 192.168.1.5. 
  Now go to your Windows VM and open a command prompt. Navigate to the selenium-server-standalone-????.jar file directory, and execute the following command, replacing the ???? with the proper version, and  with your actual Mac’s IP address (192.168.1.5).

- Node on PC (Windows):

  - $java -Dwebdriver.edge.driver=C:\Windows\MicrosoftWebDriver.exe -jar selenium-server-standalone-3.8.1.jar -role node -hub http://192.168.1.5:4444/grid/register -browser "browserName=MicrosoftEdge,platform=WINDOWS,maxInstances=5"

- You should see some terminal output about “Launching a selenium grid node”. Now go back to your grid console webpage and refresh it. The one at http://localhost:4444/grid/console. You should see some web browsers on offer! You now have a selenium grid running.

- Now it is time to run a test over the grid. Go to your project and edit TestNG-Remote.xml:
  <parameter name="browserName" value="edge" />

## Running the Example in Safari
To run this WebDriver test using Safari, first you need to grab a recent release of the Selenium open source project. Selenium’s Java and Python client libraries offer support for Safari’s native driver implementation starting in the 3.0.0-beta1 release. Note that the Apple-developed driver is unrelated to the legacy SafariDriver mentioned in the Selenium project. The old SafariDriver implementation is no longer maintained and should not be used. You do not need to download anything besides Safari 10 to get the Apple-developed driver.

Once you have obtained, installed, and configured the test to use the correct Selenium library version, you need to configure Safari to allow automation. As a feature intended for developers, Safari’s WebDriver support is turned off by default. To turn on WebDriver support, do the following:

Ensure that the Develop menu is available. It can be turned on by opening Safari preferences 
 - Safari > Preferences in the menu bar), going to the Advanced tab, and ensuring that the Show Develop menu in menu bar checkbox is checked.
Enable Remote Automation in the Develop menu. 
- This is toggled via Develop > Allow Remote Automation in the menu bar.
Authorize safaridriver to launch the webdriverd service which hosts the local web server. To permit this, run 

- $/usr/bin/safaridriver once manually and complete the authentication prompt.

If you don't have Extension insatlled you can download it here: (SafariDriver.safariextz)
http://selenium-release.storage.googleapis.com/index.html?path=2.48/


## How to run REMOTE tests from InteliJ IDEA
- Simply right click on the "TestNG-Remote.xml" for remote config usage and "Run As....".
Please be sure that your HUB and NODES are up&running.
It os the same for Local, just need to run "TestNG-Local.xml"

## How to run LOCAL tests from IDE
- Simply right click on the "TestNG-Local.xml" and chose "Run".

## Tests run from command line
- $mvn clean test -am -DtestSuite=testnglocal.xml 
- $mvn clean test -am -DtestSuite=testngremote.xml 

## Reports
- /target/surefire-reports/index.html

## Jenkins Setup
- Install Jenkins:
  - $brew install jenkins
- Run Jenkins
  - $jenkins

NOTE: For very first start Jenkins needs to be activated. Copy password from console (should be on the screen) and activate Jenkins on Browser (localhost:8080) and continue installation from browser.

- Before start we need to setup Maven:
  - Manage Jenkins --> Global Tool Configuration and setup Maven as following:
    - Name: mavenname
    - Install Automatically = true
    - Install from Apache (Version 3.5.2)
    
- New job (item):
    - Name: projectname
    - Freestyle project
    - Config: 
      - Source Code Management: 
        - Git (e.g. projecturl.git ) and credentials
      - Build: 
        - Maven version: (e.g. mavenname)
        - Goals: clean test -am -DtestSuite=testnglocal.xml
        - Advanced: POM: pom.xml

## Set up Test Results Analyzer Plugin for Jenkins
- Manage Jenkins --> Manage Plugins (e.g search for "Test Results Analyzer Plugin")
- Install plugin and restart Jenkins.
- At Jenkins dashboard, select <your project> / Configure
- Under Post-build Actions tab, select Add post-build action and choose Publish JUnit test result report
- At Test report XMLs, enter the path to your .xml report file, the analyzer will find data here to create charts, in my    case, it's target/surefire-reports/*.xml, you can edit the path to handle more tests.
