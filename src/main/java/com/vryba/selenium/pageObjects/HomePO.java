package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePO {
    private Element loginButton;

    public HomePO(){
        this.loginButton=Element.find(By.xpath("//a[@class='login-link btn-clear']"));
    }

    public LoginPO loginButtonClick(){
        loginButton.click();
        return new LoginPO();
    }
}
