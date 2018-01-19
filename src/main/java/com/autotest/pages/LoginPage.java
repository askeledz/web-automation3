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
public class LoginPage {

        WebDriver driver;

        @FindBy(how = How.CSS, using = "#lst-ib")
        WebElement input_text_box;

        public LoginPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

//        // go to data page from home page
//        public DataPage goToDataPage(){
//            //   new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(data_link));
//            data_link.click();
//            return new DataPage(driver);
//        }

        //ToDO We will use this boolean for assertion. To check if page is opened


    public boolean isTextBoxVisible(){
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(input_text_box));
        return input_text_box.isDisplayed();
    }

        public void inputSomeTextToTextBox(String inputText) {
            isTextBoxVisible();
            input_text_box.sendKeys(inputText);
        }

    }

