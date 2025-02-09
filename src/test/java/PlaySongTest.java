import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PlaySongTest extends BaseTest {

    LoginPage loginPage = null;


    @Test (groups = "Homework18")
    public void playSong() {
        loginPage = new LoginPage(driver);
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        WebElement playButtonBar = driver.findElement(By.cssSelector("[data-testid='play-next-btn']"));
        playButtonBar.click();
        WebElement playButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='play-btn']")));
        playButton.click();
        WebElement pause = driver.findElement(By.cssSelector("[class='fa fa-pause']"));
        Assert.assertTrue(pause.isDisplayed());
    }


}