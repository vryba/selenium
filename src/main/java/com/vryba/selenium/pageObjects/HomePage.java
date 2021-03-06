package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.BrowserUtilities;
import com.vryba.selenium.utilities.Element;
import org.openqa.selenium.By;
/**
 * HomePage object is an object-oriented class that serves as an interface to a Home page of AUT
 */
public class HomePage {
    private Element loginButton;
    private Element signUpButton;
    private Element avatarLogo;
    private Element searchField;
    private Element searchButton;
    private Element askQButton;
    private Element loginWarning;
    private Element firstQuestionTag;
    private Element firstQuestionTagContent;
    private Element activeTab;

    public HomePage() {
        // Below is a so called 'User Interface Map', a mechanism that stores all the locators for a page test
        // in one place for easy modification when identifiers or paths to UI elements change in the AUT
        this.signUpButton = Element.find(By.xpath("//a[@class='login-link btn']"));
        this.avatarLogo = Element.find(By.xpath("//div[contains(@class, 'gravatar-wrapper')]"));
        this.searchField = Element.find(By.xpath("//input[contains(@class, 'js-search-field')]"));
        this.searchButton = Element.find(By.xpath("//button[@class='btn js-search-submit']"));
        this.askQButton = Element.find(By.xpath("//div[@id='sidebar']/div[1]/a"));
        this.loginWarning = Element.find(By.xpath("//*[@id='login-page']/div[1]/p[1]"));
        this.firstQuestionTag = Element.find(By.xpath("//div[@class='question-summary narrow'][1]//div[contains(@class, 'tags')]/a[1]"));
        this.firstQuestionTagContent = Element.find(By.xpath("//div[@class='tm-description']"));
        this.loginButton = Element.find(By.xpath("//a[@class='login-link btn-clear']"));
        this.activeTab = Element.find(By.xpath("//div[@id='tabs']/a[2]"));
    }

    public String getTagContent(){

        return  firstQuestionTag.getText();
    }

    public HomePage mouseOverTag(){

        new BrowserUtilities().scrollToElement(firstQuestionTag);
        firstQuestionTag.mouseOver();
        return this;
    }

    public boolean isTagDescriptionContainsText(String text){

        return firstQuestionTagContent.getText().toLowerCase().contains(text.toLowerCase());
    }

    public HomePage signUpButtonClick() {
        signUpButton.click();
        return new HomePage();
    }

    public String getLoginWarningText(){
        return loginWarning.getContainingText();
    }

    public LoginPage loginButtonClick() {
        loginButton.click();
        return new LoginPage();
    }

    public HomePage askQButtonClick(){
        this.askQButton.click();
        return new HomePage();
    }

    public String getAvatarTitle() {
        return avatarLogo.getAttribute("title");
    }

    public SearchResultPage insertStringInSearchField(String inputString) {

        searchField.click().sendKeys(inputString);
        searchButton.click();
        return new SearchResultPage();
    }

    public String getActiveTabCaptionValue() {
        return activeTab.getText();
    }
}
