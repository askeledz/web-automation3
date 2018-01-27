package com.autotest.driver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

import java.lang.reflect.Field;

/**
 * Author: askeledzija 
 * It's a generic WebDriver manager, it works with local and remote instances of WebDriver
 */

public class LocalWebDriverListener implements IInvokedMethodListener {

    static final Logger logger = LogManager.getLogger(LocalWebDriverListener.class.getName());

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.debug("BEGINNING: askeledz.driver.LocalWebDriverListener.beforeInvocation");
        if (method.isTestMethod()) {
            // get browser name specified in the TestNG XML test suite file
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");

            // String browserName = method.getClass().getAnnotations()
            // String browserName = "firefox";

            // get and set new instance of local WebDriver
            WebDriver driver = LocalDriverFactory.createInstance(browserName);
            DriverManager.setWebDriver(driver);
        } else {
            logger.warn("Provided method is NOT a TestNG testMethod!!!" + " --> " + method.toString());

        }
        logger.debug("END: askeledz.driver.LocalWebDriverListener.beforeInvocation");
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.debug("BEGINNING: askeledz.driver.LocalWebDriverListener.afterInvocation");
        if (method.isTestMethod()) {
            String browser = DriverManager.getBrowserInfo();
            try {
                // change the name of the test method that will appear in the report to one that will contain
                // also browser name, version and OS.
                // very handy when analysing results.
                BaseTestMethod bm = (BaseTestMethod) testResult.getMethod();
                Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
                f.setAccessible(true);
                String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - " + bm.getMethodName() + " - " + browser;
                logger.info("Renaming test method name from: '" + bm.getMethodName() + "' to: '" + newTestName + "'");
                f.set(bm, newTestName);
            } catch (Exception ex) {
                System.out.println("ex:\n" + ex.getMessage());
            } finally {
                // close the browser
                WebDriver driver = DriverManager.getDriver();
                if (driver != null) {
                    driver.quit();
                }
            }
        }
        logger.debug("END: askeledz.driver.LocalWebDriverListener.afterInvocation");
    }
}