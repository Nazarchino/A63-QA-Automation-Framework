import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class ProfileTest extends BaseTest {


    @Test(groups = "Smoke")
    public void changeProfileNameTest() {
        String newName = UUID.randomUUID().toString();
        login("nazar@testpro.io", "Pomidor2115");
        WebElement avatar = driver.findElement(By.cssSelector("#userBadge, [class=profile]"));
        avatar.click();
        WebElement currentPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileCurrentPassword")));
        currentPasswordField.sendKeys("Pomidor2115");
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileName")));
        nameInput.clear();
        nameInput.sendKeys(newName);
        WebElement saveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-submit")));
        saveBtn.click();
        WebElement userNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#userBadge span[class=name]")));
        Assert.assertEquals(newName, userNameLabel.getText()); //todo fix the matter with the test
    }
}
