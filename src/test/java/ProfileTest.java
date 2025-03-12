import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class ProfileTest extends BaseTest {

    @Test(groups = "Smoke")
    public void changeProfileNameTest() throws InterruptedException {
        String newName = UUID.randomUUID().toString();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        WebElement avatar = getDriver().findElement(By.cssSelector("#userBadge img"));
        Thread.sleep(1000);
        avatar.click();
        WebElement currentPasswordField = getDriver().findElement(By.cssSelector("#inputProfileCurrentPassword"));
        currentPasswordField.sendKeys("Pomidor2115");
        WebElement nameInput = getDriver().findElement(By.cssSelector("#inputProfileName"));
        nameInput.clear();
        nameInput.sendKeys(newName);
        WebElement saveButton = getDriver().findElement(By.cssSelector(".btn-submit"));
        saveButton.click();
        Thread.sleep(1000);
        WebElement userNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#userBadge span[class=name]")));
        Assert.assertEquals(newName, userNameLabel.getText());
    }
}