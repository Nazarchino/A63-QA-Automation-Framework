import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddSongToPlaylistTest extends BaseTest {

    LoginPage loginPage = null;

    HomePage homePage = null;


    @Test(groups = "Smoke")
    public void addSongToPlaylist() throws InterruptedException {
        String testPlaylistName = "Playlist 1";
        loginPage = new LoginPage(driver);
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        WebElement newPlaylist = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='sidebar-create-playlist-btn']")));
        Thread.sleep(1000);
        actions.moveToElement(newPlaylist).click().perform();
        newPlaylist.click();
        WebElement simplePlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='playlist-context-menu-create-simple']")));
        simplePlaylist.click();
        WebElement playlistName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#playlists > form > input[type=text]")));
        playlistName.sendKeys(testPlaylistName);
        playlistName.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        WebElement allSongs = driver.findElement(By.cssSelector("[href='#!/songs']"));
        allSongs.click();
        WebElement testSong = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item-container']//tr[@class='song-item']//td[text()='Riqui-Riqui']")));
        testSong.click();
        WebElement addTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='add-to-btn']")));
        addTo.click();
        WebElement playlist1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='songsWrapper']//section[@class='existing-playlists']//ul//li[contains(text(), 'Playlist 1')]")));
        playlist1.click();
        WebElement songAdded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show']")));
        Assert.assertTrue(songAdded.isDisplayed());
    }
}
