import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddSongToPlaylistTest extends BaseTest {

    @Test(groups = "Smoke")
    public void addSongToPlaylist() throws InterruptedException {
        login("nazar@testpro.io", "Pomidor2115");
        int width = 1920;
        int height = 1080;
        Dimension dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);
//        WebElement newPlaylist = driver.findElement(By.cssSelector("sidebar-create-playlist-btn"));
//        newPlaylist.click();
//        WebElement simplePlaylist = driver.findElement(By.cssSelector("playlist-context-menu-create-simple"));
//        simplePlaylist.click();
//        WebElement playlistName = driver.findElement(By.cssSelector("#playlists > form > input[type=text]"));
//        playlistName.sendKeys("Playlist 1");
//        playlistName.sendKeys(Keys.RETURN);
//        WebElement createdPlaylistText = driver.findElement(By.cssSelector("[class='success show']"));
//        Assert.assertTrue(createdPlaylistText.isDisplayed());
        WebElement allSongs = driver.findElement(By.cssSelector("[href='#!/songs']"));
        allSongs.click();
        WebElement testSong = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item-container']//tr[@class='song-item']//td[text()='Riqui-Riqui']")));
        testSong.click();
        WebElement addTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='add-to-btn']")));
        addTo.click();
        WebElement playlist1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='songsWrapper']//section[@class='existing-playlists']//ul//li[contains(text(), 'Playlist 1')]")));
        playlist1.click();
        WebElement songAdded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.alertify-logs.top.right > div")));
        Assert.assertTrue(songAdded.isDisplayed());
    }
}
