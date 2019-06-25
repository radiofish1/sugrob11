package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            ARTICLE_MENU_BOOKMARK = "//*[@resource-id='org.wikipedia:id/article_menu_bookmark']",
            GOT_IT_BUTTON = "//*[@text='GOT IT']",
            CREATE_NEW_LIST_BUTTON = "//*[@text='Create new']",
            LIST_TITLE_INPUT = "//*[@resource-id='org.wikipedia:id/text_input']",
            CREATE_LIST_OK_BUTTON = "//*[@text= 'OK']",
            NAVIGATE_MENU_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            NO_THANKS_BUTTON = "//*[@resource-id='android:id/button2']",
            ARTICLE_SUBTITLE = "//*[@resource-id='pagelib_edit_section_title_description']";


    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public void addArticleToBookmarksAndCreateList(String list_name){
        this.waitForElementAndClick(By.xpath(ARTICLE_MENU_BOOKMARK), "Cannot find button to open article options", 5);
        this.waitForElementAndClick(By.xpath(GOT_IT_BUTTON), "Cannot find 'GOT IT' button", 5);
        this.waitForElementAndClick(By.xpath(CREATE_NEW_LIST_BUTTON), "Cannot find button to create a new reading list", 5);
        this.waitForElementAndSendKeys(By.xpath(LIST_TITLE_INPUT), list_name,"Cannot put text into articles folder name input", 5);
        this.waitForElementAndClick(By.xpath(CREATE_LIST_OK_BUTTON), "Cannot press OK button", 5);
    }

    public void addArticleToBookmarks(String list_name){
        this.waitForElementAndClick(By.xpath(ARTICLE_MENU_BOOKMARK), "Cannot find button to open article options", 10);
        this.waitForElementAndClick(By.xpath("//*[contains(@text, 'Learning programming')]"), "Cannot find folder " + list_name, 5);
    }

    public void returnToMainScreen(){

        this.waitForElementAndClick(By.xpath(NAVIGATE_MENU_BUTTON), "There is no menu button on the screen", 5);
                this.waitForElementAndClick(By.xpath(NO_THANKS_BUTTON), "There is no 'no thanks' button on the screen", 10);
    }

    public String getArticleSubtitle(){
        WebElement subtitle_element = this.waitForElementPresent(By.xpath(ARTICLE_SUBTITLE),"There is no article subtitle on the screen",5);
        return subtitle_element.getText();
    }

    public void assertSubtitlePresent(){
        this.assertElementPresent(By.xpath(ARTICLE_SUBTITLE), "We don't found subtitle element");
    }

}