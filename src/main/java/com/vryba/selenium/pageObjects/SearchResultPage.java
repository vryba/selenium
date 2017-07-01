package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Elements;
import org.openqa.selenium.By;

public class SearchResultPage {
    private Elements searchResults;

    public SearchResultPage(){

        this.searchResults = Elements.find(By.xpath("//div[@class='result-link']//a"));
    }

    public boolean areReturnResultsKeywordRelevant(String text){
        return this.searchResults.isStringHasText(text);
    }
}
