import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {

    LoginPage loginPage = null;

    HomePage homePage = null;


    @Test(description = "Check if user login with correct credentials")
    public void loginTest() {
        loginPage = new LoginPage(getDriver());
        loginPage.login("nazar@testpro.io", "Pomidor2115");
        homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.getAvatar().isDisplayed()); //true
    }

    @Test(dataProvider = "incorrectCredentials", dataProviderClass = DataProviderCredentials.class)
    public void loginWithEmptyCredentials(String email, String password) {
        loginPage = new LoginPage(getDriver());
        loginPage.login(email, password);
        Assert.assertTrue(loginPage.getLogo().isDisplayed());
        
    }



}

