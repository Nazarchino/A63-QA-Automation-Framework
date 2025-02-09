package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlaylistPage extends BasePage{


    @FindBy(css = "[class='del btn-delete-playlist']")
    WebElement deletePlaylist;


    public PlaylistPage(WebDriver existDriver) {
        super(existDriver);
    }

    public WebElement getDeletePlaylistButton(WebDriverWait wait){
        return deletePlaylist;

    }


    public void deletePlaylist(WebDriverWait wait){
        getDeletePlaylistButton(wait).click();

    }

}
