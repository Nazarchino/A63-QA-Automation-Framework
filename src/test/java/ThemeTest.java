import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ThemeTest extends BaseTest {
    @Test
    public void changeThemeTest() throws InterruptedException {
        String themeName = "Classic";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        HomePage homePage = new HomePage(driver);
        Thread.sleep(1000);
        homePage.getAvatar().click();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.chooseThemesByNAME(themeName);
        Assert.assertTrue(profilePage.isThemeSelected(themeName));

    }




}
