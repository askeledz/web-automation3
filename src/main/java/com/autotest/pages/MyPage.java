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


public class MyPage {

    WebDriver driver;

    @FindBy(how = How.CLASS_NAME, using = "logout")
    WebElement logOutBtn;


    public MyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(logOutBtn));
    }

    public boolean isHomePageLoaded(){
        if ( driver.getTitle().contains("My account")) {
            return true;
        }
        return false;
    }

}