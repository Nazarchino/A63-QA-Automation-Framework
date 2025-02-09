package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {


    @FindBy(css = "#userBadge img")
    WebElement avatar;

    @FindBy(css = "[data-testid='sidebar-create-playlist-btn']")
    WebElement addPlaylistButton;

    @FindBy(css = "[data-testid='playlist-context-menu-create-simple']")
    WebElement createPlaylist;

    @FindBy(css = "#playlists > form > input[type=text]")
    WebElement playlistNameInput;

    String playListLocator = "//section[@id='playlists']//li//a[text()='%s']";


    @FindBy(xpath = "//li[contains(@data-testid, 'playlist-context-menu-edit')]")
    WebElement editButton;

    @FindBy(css = "[data-testid='inline-playlist-name-input']")
    WebElement renamePlaylistInput;

    @FindBy(css = "#playlists li")
    List<WebElement> playLists;






    public HomePage(WebDriver existDriver) {
        super(existDriver);
    }


    public WebElement getAvatar() {
        return avatar;
    }


    public WebElement getAddPlaylistButton() {
        return addPlaylistButton;


    }


    public WebElement getCreatePlaylistButton() {
        return createPlaylist;

    }


    public WebElement getPlaylistNameInput() {
        return playlistNameInput;

    }

    public void createPlaylist(Actions actions, String testPlaylistName) throws InterruptedException {
        actions.moveToElement(getAddPlaylistButton()).perform();
        Thread.sleep(1000);
        getAddPlaylistButton().click();
        Thread.sleep(1000);
        getCreatePlaylistButton().click();
        getPlaylistNameInput().sendKeys(testPlaylistName);
        getPlaylistNameInput().sendKeys(Keys.ENTER);

    }

    public WebElement getPlaylistByName(String playlistName) {
        return findElement(By.xpath(String.format("//section[@id='playlists']//li//a[text()='%s']", playlistName)));


    }

    public void openPlaylist(String testPlaylistName) {
        getPlaylistByName(testPlaylistName).click();
    }


    public WebElement getPlayListByName(String PlayListName) {
        return waitAndFindWebElement(By.xpath(String.format(playListLocator, PlayListName)));

    }

    public void renamePlaylist(String currentPlaylistName, String newPlaylistName) throws InterruptedException {
        contextClickByElement(getPlayListByName(currentPlaylistName));
        Thread.sleep(1000);
        editButton.click();

        for (int i = 0; i < currentPlaylistName.length(); i++) {
            renamePlaylistInput.sendKeys(Keys.BACK_SPACE);
        }
        renamePlaylistInput.sendKeys(newPlaylistName);
        renamePlaylistInput.sendKeys(Keys.ENTER);
        getSuccessMessage();
    }



    public List<WebElement> getAllPlaylists() {
        return playLists;

    }



    public void createPlaylist(String playListName) throws InterruptedException {
        Thread.sleep(1000);
        getAddPlaylistButton().click();
        getCreatePlaylistButton().click();
        getPlaylistNameInput().sendKeys(playListName);
        getPlaylistNameInput().sendKeys(Keys.ENTER);
        waitUntilSuccessMessageIsDisappeared();
    }

}
