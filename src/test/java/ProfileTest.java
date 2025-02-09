import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class ProfileTest extends BaseTest {


    LoginPage loginPage = null;


    @Test(groups = "Smoke")
    public void changeProfileNameTest() throws InterruptedException {
        String newName = UUID.randomUUID().toString();
        loginPage = new LoginPage(driver);
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        WebElement avatar = driver.findElement(By.cssSelector("#userBadge, [class=profile]"));
        avatar.click();
        WebElement currentPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileCurrentPassword")));
        currentPasswordField.sendKeys("Pomidor2115");
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileName")));
        nameInput.clear();
        nameInput.sendKeys(newName);
        WebElement saveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-submit")));
        saveBtn.click();
        Thread.sleep(1000); //does not work without thread sleep, although the code seems correct
        WebElement userNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#userBadge span[class=name]")));
        Assert.assertEquals(newName, userNameLabel.getText());
//        Thread.sleep(3000);
//        WebElement userNameLabel = driver.findElement(By.cssSelector("#userBadge span[class=name]"));
//        Assert.assertEquals(newName, userNameLabel.getText());
    }
}
