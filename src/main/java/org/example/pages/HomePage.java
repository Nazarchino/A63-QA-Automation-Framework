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
    @FindBy(css = "#playlists i[role='button']")
    WebElement addPlayListButton;
    @FindBy(css = "[data-testid='playlist-context-menu-create-simple']")
    WebElement createPlayListButton;
    @FindBy(css = "[name='create-simple-playlist-form']>input")
    WebElement playListNameInput;
    String playListLocator = "//section[@id='playlists']//li/a[text()='%s']";
    @FindBy(xpath = "//li[contains(@data-testid, 'playlist-context-menu-edit')]")
    WebElement editButton;
    @FindBy(xpath = "//ul/li//input[@type='text']")
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
        return addPlayListButton;
    }

    public WebElement getCreatePlaylistButton() {
        return createPlayListButton;
    }

    public WebElement getPlaylistNameInput() {
        return playListNameInput;
    }

    public void createPlaylist(Actions actions,String playlistName) throws InterruptedException {
        actions.moveToElement(getAddPlaylistButton()).perform();
        getAddPlaylistButton().click();
        Thread.sleep(3000);
        getCreatePlaylistButton().click();
        getPlaylistNameInput().sendKeys(playlistName);
        getPlaylistNameInput().sendKeys(Keys.ENTER);
    }

    public WebElement getPlaylistByName(String playlistName) {
        return findElement(By.xpath(String.format("//section[@id='playlists']//li/a[text()='%s']", playlistName)));
    }

    public void openPlayList(String playlistName) {
        getPlaylistByName(playlistName).click();
    }

    public WebElement getPlayListByName(String playListName) {
        return waitAndFindWebElement(By.xpath(String.format(playListLocator, playListName)));
    }

    public void renamePlayList(String currentPlayListName, String newPlayListName) throws InterruptedException {
        contextClickByElement(getPlayListByName(currentPlayListName));
        Thread.sleep(2000);
        editButton.click();
        for (int i = 0; i < currentPlayListName.length(); i++) {
            renamePlaylistInput.sendKeys(Keys.BACK_SPACE);
        }
        renamePlaylistInput.sendKeys(newPlayListName);
        renamePlaylistInput.sendKeys(Keys.ENTER);
        getSuccessMessage();
    }

    public List<WebElement> getAllPlayLists() {
        return playLists;
    }

    public void createPlayList(String playListName) throws InterruptedException {
        Thread.sleep(3000);
        getAddPlaylistButton().click();
        getCreatePlaylistButton().click();
        getPlaylistNameInput().sendKeys(playListName);
        getPlaylistNameInput().sendKeys(Keys.ENTER);
        waitUntilSuccessMessageIsDisappear();
    }
}