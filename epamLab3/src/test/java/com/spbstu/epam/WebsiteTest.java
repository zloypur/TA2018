package com.spbstu.epam;

import com.codeborne.selenide.Configuration;
import com.spbstu.epam.utils.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.spbstu.epam.enums.HOME_PAGE_DATA.*;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENT_PAGE_CHECKBOX_TEXT.*;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENT_PAGE_DROPDOWN_TEXT.*;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENT_PAGE_RADIO_TEXT.*;

public class WebsiteTest {

    /*
     * Create BeforeSuite method which get properties from test\resources\test.properties and configuring Selenide
     */
    @BeforeSuite
    public void beforeSuite (){
        TestConfig config = ConfigFactory.create(TestConfig.class);
        System.setProperty("webdriver.chrome.driver", config.pathToDriver());

        Configuration.browser = "CHROME";
        Configuration.timeout = 4000;
    }

    /*
     * Create BeforeTest method which opens Chrome window, maximaized it, navigates to the test website
     */
    @BeforeTest
    public void beforeTest(){
        EpamTestWebsiteSelenide.init();
    }

    /*
     * Create Test method which makes all the checks from the specification using pageObjects
     */
    @Test
    public void websiteTest(){
        /*
         * open site by url
         */
        EpamTestWebsiteSelenide.homePageSelenide.open();
        Assert.assertEquals(EpamTestWebsiteSelenide.homePageSelenide.currentURL(), TEST_WEBSITE_URL.getValue());

        /*
         * chrome title check
         */
        Assert.assertEquals(EpamTestWebsiteSelenide.homePageSelenide.currentBrowserTitle(), TEST_WEBSITE_TITLE.getValue());

        /*
         * log in website
         */
        EpamTestWebsiteSelenide.homePageSelenide.login(LOGIN.getValue().toString(), PASSWORD.getValue().toString());

        /*
         * user name check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkUserNameVisibility();
        EpamTestWebsiteSelenide.homePageSelenide.checkUserName(USERNAME.getValue().toString());

        /*
         * chrome title second check
         */
        Assert.assertEquals(EpamTestWebsiteSelenide.homePageSelenide.currentBrowserTitle(), TEST_WEBSITE_TITLE.getValue());

        /*
         * presence of images on the home page check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkImagesCount((Integer)IMAGES_COUNT.getValue());
        EpamTestWebsiteSelenide.homePageSelenide.isImagesVisibility();

        /*
         * presence and correctness of under images texts on the home page check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkTextsCount((Integer)TEXTS_COUNT.getValue());
        EpamTestWebsiteSelenide.homePageSelenide.checkTextsDisplayedCorrectly((List<String>)BENEFITS_TEXT.getValue());

        /*
         * main header and text below check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkMainTitleVisibility();
        EpamTestWebsiteSelenide.homePageSelenide.checkMainTextsVisibility();
        EpamTestWebsiteSelenide.homePageSelenide.checkMainTitleText(MAIN_HEADER_TEXT.getValue().toString());
        EpamTestWebsiteSelenide.homePageSelenide.checkMainTextText(HEADER_TEXT.getValue().toString());

        /*
         * header service menu visibility and options check
         */
        EpamTestWebsiteSelenide.homePageSelenide.openHeaderServiceMenu();
        EpamTestWebsiteSelenide.homePageSelenide.checkHeaderServideMenuVisibility();
        EpamTestWebsiteSelenide.homePageSelenide.checkHeaderServiceMenuOptions(
                (List<String>)HEADER_DROPDOWN_SERVICE_MENU_OPTIONS_TEXT.getValue());

        /*
         * left side service menu visibility and options check
         */
        EpamTestWebsiteSelenide.homePageSelenide.openLeftSideServiceMenu();
        EpamTestWebsiteSelenide.homePageSelenide.checkLeftSideServiceMenuVisibility();
        EpamTestWebsiteSelenide.homePageSelenide.checkLeftSideServiceMenuOption(
                (List<String>)LEFT_SIDE_SERVICE_MENU_OPTIONS_TEXT.getValue());

        /*
         * open other page through header menu Service -> Different Elements
         */
        EpamTestWebsiteSelenide.homePageSelenide.openDifferentElementsPage();

        /*
         * checks presence of all needed elements on the Different Elements page
         */
        EpamTestWebsiteSelenide.differentElementsPageSelenide.checkPageElements();

        /*
         * select required elements on the Different Elements page
         */
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setCheckboxSelected(LEFT_CHECKBOX_TEXT.getValue().toString());
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setCheckboxSelected(MID_RIGHT_CHECKBOX_TEXT.getValue().toString());
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setRadioSelected(RIGHT_RADIO_TEXT.getValue().toString());
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setDropdownValue(DROPDOWN_TEXT_4.getValue().toString());

        EpamTestWebsiteSelenide.differentElementsPageSelenide.checkLogs(
                (List<String>) Arrays.asList(DROPDOWN_TEXT_4.getValue().toString(),
                        RIGHT_RADIO_TEXT.getValue().toString(), MID_RIGHT_CHECKBOX_TEXT.getValue().toString(),
                LEFT_CHECKBOX_TEXT.getValue().toString()));

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
