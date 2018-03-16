package com.autotest;

import com.autotest.pages.LoginPage;
import com.autotest.pages.MyPage;
import com.autotest.test.BaseTestCase;
import com.autotest.util.Config;
import com.autotest.util.MySeleniumMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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
    @Test
    public void testLandingPage() {
        driver = invokeBrowser(Config.USER_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.signInClick();
        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);
        loginPage.submitClick();

        //Go to and Create My Page
        MyPage myPage = new MyPage(driver);

        Assert.assertEquals(true, MySeleniumMethods.isDisplayed(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(1) > a > span"), driver));

        MySeleniumMethods.takeScreenshot(driver,getClass().getSimpleName().toString());
    }


}
