package com.vryba.selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePO {
    private WebDriver driver;
    private WebElement loginButton;

    public HomePO(WebDriver driver){
        this.driver=driver;
        this.loginButton=driver.findElement(By.xpath("//a[@class='login-link btn-clear']"));
    }

    public LoginPO loginButtonClick(){
        loginButton.click();
        return new LoginPO(driver);
    }
}
