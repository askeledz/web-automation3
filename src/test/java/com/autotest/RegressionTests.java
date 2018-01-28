package com.autotest;

import com.autotest.driver.DriverManager;
import com.autotest.pages.HomePage;
import com.autotest.pages.LoginPage;
import com.autotest.pages.MyPage;
import com.autotest.test.BaseTestCase;
import com.autotest.util.MySeleniumMethods;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RegressionTests extends BaseTestCase {



    //Dave Haeffner’s Practice Site
    private static String PAGE_URL = "http://automationpractice.com/";

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
        //driver = DriverManager.getDriver();
        //System.setProperty("log4j.configuration", "log4j2-test.properties");


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


        //Take screenshot
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Parameters
    @Test
    public void extestLandingPage() {
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

        logger.info("Thread id = " + Thread.currentThread().getId());
        logger.info("Hash code of webDriver instance = " + driver.hashCode());
        logger.info("Test executed using = " + DriverManager.getBrowserInfo());
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }


}
