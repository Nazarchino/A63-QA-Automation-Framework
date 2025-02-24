import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.PlaylistPage;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.WaitUtils.waitUntilVisibilityOfElementLocatedBy;

public class DeletePlaylistTest extends BaseTest {

    LoginPage loginPage = null;

    HomePage homePage = null;


    @Test
    public void deletePlaylist() throws InterruptedException {
        String PlaylistName = "PlaylistForDeleting";
        loginPage = new LoginPage(getDriver());
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        homePage = new HomePage(getDriver());
        homePage.createPlaylist(actions, PlaylistName);
        homePage.openPlaylist(PlaylistName);
        PlaylistPage playlistPage = new PlaylistPage(getDriver());
        playlistPage.deletePlaylist(wait);
        waitUntilVisibilityOfElementLocatedBy(getDriver(), By.xpath("//section[@id='playlists']//li//a[text()='PlaylistForDeleting']"));
        Assert.assertTrue(homePage.getPlaylistByName(PlaylistName).isDisplayed());


    }
        @Test
        public void addPlaylistTest() throws InterruptedException{
        String PlaylistName = "Playlist 1";
        loginPage = new LoginPage(getDriver());
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        homePage = new HomePage(getDriver());
        int previousSize = homePage.getAllPlaylists().size();
        homePage.createPlaylist(PlaylistName);
        int actualSize = homePage.getAllPlaylists().size();
        Assert.assertNotEquals(actualSize, previousSize);
    }
}

