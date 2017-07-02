package com.vryba.selenium.pageObjects;

import com.vryba.selenium.utilities.Element;
import com.vryba.selenium.utilities.Elements;
import org.openqa.selenium.By;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class SearchResultPage {
    private Elements searchResults;
    private Element resCountDisplayBox;

    public SearchResultPage() {

        this.searchResults = Elements.find(By.xpath("//div[@class='result-link']//a"));
        this.resCountDisplayBox = Element.find(By.xpath(".//*[@id='mainbar']/div[2]/h2"));
    }
    public boolean areReturnResultsKeywordRelevant(String text) {
        return this.searchResults.isStringHasText(text);
    }
    public boolean isTotalResultsCountDisplayed(String text) {
        return this.resCountDisplayBox.isVisibility(true);
    }
    public boolean checkingResultsCount(){
        if (resultCount()>0)
        return true;
        else{
            System.out.println("Search query produced no result");
            return false;
        }
    }
    public double resultCount() {
        String countText = resCountDisplayBox.getContainingText();
        return extractNumberFromResultCountText(countText);
    }
    private double extractNumberFromResultCountText(String countText) {
        countText = countText.replaceAll(",",".");
        return Double.parseDouble(countText.substring(0, countText.indexOf(" results")));

    }
}
