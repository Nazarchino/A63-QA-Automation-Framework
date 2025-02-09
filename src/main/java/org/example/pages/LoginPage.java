package org.example.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[type='email']")
    WebElement emailInput;
    @FindBy(css = "[type='password']")
    WebElement passwordInput;
    @FindBy(css = "[type='submit']")
    WebElement loginButton;
    @FindBy(css = ".logo")
    WebElement logo;



    public LoginPage(WebDriver existDriver) {
        super(existDriver);
    }


    public void login(String email, String password){
        provideEmail(email).providePassword(password).clickSubmit();

    }

    private LoginPage provideEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }

    private LoginPage providePassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    private void clickSubmit(){
        loginButton.click();
    }




    public WebElement getLogo() {

        return logo;


    }


}
