package com.spbstu.epam;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.TestConfig;

import java.util.Arrays;
import java.util.List;

public class WebsiteTest {

    public static final String TEST_WEBSITE_URL = ConfigFactory.create(TestConfig.class).homePageURL();
    public static final String TEST_WEBSITE_TITLE = "Index Page";
    public static final String LOGIN = "epam";
    public static final String PASSWORD = "1234";
    public static final String USERNAME = " PITER CHAILOVSKII";
    public static final int IMAGES_COUNT = 4;
    public static final int TEXTS_COUNT = 4;
    public static final List<String> BENEFITS_TEXT = Arrays.asList("To include good practices\nand ideas from successful\nEPAM projec",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
    public static final String MAIN_HEADER_TEXT = "EPAM FRAMEWORK WISHES…";
    public static final String HEADER_TEXT = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT," +
            " SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, " +
            "QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT " +
            "DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";

    private WebDriver driver;

    /*
     * Create BeforeSuite method which get properties from test\resources\test.properties
     */
    @BeforeSuite
    public void beforeSiute (){
        System.setProperty("webdriver.chrome.driver", ConfigFactory.create(TestConfig.class).pathToDriver());
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
        Assert.assertEquals(EpamTestWebsite.homePage.currentURL(), TEST_WEBSITE_URL);

        /*
         * chrome title check
         */
        Assert.assertEquals(EpamTestWebsite.homePage.currentBrowserTitle(), TEST_WEBSITE_TITLE);

        /*
         * log in website
         */
        EpamTestWebsite.homePage.login(LOGIN, PASSWORD);
        Assert.assertTrue(EpamTestWebsite.homePage.isLoggedIn(),
                "Logout button isn\'t displayed. Login wasn\'t successful.");

        /*
         * user name check
         */
        Assert.assertTrue(EpamTestWebsite.homePage.isUserNameDisplayed(),
                "User name isn\'t displayed");
        Assert.assertEquals(EpamTestWebsite.homePage.userName(), USERNAME);

        /*
         * chrome title second check
         */
        Assert.assertEquals(EpamTestWebsite.homePage.currentBrowserTitle(), TEST_WEBSITE_TITLE);

        /*
         * presence of images on the home page check
         */
        Assert.assertEquals(EpamTestWebsite.homePage.imagesCount(), IMAGES_COUNT,
                String.format("There are %d images on the page which is lesser then required (%d)",EpamTestWebsite.homePage.imagesCount(), IMAGES_COUNT));
        Assert.assertTrue(EpamTestWebsite.homePage.isImagesDisplayed(), "Not all images are displayed.");

        /*
         * presence and correctness of under images texts on the home page check
         */
        Assert.assertEquals(EpamTestWebsite.homePage.textsCount(), TEXTS_COUNT,
                String.format("There are %d texts on the page which is lesser then required (%d)",
                        EpamTestWebsite.homePage.textsCount(), TEXTS_COUNT));
        Assert.assertTrue(EpamTestWebsite.homePage.isTextsDisplayedCorrectly(BENEFITS_TEXT), "Not all texts are displayed correctly.");

        /*
         * main header and text below check
         */
        Assert.assertTrue(EpamTestWebsite.homePage.isMainTitleDisplayed(), "Main header text isn\'t displayed.");
        Assert.assertTrue(EpamTestWebsite.homePage.isMainTextsDisplayed(), "Header text isn\'t displayed.");
        Assert.assertEquals(EpamTestWebsite.homePage.mainTitleText(), MAIN_HEADER_TEXT);
        Assert.assertEquals(EpamTestWebsite.homePage.mainTextText(), HEADER_TEXT);
    }
}
