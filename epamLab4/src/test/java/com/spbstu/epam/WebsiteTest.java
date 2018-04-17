package com.spbstu.epam;

import com.codeborne.selenide.Configuration;
import com.spbstu.epam.allure.AllureAttachmentListener;
import com.spbstu.epam.utils.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.util.Arrays;
import java.util.List;

import static com.spbstu.epam.enums.HOME_PAGE_DATA.*;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_CHECKBOX_TEXT.LEFT_CHECKBOX_TEXT;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_CHECKBOX_TEXT.MID_RIGHT_CHECKBOX_TEXT;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_DROPDOWN_TEXT.DROPDOWN_TEXT_4;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_RADIO_TEXT.RIGHT_RADIO_TEXT;

@Listeners(AllureAttachmentListener.class)
@Features({"Selenide Test Suite"})
@Stories({"Website test"})
public class WebsiteTest {

    /*
     * Create BeforeSuite method which get properties from test\resources\test.properties and configuring Selenide
     */
    @BeforeSuite
    public void beforeSuite() {
        //TestConfig config = ConfigFactory.create(TestConfig.class);
        //System.setProperty("webdriver.chrome.driver", config.pathToDriver());

        Configuration.browser = "CHROME";
        Configuration.timeout = 4000;
    }

    /*
     * Create BeforeTest method which opens Chrome window, maximaized it, navigates to the test website
     */
    @BeforeTest
    public void beforeTest() {
        EpamTestWebsiteSelenide.init();
    }

    /*
     * Create Test method which makes all the checks from the specification using pageObjects
     */
    @Test
    public void websiteTest() {
        /*
         * open site by url
         */
        EpamTestWebsiteSelenide.homePageSelenide.open();

        /*
         * log in website
         */
        EpamTestWebsiteSelenide.homePageSelenide.login(LOGIN.getValue().toString(), PASSWORD.getValue().toString());

        /*
         * user name check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkUserName(USERNAME.getValue().toString());

        /*
         * presence of images on the home page check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkImages((Integer) IMAGES_COUNT.getValue());

        /*
         * presence and correctness of under images texts on the home page check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkTexts((List<String>) BENEFITS_TEXT.getValue());

        /*
         * main header and text below check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkMainTitle((String) MAIN_HEADER_TEXT.getValue());
        EpamTestWebsiteSelenide.homePageSelenide.checkMainText((String) HEADER_TEXT.getValue());

        /*
         * header service menu visibility and options check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkHeaderServiceMenuOptions((List<String>) HEADER_DROPDOWN_SERVICE_MENU_OPTIONS_TEXT.getValue());

        /*
         * left side service menu visibility and options check
         */
        EpamTestWebsiteSelenide.homePageSelenide.checkLeftSideServiceMenuOption((List<String>) LEFT_SIDE_SERVICE_MENU_OPTIONS_TEXT.getValue());

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
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setCheckboxSelected(LEFT_CHECKBOX_TEXT.getValue());
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setCheckboxSelected(MID_RIGHT_CHECKBOX_TEXT.getValue());
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setRadioSelected(RIGHT_RADIO_TEXT.getValue());
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setDropdownValue(DROPDOWN_TEXT_4.getValue());

        /*
         * checks that logs are coinciding with performed actions
         */
        EpamTestWebsiteSelenide.differentElementsPageSelenide.checkLogs((List<String>) Arrays.asList(DROPDOWN_TEXT_4.getValue(),
                RIGHT_RADIO_TEXT.getValue(), MID_RIGHT_CHECKBOX_TEXT.getValue(), LEFT_CHECKBOX_TEXT.getValue()));

        /*
         * unselect required elements
         */
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setCheckboxUnselected(LEFT_CHECKBOX_TEXT.getValue());
        EpamTestWebsiteSelenide.differentElementsPageSelenide.setCheckboxUnselected(MID_RIGHT_CHECKBOX_TEXT.getValue());

        /*
         * check that logs are coinciding with performed actions
         */
        EpamTestWebsiteSelenide.differentElementsPageSelenide.checkLogs(Arrays.asList(LEFT_CHECKBOX_TEXT.getValue(),
                MID_RIGHT_CHECKBOX_TEXT.getValue()));
    }
}
