package com.vryba.selenium.pageObjects;
import com.gargoylesoftware.htmlunit.Page;
import com.vryba.selenium.utilities.Element;
import org.openqa.selenium.By;

public class HomePage {
    private Element loginButton;
    private Element avatarLogo;
    private Element searchField;
    private Element searchButton;
    private Element joinStackBox;
    private Element dismissButton;
    private Element askQButton;
    public Element loginWarning;

    public HomePage() {
        this.loginButton = Element.find(By.xpath("//a[@class='login-link btn-clear']"));
        this.avatarLogo = Element.find(By.xpath("//div[contains(@class, 'gravatar-wrapper')]"));
        this.searchField = Element.find(By.xpath("//input[contains(@class, 'js-search-field')]"));
        this.searchButton = Element.find(By.xpath("//button[@class='btn js-search-submit']"));
        this.joinStackBox = Element.find(By.xpath(".//*[@id='herobox']"));
        this.dismissButton = Element.find(By.xpath(".//*[@id='close']/a"));
        this.askQButton = Element.find(By.xpath(".//*[@id='sidebar']/div[1]/a"));
    }

    public HomePage loginWarning(String text){
        this.askQButton.click();
        return new HomePage();
    }

    public String getLoginWarningText(){
        return loginWarning.getContainingText();
    }

    public LoginPage loginButtonClick() {
        loginButton.click();
        return new LoginPage();
    }

    public HomePage dismissButtonClick(){
        this.dismissButton.click();
        return new HomePage();
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
}
