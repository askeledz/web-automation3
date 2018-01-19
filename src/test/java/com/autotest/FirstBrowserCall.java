package com.autotest;

import com.autotest.driver.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;

//import com.autotest.pages.wbd.Country;
//import com.autotest.pages.wbd.CountryPage;
//import com.autotest.pages.wbd.DataPage;
//import com.autotest.pages.wbd.HICPage;
//import com.autotest.pages.wbd.Header;
//import com.autotest.pages.wbd.HomePage;
//import com.autotest.pages.wbd.OldPage;
//import com.autotest.pages.wbd.ValuesPage;
//import com.autotest.utils.CSVUtil;

public class FirstBrowserCall {

    static Logger log = Logger.getLogger(FirstBrowserCall.class);
    private static String PAGE_URL = "https://www.google.hr";

    private static WebDriver driver = null;

    @Test
    public void testVerifyHomePage() throws InterruptedException, IOException {

        //driver = DriverManager.getDriver();

        driver = invokeBrowser(PAGE_URL);

    }

    @Test
    public void testVerifyHomePage2() throws InterruptedException, IOException {

        //driver = DriverManager.getDriver();

        driver = invokeBrowser(PAGE_URL);

    }

    /*********** hellper methods *************/

    public static void clickAllHyperLinksByCountryName(String countryName) throws InterruptedException {
        getElementWithIndex(countryName).click();
    }

    public static WebElement getElementWithIndex(String countryName) {
        return driver.findElement(By.linkText(countryName));
    }

    private WebDriver invokeBrowser(String url) {
        // private void invokeBrowser(String url) {
        WebDriver driver = DriverManager.getDriver();

        log.info("Thread id = " + Thread.currentThread().getId());
        log.info("Hash code of webDriver instance = " + driver.hashCode());
        log.info("Test executed using = " + DriverManager.getBrowserInfo());
        driver.get(url);
        // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}