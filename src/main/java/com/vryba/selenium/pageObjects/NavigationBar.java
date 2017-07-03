package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Constant;
import com.vryba.selenium.utilities.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationBar {
    public Element questionsNavBarListItem;
    public Element jobsNavBarListItem;
    public Element docNavBarListItem;
    public Element tagsNavBarListItem;
    public Element usersNavBarListItem;
    public String inactiveNavBarTabAttribute = "-item";
    public String activeNavBarTabAttribute = inactiveNavBarTabAttribute+"_current";
    public String questionsPageUrl = Constant.URL+"questions";
    public String jobsPageUrl = Constant.URL+"jobs";
    public String docsPageUrl = Constant.URL+"docs";
    public String tagsPageUrl = Constant.URL+"tags";
    public String usersPageUrl = Constant.URL+"users";

    public NavigationBar(){
        this.questionsNavBarListItem = Element.find(By.xpath("//a[@id='nav-questions']/.."));
        this.jobsNavBarListItem = Element.find(By.xpath("//a[@id='nav-jobs']/.."));
        this.docNavBarListItem = Element.find(By.xpath("//a[@id='nav-docs']/.."));
        this.tagsNavBarListItem = Element.find(By.xpath("//a[@id='nav-tags']/.."));
        this.usersNavBarListItem = Element.find(By.xpath("//a[@id='nav-users']/.."));
    }

    public String getNavBarListItemStatus(){
        return questionsNavBarListItem.getAttribute("class");
    }

    public String getTitleTabText(){
        return questionsNavBarListItem.getAttribute("title");
    }

    public ExpectedCondition<Boolean> isTitleActive(String title) {
        return ExpectedConditions.titleIs(title);
    }

    public ExpectedCondition<Boolean> isUrlCorrect(String url) { return ExpectedConditions.urlContains(url); }

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
