
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationNavigationTest extends BaseTest {

    @Test(groups = "Smoke")
    public void registrationNavigation() {

        WebElement registrationLink = getDriver().findElement(By.cssSelector("[href='registration']"));
        registrationLink.click();
        WebElement registrationText = getDriver().findElement(By.xpath("//h2 [contains (text(), 'Register new account or')]"));
        Assert.assertTrue(registrationText.isDisplayed());

    }
}
