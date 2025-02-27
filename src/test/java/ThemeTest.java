import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ThemeTest extends BaseTest {

    @Test
    public void changeThemeTest() throws InterruptedException {
        String themeName = "Violet";
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        HomePage homePage = new HomePage(getDriver());
        homePage.getAvatar().click();
        ProfilePage profilePage = new ProfilePage(getDriver());
        profilePage.chooseThemByName(themeName);
        Assert.assertTrue(profilePage.isThemeSelected(themeName));
    }
}