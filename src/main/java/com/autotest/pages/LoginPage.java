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

    @FindBy(how = How.ID, using = "email")
    WebElement userEmail;

    @FindBy(how = How.ID, using = "passwd")
    WebElement userPassword;

    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]")
    WebElement signInBtn;

    @FindBy(how = How.CSS, using = "#SubmitLogin > span")
    WebElement submitButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isEmailBoxVisible(){
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(userEmail));
        return userEmail.isDisplayed();
    }

    public boolean isPasswordBoxVisible(){
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(userPassword));
        return userPassword.isDisplayed();
    }

    public void inputEmail(String email) {
        isEmailBoxVisible();
        userEmail.sendKeys(email);
    }

    public void inputPassword(String pass) {
        isPasswordBoxVisible();
        userPassword.sendKeys(pass);
    }

    public void signInClick(){
        signInBtn.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void submitClick(){
        submitButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // go to data page from home page
    public MyPage goToMyPage(){
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();
        return new MyPage(driver);
    }
}
