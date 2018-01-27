package com.autotest.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MySeleniumMethods {

    private static final Logger logger = LogManager.getLogger(MySeleniumMethods.class);

    //isDisplayed
    public static boolean isDisplayed(By locator, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }



}
