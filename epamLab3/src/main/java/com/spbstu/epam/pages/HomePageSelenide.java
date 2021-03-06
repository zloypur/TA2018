package com.spbstu.epam.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.spbstu.epam.utils.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;


public class HomePageSelenide {
    @FindBy(css = ".profile-photo")
    SelenideElement profilePhoto;

    @FindBy(css = "#Login")
    SelenideElement loginField;

    @FindBy(css = "#Password")
    SelenideElement passwordField;

    @FindBy(css = "form .btn-login")
    SelenideElement submit;

    @FindBy(css = ".logout")
    SelenideElement logoutBtn;

    @FindBy(css = ".icons-benefit")
    ElementsCollection images;

    @FindBy(css = ".benefit-txt")
    ElementsCollection benefitsText;

    @FindBy(css = ".main-title")
    SelenideElement mainTitle;

    @FindBy(css = ".main-txt")
    SelenideElement mainText;

    @FindBy(css = ".m-l8 .dropdown")
    SelenideElement headerDropdownServiceButton;

    @FindBy(css = ".m-l8 .dropdown-menu")
    SelenideElement headerDropdownServiceMenu;

    @FindBy(css = ".sub-menu")
    SelenideElement leftSideServiceButton;

    @FindBy(css = ".sub")
    SelenideElement leftSideServiceMenu;

    @FindBy(css = ".dropdown-menu | [href=\"page8.htm\"]")
    SelenideElement differentElementPageButton;

    @FindBy(css = ".dropdown-menu | [href=\"page4.htm\"]")
    SelenideElement datesPageButton;

    public HomePageSelenide() {
        Selenide.page(this);
    }

    public void open() {
        Selenide.open(ConfigFactory.create(TestConfig.class).homePageURL());
    }

    public String currentBrowserTitle() {
        return Selenide.title();
    }

    public void login(String login, String password) {
        profilePhoto.click();
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        submit.click();
        logoutBtn.shouldBe(visible);
    }

    public void checkLogIn() {
        logoutBtn.shouldBe(visible);
    }

    public void checkUserName(String name) {
        profilePhoto.shouldBe(visible);
        profilePhoto.shouldHave(text(name));
    }

    public void checkImages(Integer count) {
        images.shouldHaveSize(count);
        images.forEach(e -> e.shouldBe(visible));
    }

    public void checkTexts(List<String> benefitsText) {
        this.benefitsText.shouldHaveSize(benefitsText.size());
        this.benefitsText.shouldHave(texts(benefitsText));
    }

    public void checkMainTitle(String title) {
        mainTitle.shouldBe(visible);
        mainTitle.shouldHave(text(title));
    }

    public void checkMainText(String text) {
        mainText.shouldBe(visible);
        mainText.shouldHave(text(text));
    }

    public void checkHeaderServiceMenuOptions(List<String> optionsText) {
        if (headerDropdownServiceMenu.is(hidden)) {
            headerDropdownServiceButton.click();
        }
        headerDropdownServiceMenu.shouldBe(visible);
        optionsText.forEach(e -> headerDropdownServiceMenu.shouldHave(text(e)));
    }

    public void checkLeftSideServiceMenuOption(List<String> optionsText) {
        if (leftSideServiceMenu.is(hidden)) {
            leftSideServiceButton.click();
        }
        leftSideServiceMenu.shouldBe(visible);
        optionsText.forEach(e -> leftSideServiceMenu.shouldHave(text(e)));
    }

    public void openDifferentElementsPage() {
        if (headerDropdownServiceMenu.is(hidden)) {
            headerDropdownServiceButton.click();
        }
        differentElementPageButton.click();
    }

    public void openDatesPage() {
        if (headerDropdownServiceMenu.is(hidden)) {
            headerDropdownServiceButton.click();
        }
        datesPageButton.click();
    }
}
