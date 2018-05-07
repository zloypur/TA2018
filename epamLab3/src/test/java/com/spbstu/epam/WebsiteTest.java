package com.spbstu.epam;

import com.codeborne.selenide.Configuration;
import com.spbstu.epam.utils.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.spbstu.epam.EpamTestWebsiteSelenide.*;
import static com.spbstu.epam.enums.HOME_PAGE_DATA.*;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_CHECKBOX_TEXT.LEFT_CHECKBOX_TEXT;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_CHECKBOX_TEXT.MID_RIGHT_CHECKBOX_TEXT;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_DROPDOWN_TEXT.DROPDOWN_TEXT_4;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_RADIO_TEXT.RIGHT_RADIO_TEXT;

public class WebsiteTest {

    /*
     * Create BeforeSuite method which get properties from test\resources\test.properties and configuring Selenide
     */
    @BeforeSuite
    public void beforeSuite() {
        TestConfig config = ConfigFactory.create(TestConfig.class);
        System.setProperty("webdriver.chrome.driver", config.pathToDriver());

        Configuration.browser = "CHROME";
        Configuration.timeout = 4000;
    }

    /*
     * Create BeforeTest method which opens Chrome window, maximaized it, navigates to the test website
     */
    @BeforeTest
    public void beforeTest() {
        init();
    }

    /*
     * Create Test method which makes all the checks from the specification using pageObjects
     */
    @Test(enabled = false)
    public void testCase1() {
        /*
         * open site by url
         */
        homePageSelenide.open();

        /*
         * log in website
         */
        homePageSelenide.login(LOGIN.getValue().toString(), PASSWORD.getValue().toString());

        /*
         * user name check
         */
        homePageSelenide.checkUserName(USERNAME.getValue().toString());

        /*
         * presence of images on the home page check
         */
        homePageSelenide.checkImages((Integer) IMAGES_COUNT.getValue());

        /*
         * presence and correctness of under images texts on the home page check
         */
        homePageSelenide.checkTexts((List<String>) BENEFITS_TEXT.getValue());

        /*
         * main header and text below check
         */
        homePageSelenide.checkMainTitle((String) MAIN_HEADER_TEXT.getValue());
        homePageSelenide.checkMainText((String) HEADER_TEXT.getValue());

        /*
         * header service menu visibility and options check
         */
        homePageSelenide.checkHeaderServiceMenuOptions((List<String>) HEADER_DROPDOWN_SERVICE_MENU_OPTIONS_TEXT.getValue());

        /*
         * left side service menu visibility and options check
         */
        homePageSelenide.checkLeftSideServiceMenuOption((List<String>) LEFT_SIDE_SERVICE_MENU_OPTIONS_TEXT.getValue());

        /*
         * open other page through header menu Service -> Different Elements
         */
        homePageSelenide.openDifferentElementsPage();

        /*
         * checks presence of all needed elements on the Different Elements page
         */
        differentElementsPageSelenide.checkPageElements();

        /*
         * select required elements on the Different Elements page
         */
        differentElementsPageSelenide.setCheckboxSelected(LEFT_CHECKBOX_TEXT.getValue());
        differentElementsPageSelenide.setCheckboxSelected(MID_RIGHT_CHECKBOX_TEXT.getValue());
        differentElementsPageSelenide.setRadioSelected(RIGHT_RADIO_TEXT.getValue());
        differentElementsPageSelenide.setDropdownValue(DROPDOWN_TEXT_4.getValue());

        /*
         * checks that logs are coinciding with performed actions
         */
        differentElementsPageSelenide.checkLogs((List<String>) Arrays.asList(DROPDOWN_TEXT_4.getValue(),
                RIGHT_RADIO_TEXT.getValue(), MID_RIGHT_CHECKBOX_TEXT.getValue(), LEFT_CHECKBOX_TEXT.getValue()));

        /*
         * unselect required elements
         */
        differentElementsPageSelenide.setCheckboxUnselected(LEFT_CHECKBOX_TEXT.getValue());
        differentElementsPageSelenide.setCheckboxUnselected(MID_RIGHT_CHECKBOX_TEXT.getValue());

        /*
         * check that logs are coinciding with performed actions
         */
        differentElementsPageSelenide.checkLogs(Arrays.asList(LEFT_CHECKBOX_TEXT.getValue(),
                MID_RIGHT_CHECKBOX_TEXT.getValue()));
    }

    @Test
    public void testCase2() {
         /*
         * open site by url
         */
        homePageSelenide.open();

        /*
         * log in website
         */
        homePageSelenide.login(LOGIN.getValue().toString(), PASSWORD.getValue().toString());

        /*
         * user name check
         */
        homePageSelenide.checkUserName(USERNAME.getValue().toString());

        homePageSelenide.openDatesPage();

        datesPageSelenide.setSliderRange(0, 100);
        datesPageSelenide.setSliderRange(0, 0);
        datesPageSelenide.setSliderRange(100, 100);
        datesPageSelenide.setSliderRange(30, 70);
    }
}
