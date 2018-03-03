package com.autotest;

import com.autotest.driver.DriverManager;
import com.autotest.pages.HomePage;
import com.autotest.pages.LoginPage;
import com.autotest.pages.MyPage;
import com.autotest.test.BaseTestCase;
import com.autotest.util.Config;
import com.autotest.util.MySeleniumMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class RegressionTests extends BaseTestCase {

    private static WebDriver driver = null;

    //Logger
    private static final Logger logger = LogManager.getLogger(RegressionTests.class);

    @BeforeMethod
    public void beforeMethod() {

    }

    @BeforeTest
    public void beforeTest() {

    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterTest
    public void afterTest() {

    }

    @Parameters
    @Override
    @Test
    public void testLandingPage() {
        driver = invokeBrowser(Config.USER_URL);

        //Creating HomePage
        HomePage homePage = new HomePage(driver);

        //Go to and Create Login Page
        LoginPage loginPage = homePage.goToLoginPage();

        //Enter credentials
        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        //Go to and Create My Page
        MyPage myPage = loginPage.goToMyPage();

        Assert.assertEquals(true, MySeleniumMethods.isDisplayed(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(1) > a > span"), driver));

        MySeleniumMethods.takeScreenshot(driver,getClass().getSimpleName().toString());
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
        logger.info("START: " + DriverManager.getBrowserInfo() + " - " + getClass().getSimpleName().toString());
        //logger.info("Thread id = " + Thread.currentThread().getId());
        //logger.info("Driver instance= " + driver.hashCode());
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

}
