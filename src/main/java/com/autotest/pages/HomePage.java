package com.autotest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author askeledzija
 */


public class HomePage {

        WebDriver driver;

        @FindBy(how = How.CLASS_NAME, using = "login")
        WebElement signInBtn;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(signInBtn));
    }

    public boolean isHomePageLoaded(){
        if ( driver.getTitle().contains("Store")) {
            return true;
        }
        return false;
    }

    // go to Login page from Home page
    public LoginPage goToLoginPage() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(signInBtn));
        signInBtn.click();
        return new LoginPage(driver);
    }

}
