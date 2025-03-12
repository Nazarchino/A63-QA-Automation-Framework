import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PlaySongTest extends BaseTest {
    @Test
    public void playSong() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        WebElement playButtonBar = getDriver().findElement(By.cssSelector("[data-testid='play-next-btn']"));
        playButtonBar.click();
        WebElement playButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='play-btn']")));
        playButton.click();
        WebElement pause = getDriver().findElement(By.cssSelector("[class='fa fa-pause']"));
        Assert.assertTrue(pause.isDisplayed());
    }


}
