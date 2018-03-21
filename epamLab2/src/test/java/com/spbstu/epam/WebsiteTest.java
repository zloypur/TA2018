package com.spbstu.epam;

import com.spbstu.epam.utils.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static com.spbstu.epam.enums.HOME_PAGE_DATA.*;

public class WebsiteTest {


    private WebDriver driver;

    /*
     * Create BeforeSuite method which get properties from test\resources\test.properties
     */
    @BeforeSuite
    public void beforeSuite (){
        TestConfig config = ConfigFactory.create(TestConfig.class);
        System.setProperty("webdriver.chrome.driver", config.pathToDriver());
    }

    /*
     * Create BeforeTest method which opens Chrome window, maximaized it, navigates to the test website
     */
    @BeforeTest
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        EpamTestWebsite.init(driver);
    }

    /*
     * Create AfterTest method which closes Chrome window
     */
    @AfterTest
    public void  afterTest(){
        driver.close();
    }

    /*
     * Create Test method which makes all the checks from the specification using pageObjects
     */
    @Test
    public void websiteTest(){
        /*
         * open site by url
         */
        EpamTestWebsite.homePage.open();
        Assert.assertEquals(EpamTestWebsite.homePage.currentURL(), TEST_WEBSITE_URL.getValue());

        /*
         * chrome title check
         */
        Assert.assertEquals(EpamTestWebsite.homePage.currentBrowserTitle(), TEST_WEBSITE_TITLE.getValue());

        /*
         * log in website
         */
        EpamTestWebsite.homePage.login((String)LOGIN.getValue(), (String)PASSWORD.getValue());
        Assert.assertTrue(EpamTestWebsite.homePage.isLoggedIn(),
                "Logout button isn\'t displayed. Login wasn\'t successful.");

        /*
         * user name check
         */
        Assert.assertTrue(EpamTestWebsite.homePage.isUserNameDisplayed(),
                "User name isn\'t displayed");
        Assert.assertEquals(EpamTestWebsite.homePage.userName(), USERNAME.getValue());

        /*
         * chrome title second check
         */
        Assert.assertEquals(EpamTestWebsite.homePage.currentBrowserTitle(), TEST_WEBSITE_TITLE.getValue());

        /*
         * presence of images on the home page check
         */
        Assert.assertEquals(EpamTestWebsite.homePage.imagesCount(), IMAGES_COUNT.getValue(),
                String.format("There are %d images on the page which is lesser then required (%d)",
                        EpamTestWebsite.homePage.imagesCount(), IMAGES_COUNT.getValue()));
        Assert.assertTrue(EpamTestWebsite.homePage.isImagesDisplayed(), "Not all images are displayed.");

        /*
         * presence and correctness of under images texts on the home page check
         */
        Assert.assertEquals(EpamTestWebsite.homePage.textsCount(), TEXTS_COUNT.getValue(),
                String.format("There are %d texts on the page which is lesser then required (%d)",
                        EpamTestWebsite.homePage.textsCount(), TEXTS_COUNT.getValue()));
        Assert.assertTrue(EpamTestWebsite.homePage.isTextsDisplayedCorrectly((List<String>)BENEFITS_TEXT.getValue()),
                "Not all texts are displayed correctly.");

        /*
         * main header and text below check
         */
        Assert.assertTrue(EpamTestWebsite.homePage.isMainTitleDisplayed(), "Main header text isn\'t displayed.");
        Assert.assertTrue(EpamTestWebsite.homePage.isMainTextsDisplayed(), "Header text isn\'t displayed.");
        Assert.assertEquals(EpamTestWebsite.homePage.mainTitleText(), MAIN_HEADER_TEXT.getValue());
        Assert.assertEquals(EpamTestWebsite.homePage.mainTextText(), HEADER_TEXT.getValue());
    }
}
