package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Element;
import org.openqa.selenium.By;

public class QuestionsPage {
    private Element activeNavBarListItem;
    private Element activeTab;
    private Element subHeader;
    private Element questionsSummaryNum;

    public QuestionsPage(){

        this.activeNavBarListItem = Element.find(By.xpath(""));
        this.activeTab = Element.find(By.xpath(""));
        this.subHeader = Element.find(By.xpath(""));
        this.questionsSummaryNum = Element.find(By.xpath(""));

    }
}
