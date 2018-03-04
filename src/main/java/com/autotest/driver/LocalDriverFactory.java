package com.autotest.driver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;


/**
 * Author: askeledzija It's a generic WebDriver manager, it works with local and remote instances of WebDriver
 */
public class LocalDriverFactory {

    private static final Logger logger = LogManager.getLogger(LocalWebDriverListener.class);
    //For Jenkins (Linux)
    //public static String chromeDriverPath = "/usr/bin/chromedriver";
    //public static String geckoDriverPath = "/usr/bin/geckodriver";


    static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.gecko.driver", "geckodriver");
            System.setProperty("webdriver.firefox.logfile", "Firefox.log");


            FirefoxOptions options = new FirefoxOptions();

            //Set Firefox Headless mode as TRUE
            //options.setHeadless(true);

            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            //logger.info("Browser name: " + browserName);
            return driver;


        }
        if (browserName.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver", "chromedriver");
            System.setProperty("webdriver.chrome.logfile", "Chrome.log");

            ChromeOptions options = new ChromeOptions();

            //Set Chrome Headless mode as TRUE
            //options.setHeadless(true);
            options.addArguments("--window-size=1920,1080");
            options.addArguments("disable-infobars");
            //options.addArguments("--start-maximized");
            //options.addArguments("--kiosk");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            //logger.info("Browser name: " + browserName);
            return driver;


        }
        if (browserName.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            // logger.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
            return driver;
        }
        if (browserName.toLowerCase().contains("safari")) {
            System.setProperty("webdriver.safari.logfile", "Safari.log");
            driver = new SafariDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // logger.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
            return driver;
        }

        //logger.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
        return driver;
    }
}