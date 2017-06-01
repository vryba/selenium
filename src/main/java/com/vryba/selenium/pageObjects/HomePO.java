package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Element;
import org.openqa.selenium.By;

public class HomePO {
    private Element loginButton;
    private Element avatarLogo;
    private Element searchField;
    private Element searchButton;

    public HomePO() {
        this.loginButton = Element.find(By.xpath("//a[@class='login-link btn-clear']"));
        this.avatarLogo = Element.find(By.xpath("//div[contains(@class, 'gravatar-wrapper')]"));
        this.searchField = Element.find(By.xpath("//input[contains(@class, 'js-search-field')]"));
        this.searchButton = Element.find(By.xpath("//button[@class='btn js-search-submit']"));
    }

    public LoginPO loginButtonClick() {
        loginButton.click();
        return new LoginPO();
    }

    public String getAvatarTitle() {
        return avatarLogo.getAttribute("title");
    }

    public SearchResultPO insertStringInSearchField(String inputString) {
        searchField.click().sendKeys(inputString);
        searchButton.click();
        return new SearchResultPO();
    }

}
