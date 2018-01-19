package com.autotest.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * Author: askeledzija It's a generic WebDriver manager, it works with local and remote instances of WebDriver
 */
public class LocalDriverFactoryWEB {

    static Logger log = Logger.getLogger(LocalDriverFactoryWEB.class);

    static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.gecko.driver", "/Users/askeledzija/Documents/Private/Development/workspace/webui/geckodriver");
            System.setProperty("webdriver.firefox.logfile","/Users/askeledzija/Documents/Private/Development/workspace/webui/log.txt" );

//            FirefoxOptions options = new FirefoxOptions();
//            options.addArguments("disable-infobars");
//            options.addArguments("--start-maximized");
//            options.addArguments("--kiosk");
//
//            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);

            driver = new FirefoxDriver();
            log.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
            return driver;
        }
        if (browserName.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver","/Users/askeledzija/Documents/Private/Development/workspace/webui/chromedriver");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments("--start-maximized");
            options.addArguments("--kiosk");

            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            driver = new ChromeDriver(options);
            log.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
            return driver;

        }
        if (browserName.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
            log.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
            return driver;
        }
        if (browserName.toLowerCase().contains("safari")) {
            //System.setProperty("webdriver.safari.driver", "/Users/askeledzija/Documents/Private/Development/workspace/autotest-deinteam-ui3/SafariDriver.safariextz");


            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setUseCleanSession(true);
            capabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);

            driver = new SafariDriver(safariOptions);
            log.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
            return driver;
        }

        log.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
        return driver;
    }
}