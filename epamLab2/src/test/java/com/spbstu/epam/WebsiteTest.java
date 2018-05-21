package com.spbstu.epam;

<<<<<<< HEAD
import com.sun.xml.internal.bind.v2.TODO;
=======
import com.spbstu.epam.utils.TestConfig;
>>>>>>> 614a8218a7ea2631bb2960c6ad2cbbbcc0379fe3
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

<<<<<<< HEAD
// TODO you have to format your code and remove useless imports
public class WebsiteTest {

    // TODO it will be better if you create ENUM(in a different file) for store this sort of data
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
    // !TODO
=======
import static com.spbstu.epam.enums.HOME_PAGE_DATA.*;

public class WebsiteTest {

>>>>>>> 614a8218a7ea2631bb2960c6ad2cbbbcc0379fe3

    private WebDriver driver;

    /*
     * Create BeforeSuite method which get properties from test\resources\test.properties
     */
    @BeforeSuite
<<<<<<< HEAD
    public void beforeSuite (){ // <--
        // TODO this not really good idea couse in case if you have more than one property you will recreate the whole TestConfig...
        System.setProperty("webdriver.chrome.driver", ConfigFactory.create(TestConfig.class).pathToDriver());
=======
    public void beforeSuite (){
        TestConfig config = ConfigFactory.create(TestConfig.class);
        System.setProperty("webdriver.chrome.driver", config.pathToDriver());
>>>>>>> 614a8218a7ea2631bb2960c6ad2cbbbcc0379fe3
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
<<<<<<< HEAD
        EpamTestWebsite.homePage.login(LOGIN, PASSWORD);
        // TODO what is the reason why you are using shielding character ? From my point of view it will work pretty good as is...
=======
        EpamTestWebsite.homePage.login((String)LOGIN.getValue(), (String)PASSWORD.getValue());
>>>>>>> 614a8218a7ea2631bb2960c6ad2cbbbcc0379fe3
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
