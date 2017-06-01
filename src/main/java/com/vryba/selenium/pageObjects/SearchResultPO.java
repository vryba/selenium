package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Elements;
import org.openqa.selenium.By;

public class SearchResultPO {
    private Elements searchResults;

    public SearchResultPO(){
        this.searchResults = Elements.find(By.xpath("//div[@class='result-link']//a"));
    }

    public boolean areListItemsHaveText(String text){
        return this.searchResults.isStringHasText(text);
    }
}
