package com.vryba.selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPO {

    private WebDriver driver;
    private WebElement activeTab;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement loginSubmitButton;
    public static final String INCORRECT_EMAIL="The email is not a valid email address.";
    //public static final String INCORRECT_PASSWORD="The email is not a valid email address.";

    public LoginPO(WebDriver driver){
        this.driver=driver;
        this.activeTab=driver.findElement(By.xpath(".//*[@id='tabs']/a[@class='youarehere']"));
        this.emailField=driver.findElement(By.id("email"));
        this.passwordField = driver.findElement(By.id("password"));
        this.loginSubmitButton = driver.findElement(By.id("submit-button"));
    }
    public String getActiveTabCaptionValue(){
        return activeTab.getText();
    }
    public LoginPO enterCredentials(String email, String password){
        emailField.clear();
        emailField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }
    public LoginPO submitButtonClick(){
        loginSubmitButton.click();
        return this;
    }
    public boolean isErrorDisplayed(String errMessage){

        return driver.findElement(By.xpath("//div[text()='"+errMessage+"']")).isDisplayed();
    }
}
