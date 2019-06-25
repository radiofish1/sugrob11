package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_RESULT_TITLE_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/fragment_search_results']//*[contains(@text, '{SUBSTRING}')]",
            SEARCH_CLOSE_BUTTON = "//*[@resource-id='org.wikipedia:id/search_close_btn']";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }


    private static String getResultSearchElement(String substr) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substr);
    }

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "There is no search field on the screen", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String seach_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), seach_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXpath), "Cannot find search result");
    }

    public void clickForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find search result", 10);
    }

    public List<WebElement> getSearchResult(){
        List<WebElement> searchResults = this.waitForListOfElementsPresentByXPath(By.xpath(SEARCH_RESULT_TITLE_ELEMENT),"There is no search results on the screen", 15);
        return searchResults;
    }

    public int getSearchResultCount(){
        List<WebElement> searchResults = getSearchResult();
        return searchResults.size();
    }

    public void clickSearchCloseButton(){
        this.waitForElementAndClick(By.xpath(SEARCH_CLOSE_BUTTON), "There is no search close button on the screen", 5);
    }

    public void waitForSearchResultToDisappear(){
        this.waitForElementNotPresent(By.xpath(SEARCH_RESULT_TITLE_ELEMENT), "Search results still present on the screen", 5);
    }

}