import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.PlaylistPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

import static org.example.WaitUtils.waitUntilVisibilityOfElementLocatedBy;

public class DeletePlayListTest extends BaseTest {

    LoginPage loginPage = null;
    HomePage homePage = null;

    @Test
    public void deletePlayListTest() throws InterruptedException {
        String playListName = "TestPlayListForDeleting";
        loginPage = new LoginPage(getDriver());
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        homePage = new HomePage(getDriver());
        homePage.createPlaylist(actions, playListName);
        homePage.openPlayList(playListName);
        PlaylistPage playlistPage = new PlaylistPage(getDriver());
        playlistPage.deletePlayList(wait);
        waitUntilVisibilityOfElementLocatedBy(getDriver(), By.xpath("//section[@id='playlists']//li/a[text()='TestPlayListForDeleting']"));
        Assert.assertTrue(homePage.getPlaylistByName(playListName).isDisplayed());
    }

    @Test
    public void addPlayListTest() throws InterruptedException {
        String playListName = "TestPlayListForDeleting";
        loginPage = new LoginPage(getDriver());
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        homePage = new HomePage(getDriver());
        int previousSize = homePage.getAllPlayLists().size();
        homePage.createPlayList(playListName);
        int actualSize = homePage.getAllPlayLists().size();
        Assert.assertNotEquals(actualSize, previousSize); // previousSize != actualSize
    }
}