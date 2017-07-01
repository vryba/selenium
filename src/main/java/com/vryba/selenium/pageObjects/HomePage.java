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
    public Element questionsNavBarListItem;
    public Element jobsNavBarListItem;
    public Element docNavBarListItem;
    public Element tagsNavBarListItem;
    public Element usersNavBarListItem;

    public HomePage() {
        this.loginButton = Element.find(By.xpath("//a[@class='login-link btn-clear']"));
        this.avatarLogo = Element.find(By.xpath("//div[contains(@class, 'gravatar-wrapper')]"));
        this.searchField = Element.find(By.xpath("//input[contains(@class, 'js-search-field')]"));
        this.searchButton = Element.find(By.xpath("//button[@class='btn js-search-submit']"));
        this.joinStackBox = Element.find(By.xpath(".//*[@id='herobox']"));
        this.dismissButton = Element.find(By.xpath(".//*[@id='close']/a"));
        this.askQButton = Element.find(By.xpath(".//*[@id='sidebar']/div[1]/a"));
        this.questionsNavBarListItem = Element.find(By.xpath("//a[@id='nav-questions']/.."));
        this.jobsNavBarListItem = Element.find(By.xpath("//a[@id='nav-jobs']/.."));
        this.docNavBarListItem = Element.find(By.xpath("//a[@id='nav-docs']/.."));
        this.tagsNavBarListItem = Element.find(By.xpath("//a[@id='nav-tags']/.."));
        this.usersNavBarListItem = Element.find(By.xpath("//a[@id='nav-users']/.."));
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
    public QuestionsPage questionsNavBarItemClick() {
        questionsNavBarListItem.click();
        return new QuestionsPage();
    }
/*    public JobsPage jobsNavBarItemClick() {
        jobsNavBarListItem.click();
        return new JobsPage();
    }
    public DocumentationPage docNavBarItemClick() {
        docNavBarListItem.click();
        return new DocumentationPage();
    }
    public TagsPage tagsNavBarItemClick() {
        tagsNavBarListItem.click();
        return new TagsPage();
    }
    public UsersPage usersNavBarItemClick() {
        usersNavBarListItem.click();
        return new UsersPage();
    }*/

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

    public String getNavBarListItemStatus(){
        return questionsNavBarListItem.getAttribute("class");
    }

    public String getTitle(){
        return questionsNavBarListItem.getAttribute("title");
    }

    public SearchResultPage insertStringInSearchField(String inputString) {
        searchField.click().sendKeys(inputString);
        searchButton.click();
        return new SearchResultPage();
    }
}
