package com.vryba.selenium.pageObjects;

import com.vryba.selenium.dataProvider.UsersData;
import com.vryba.selenium.utilities.Element;
import org.openqa.selenium.By;

public class SignUpPage {
    private Element activeTab;
    private Element displayName;
    private Element emailField;
    private Element passwordField;
    private Element signUpSubmitButton;
    public static final String EMPTY_MAIL = "Email cannot be empty.";
    public static final String EMPTY_PASSWORD = "Password cannot be empty.";
    public static final String INVALID_EMAIL = "The email is not a valid email address.";
    public static final String INVALID_PASSWORD = "The email or password is incorrect.";

    public SignUpPage() {
        this.activeTab = Element.find(By.xpath("//div[@id='tabs']/a[2]"));
        this.emailField = Element.find(By.id("email"));
        this.passwordField = Element.find(By.id("password"));
        this.signUpSubmitButton = Element.find(By.xpath("//a[@id='tell-me-more']"));
        this.displayName = Element.find(By.xpath("input[@id='display-name']"));
    }

    public String getActiveTabCaptionValue() {
        return activeTab.getText();
    }

    public SignUpPage enterCredentials(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        return this;
    }

    public SignUpPage enterCredentials(UsersData usersData) {
        emailField.sendKeys(usersData.getEmail());
        passwordField.sendKeys(usersData.getPassword());
        return this;
    }

    public SignUpPage submitButtonClick () {
        signUpSubmitButton.click();
        return this;
    }

    public boolean isErrorDisplayed(String errMessage) {
        return Element.find(By.xpath("//div[text()='" + errMessage + "']")).isVisibility(true);
    }
}
