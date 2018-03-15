package com.spbstu.epam;

import com.spbstu.epam.utils.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class LabTests {

    public static final String TEST_WEBSITE_URL = "https://jdi-framework.github.io/tests/index.htm";
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

    private ChromeDriver driver;

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
    }

    /*
     * Create AfterTest method which closes Chrome window
     */
    @AfterTest
    public void  afterTest(){
        driver.close();
    }

    /*
     * Create Test method which makes all the checks from the specification
     */
    @Test
    public void websiteTest(){
        /*
         * open site by url
         */
        driver.navigate().to(TEST_WEBSITE_URL);
        Assert.assertEquals(driver.getCurrentUrl(), TEST_WEBSITE_URL);

        /*
         * chrome title check
         */
        Assert.assertEquals(driver.getTitle(), TEST_WEBSITE_TITLE);

        /*
         * log in website
         */
        driver.findElementByCssSelector(".profile-photo").click();
        driver.findElementByCssSelector("#Login").sendKeys(LOGIN);
        driver.findElementByCssSelector("#Password").sendKeys(PASSWORD);
        driver.findElementByCssSelector("form .btn-login").click();
        Assert.assertTrue(driver.findElementByCssSelector(".logout").isDisplayed(),
                "Logout button isn\'t displayed. Login wasn\'t successful.");

        /*
         * user name check
         */
        Assert.assertTrue(0 == driver.findElementByCssSelector(".profile-photo").
                findElements(By.cssSelector(".hidden")).size(), "User name isn\'t displayed");
        Assert.assertEquals(driver.findElementByCssSelector(".profile-photo").
                getAttribute("innerText"), USERNAME);

        /*
         * chrome title second check
         */
        Assert.assertEquals(driver.getTitle(), TEST_WEBSITE_TITLE);

        /*
         * presence of images on the home page check
         */
        List<WebElement> images = driver.findElementsByCssSelector(".icons-benefit");
        Assert.assertEquals(images.size(), IMAGES_COUNT,
                String.format("There are %d images on the page which is lesser then required (%d)",images.size(), IMAGES_COUNT));
        images.forEach(e -> Assert.assertTrue(e.isDisplayed(),
                String.format("\"%s\" image isn't displayed.", e.getAttribute("className"))));

        /*
         * presence and correctness of under images texts on the home page check
         */
        List<WebElement> texts = driver.findElementsByCssSelector(".benefit-txt");
        Assert.assertEquals(texts.size(), TEXTS_COUNT,
                String.format("There are %d texts on the page which is lesser then required (%d)",texts.size(), TEXTS_COUNT));
        texts.forEach(e -> Assert.assertTrue(BENEFITS_TEXT.contains(e.getText()),
                String.format("No such benefit text:\"%s\"", e.getText())));

        /*
         * main header and text below check
         */
        WebElement mainHeaderText = driver.findElementByCssSelector(".main-title");
        WebElement headerText = driver.findElementByCssSelector(".main-txt");
        Assert.assertTrue(mainHeaderText.isDisplayed(), "Main header text isn\'t displayed.");
        Assert.assertTrue(headerText.isDisplayed(), "Header text isn\'t displayed.");
        Assert.assertEquals(mainHeaderText.getAttribute("innerText"), MAIN_HEADER_TEXT);
        Assert.assertEquals(headerText.getAttribute("innerText"), HEADER_TEXT);
    }
}
