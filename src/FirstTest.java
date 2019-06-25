import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.BookmarkListPageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCheckSearchResultPresent() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        int resultCount = searchPageObject.getSearchResultCount();
        Assert.assertTrue("There is no search results on the screen", resultCount > 0);

        searchPageObject.clickSearchCloseButton();
        searchPageObject.waitForSearchResultToDisappear();
    }

    @Test
    public void testSaveTwoArticlesToMyList() {

        String folderName = "Learning programming";
        String firstArticleTitle = "Java";
        String firstArticleSubtitle = "Object-oriented programming language";
        String SecondArticleTitle = "Appium";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        BookmarkListPageObject bookmarkPageObject = new BookmarkListPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(firstArticleTitle);
        searchPageObject.clickForSearchResult("Object-oriented programming language");

        articlePageObject.addArticleToBookmarksAndCreateList(folderName);
        articlePageObject.returnToMainScreen();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SecondArticleTitle);
        searchPageObject.clickForSearchResult("Appium");

        articlePageObject.addArticleToBookmarks(folderName);
        articlePageObject.returnToMainScreen();

        MainPageObject.openBookmarksList(folderName);

        bookmarkPageObject.deleteArticleFromListBySwipe(SecondArticleTitle);
        bookmarkPageObject.openArticle(firstArticleTitle);
        String articleSubtitle = articlePageObject.getArticleSubtitle();
        Assert.assertEquals("Title does not match. Expected: " + firstArticleSubtitle + ", actual: " + articleSubtitle, firstArticleSubtitle, articleSubtitle);
    }
    @Test
    public void testCheckArticleTitleWithoutWaiting(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String search_str = "Java";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_str);
        searchPageObject.clickForSearchResult("programming language");
        articlePageObject.assertSubtitlePresent();
    }
}