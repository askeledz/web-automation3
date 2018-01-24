package com.autotest;

import com.autotest.driver.DriverManager;
import com.autotest.pages.HomePage;
import com.autotest.pages.LoginPage;
import com.autotest.pages.MyPage;
import com.autotest.util.MySeleniumMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RegressionTests {

    static Logger log = Logger.getLogger(RegressionTests.class);

    //Dave Haeffner’s Practice Site
    private static String PAGE_URL = "http://automationpractice.com/";

    private static WebDriver driver = null;

    @Test
    public void testLoginPage() throws InterruptedException, IOException {

        //driver = DriverManager.getDriver();
        driver = invokeBrowser(PAGE_URL);

        // Create home page object....
        HomePage homePage = new HomePage(driver);

        //Go to and Create Login Page
        LoginPage loginPage = homePage.goToLoginPage();

        //Enter credentials
        loginPage.inputEmail("andrej.skeledzija@gmail.com");
        loginPage.inputPassword("123456789");

        //Go to and Create My Page
        MyPage myPage = loginPage.goToMyPage();

        Assert.assertEquals(true, MySeleniumMethods.isDisplayed(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(1) > a > span"), driver));

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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
