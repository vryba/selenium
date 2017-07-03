package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationBar {
    public Element questionsNavBarListItem;
    public Element jobsNavBarListItem;
    public Element docNavBarListItem;
    public Element tagsNavBarListItem;
    public Element usersNavBarListItem;

    public NavigationBar(){
        this.questionsNavBarListItem = Element.find(By.xpath("//a[@id='nav-questions']/.."));
        this.jobsNavBarListItem = Element.find(By.xpath("//a[@id='nav-jobs']/.."));
        this.docNavBarListItem = Element.find(By.xpath("//a[@id='nav-docs']/.."));
        this.tagsNavBarListItem = Element.find(By.xpath("//a[@id='nav-tags']/.."));
        this.usersNavBarListItem = Element.find(By.xpath("//a[@id='nav-users']/.."));
    }

/*    public NavigationBar selectTab(int pageNumber) throws Exception {
        WebElement page = wait.until(ExpectedConditions.
                elementToBeClickable(pageLocator(pageNumber)));
        page.click();
        return new ResultsPage(driver, keyword);
    public String getNavBarListItemStatus(){
        return questionsNavBarListItem.getAttribute("class");
    }

    public String getTitle(){
        return questionsNavBarListItem.getAttribute("title");
    }

    public Boolean titleIs(String title) {
        return wait.until(ExpectedConditions.titleIs(title));
    }

    public Boolean urlIs(String url) {
        return wait.until(ExpectedConditions.urlContains(url));
    }*/

    public QuestionsPage questionsNavBarItemClick() {
        questionsNavBarListItem.click();
        return new QuestionsPage();
    }
    public JobsPage jobsNavBarItemClick() {
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
    }
}
