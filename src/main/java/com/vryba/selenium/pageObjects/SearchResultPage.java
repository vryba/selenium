package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Element;
import com.vryba.selenium.utilities.Elements;
import org.openqa.selenium.By;

public class SearchResultPage {
    private Elements searchResults;
    private Element resCountDisplayBox;

    public SearchResultPage() {

        this.searchResults = Elements.find(By.xpath("//div[@class='result-link']//a"));
        this.resCountDisplayBox = Element.find(By.xpath("//div[@id='mainbar']/div[2]/h2"));
    }

    public boolean areReturnResultsKeywordRelevant(String text) {
        return this.searchResults.isStringHasText(text);
    }
    public boolean isTotalResultsCountDisplayed(String text) {
        return this.resCountDisplayBox.isVisibility(true);
    }
    public boolean checkingResultsCount(){
        if (resultCount()>0){
            System.out.println("At least Search returned one query result");
            return true;
        } else{
            System.out.println("Search query produced no result");
            return false;
        }
    }
    private double resultCount() {
        String countText = resCountDisplayBox.getContainingText();
        return extractNumberFromResultCountText(countText);
    }
    private double extractNumberFromResultCountText(String countText) {
        countText = countText.replaceAll(",",".");
        return Double.parseDouble(countText.substring(0, countText.indexOf(" results")));

    }
}
