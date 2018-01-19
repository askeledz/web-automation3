package com.autotest.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Author: askeledzija It's a generic WebDriver manager, it works with local and remote instances of WebDriver
 */
public class LocalDriverFactory {

    static Logger log = Logger.getLogger(LocalDriverFactory.class);

    static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.gecko.driver", "geckodriver");
            System.setProperty("webdriver.firefox.logfile","Firefox.log" );

            driver = new FirefoxDriver();
            log.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
            return driver;
        }
        if (browserName.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver","chromedriver");
            System.setProperty("webdriver.chrome.logfile","Chrome.log" );

            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            //options.addArguments("--start-maximized");
            //options.addArguments("--kiosk");

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
            System.setProperty("webdriver.safari.logfile","Safari.log" );
            driver = new SafariDriver();
            log.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
            return driver;
        }

        log.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
        return driver;
    }
}