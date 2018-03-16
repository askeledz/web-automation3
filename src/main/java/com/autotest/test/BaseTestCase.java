package com.autotest.test;

import com.autotest.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Andrej Skeledzija 2017
 */
public class BaseTestCase {

    private static final Logger logger = LogManager.getLogger(BaseTestCase.class);

    protected WebDriver invokeBrowser(String url) {
        WebDriver driver = DriverManager.getDriver();
        logger.info("START: " + DriverManager.getBrowserInfo());
        logger.info("Thread id = " + Thread.currentThread().getId());
        logger.info("Driver instance= " + driver.hashCode());
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
