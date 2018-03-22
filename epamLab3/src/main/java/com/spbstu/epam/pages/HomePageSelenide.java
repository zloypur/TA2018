package com.spbstu.epam.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.spbstu.epam.utils.TestConfig;
import org.aeonbits.owner.ConfigFactory;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;


public class HomePageSelenide {
    SelenideElement profilePhoto = Selenide.$(".profile-photo");

    SelenideElement loginField = Selenide.$("#Login");

    SelenideElement passwordField = Selenide.$("#Password");

    SelenideElement submit = Selenide.$("form .btn-login");

    SelenideElement logoutBtn = Selenide.$(".logout");

    ElementsCollection images = Selenide.$$(".icons-benefit");

    ElementsCollection benefitsTesxt = Selenide.$$(".benefit-txt");

    SelenideElement mainTitle = Selenide.$(".main-title");

    SelenideElement mainText = Selenide.$(".main-txt");

    SelenideElement headerDropdownServiceButton = Selenide.$(".m-l8 .dropdown");

    SelenideElement headerDropdownServiceMenu = Selenide.$(".m-l8 .dropdown-menu");

    SelenideElement leftSideServiceButton = Selenide.$(".sub-menu");

    SelenideElement leftSideServiceMenu = Selenide.$(".sub");

    SelenideElement differentElementPageButton = Selenide.$(".dropdown-menu | [href=\"page8.htm\"]");

    public HomePageSelenide() {
        Selenide.page(this);
    }

    public void open() {
        Selenide.open(ConfigFactory.create(TestConfig.class).homePageURL());
    }

    public String currentURL(){ return WebDriverRunner.url(); }

    public String currentBrowserTitle(){
        return Selenide.title();
    }

    public void login(String login, String password) {
        profilePhoto.click();
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        submit.click();
    }

    public void checkLogIn(){
        logoutBtn.shouldBe(visible);
    }

    public void checkUserNameVisibility(){
        profilePhoto.shouldBe(visible);
    }

    public void checkUserName(String name) { profilePhoto.shouldHave(text(name));}

    public void checkImagesCount(Integer size){
        images.shouldHaveSize(size);
    }

    public void isImagesVisibility(){ images.forEach(e -> e.shouldBe(visible)); }

    public void checkTextsCount(Integer size){ benefitsTesxt.shouldHaveSize(size); }

    public void checkTextsDisplayedCorrectly(List<String> benefitsText){
        this.benefitsTesxt.shouldHave(texts(benefitsText));
    }

    public void checkMainTitleVisibility(){ mainTitle.shouldBe(visible); }

    public void checkMainTextsVisibility(){ mainText.shouldBe(visible); }

    public void checkMainTitleText(String title){ mainTitle.shouldHave(text(title)); }

    public void checkMainTextText(String text){ mainText.shouldHave(text(text)); }

    public void openHeaderServiceMenu(){
        if(headerDropdownServiceMenu.is(hidden)) {
            headerDropdownServiceButton.click();
        }
    }

    public void checkHeaderServideMenuVisibility(){
        headerDropdownServiceMenu.shouldBe(visible);
    }

    public void checkHeaderServiceMenuOptions(List<String> optionsText){
        optionsText.forEach(e -> headerDropdownServiceMenu.shouldHave(text(e)));
    }

    public void openLeftSideServiceMenu(){
        if(leftSideServiceMenu.is(hidden)) {
            leftSideServiceButton.click();
        }
    }

    public void checkLeftSideServiceMenuVisibility(){
        leftSideServiceMenu.shouldBe(visible);
    }

    public void  checkLeftSideServiceMenuOption(List<String> optionsText){
        optionsText.forEach(e -> leftSideServiceMenu.shouldHave(text(e)));
    }

    public void openDifferentElementsPage(){
        if(headerDropdownServiceMenu.is(hidden)) {
            headerDropdownServiceButton.click();
        }
        differentElementPageButton.click();
    }
}
