package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPO {

    private Element activeTab;
    private Element emailField;
    private Element passwordField;
    private Element loginSubmitButton;
    public static final String INCORRECT_EMAIL="The email is not a valid email address.";
    //public static final String INCORRECT_PASSWORD="The email is not a valid email address.";

    public LoginPO(){
        this.activeTab=Element.find(By.xpath(".//*[@id='tabs']/a[@class='youarehere']"));
        this.emailField=Element.find(By.id("email"));
        this.passwordField = Element.find(By.id("password"));
        this.loginSubmitButton = Element.find(By.id("submit-button"));
    }
    public String getActiveTabCaptionValue(){
        return activeTab.getText();
    }
    public LoginPO enterCredentials(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        return this;
    }
    public LoginPO submitButtonClick(){
        loginSubmitButton.click();
        return this;
    }
    public boolean isErrorDisplayed(String errMessage){

        return Element.find(By.xpath("//div[text()='"+errMessage+"']")).isVisibility(true);
    }
}
