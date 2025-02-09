import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PlaySongByActionClickTest extends BaseTest {

    LoginPage loginPage = null;


    @Test
    public void playSongByContextClick() {
        String songLocator = "//article[@data-test='song-card']//span[contains(text(), '%s')]";
        String songName = "Way Of The Samurai";
        loginPage = new LoginPage(driver);
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        WebElement allSongsMenuItem = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[id='sidebar'] .songs")));
        actions.click(allSongsMenuItem).perform();
        WebElement song = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(songLocator, songName))));
        actions.contextClick(song).perform();
        WebElement playFromContextMenu = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='song-context-menu'] .playback")));
        actions.click(playFromContextMenu).perform();
        WebElement pause = driver.findElement(By.cssSelector("[class='fa fa-pause']"));
        Assert.assertTrue(pause.isDisplayed());

    }
}
