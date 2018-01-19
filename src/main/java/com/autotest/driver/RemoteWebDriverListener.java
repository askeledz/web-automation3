package com.autotest.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;


public class RemoteWebDriverListener implements IInvokedMethodListener {

    static Logger log = Logger.getLogger(RemoteWebDriverListener.class);

    
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        log.debug("BEGINNING: organized.chaos.RemoteWebDriverListener.beforeInvocation");
        if (method.isTestMethod()) {
            // get browser name specified in the TestNG XML test suite file
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            URL hubURL = null;
            try {
                // get hub URL specified in the TestNG XML test suite file
                hubURL = new URL(method.getTestMethod().getXmlTest().getParameter("hubURL"));
            } catch (MalformedURLException e) {
                System.out.println("ex:\n" + e.getMessage() + "");
                e.printStackTrace();
            }
            log.info("HUB URL: " + hubURL);
            // get and set new instance of remote WebDriver
            WebDriver driver = RemoteDriverFactory.createInstance(hubURL, browserName);
            DriverManager.setWebDriver(driver);
        } else {
            log.warn("Provided method is NOT a TestNG testMethod!!!");
        }
        log.debug("END: organized.chaos.RemoteWebDriverListener.beforeInvocation");
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        log.debug("BEGINNING: organized.chaos.RemoteWebDriverListener.afterInvocation");
        if (method.isTestMethod()) {
            String browser = DriverManager.getBrowserInfo();
            try {
                BaseTestMethod bm = (BaseTestMethod)testResult.getMethod();
                Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
                f.setAccessible(true);
                String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - " + bm.getMethodName() + " - " + browser;
                log.info("Renaming test name from: '" + bm.getMethodName() + "' to: '" + newTestName + "'");
                f.set(bm, newTestName);
            } catch (Exception ex) {
                System.out.println("ex" + ex.getMessage());
            } finally {
                WebDriver driver = DriverManager.getDriver();
                if (driver != null) {
                    driver.quit();
                }
            }
        }
        log.debug("END: organized.chaos.RemoteWebDriverListener.afterInvocation");
    }
}