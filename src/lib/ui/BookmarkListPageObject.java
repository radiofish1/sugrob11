package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class BookmarkListPageObject extends MainPageObject {

    public BookmarkListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void deleteArticleFromListBySwipe(String articleTitle) {
        this.swipeElementToLeft(By.xpath("//*[contains(@text, '" + articleTitle + "')]"), "Cannot find '" + articleTitle + "'");
    }

    public void openArticle(String articleTitle){
        this.waitForElementAndClick(By.xpath("//*[contains(@text, '" + articleTitle + "')]"), "There is no article with title '" + articleTitle + "' on the screen", 5);
    }
}