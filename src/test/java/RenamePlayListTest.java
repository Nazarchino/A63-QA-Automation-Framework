import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class RenamePlayListTest extends BaseTest {
    String newPlayListName = "New playlist";
    String currentPlayListName = "Playlist 1";
    HomePage homePage = null;

    @Test
    public void renamePlayListTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        Thread.sleep(2000);
        homePage = new HomePage(getDriver());
        homePage.renamePlayList(currentPlayListName, newPlayListName);

        Assert.assertEquals(newPlayListName, homePage.getPlayListByName(newPlayListName).getText());
        //Assert.assertTrue(homePage.getPlaylistByName(newPlayListName).isDisplayed());
    }

    @AfterMethod
    public void rollBackChanges() throws InterruptedException {
        homePage = new HomePage(getDriver());
        homePage.renamePlayList(newPlayListName, currentPlayListName);
    }
}