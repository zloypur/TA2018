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

import java.util.List;

public class LabTests {

    public static final String TEST_SITE = "https://jdi-framework.github.io/tests/index.htm";
    public static final String TEST_SITE_TITLE = "Index Page";
    public static final String LOGIN = "epam";
    public static final String PASSWORD = "1234";
    public static final String USERNAME = " PITER CHAILOVSKII";
    public static final String USERNAME_CLASS_VALUE = "hidden";
    public static final int IMAGES_COUNT = 4;
    public static final int TEXTS_COUNT = 4;
    public static final String[] TEXTS = {"To include good practices\nand ideas from successful\nEPAM projec",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"};
    public static final String MAIN_HEADER_TEXT = "EPAM FRAMEWORK WISHES…";
    public static final String HEADER_TEXT = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
            "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT " +
            "DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";

    private TestConfig config;
    private ChromeDriver driver;

    //1 Create BeforeSuite method which get properties from test\resources\test.properties
    @BeforeSuite
    public void beforeSiute (){
        config = ConfigFactory.create(TestConfig.class);
        System.setProperty("webdriver.chrome.driver", config.pathToDriver());
    }

    //2 Create BeforeTest method which opens Chrome window, maximaized it, navigates to the test site
    @BeforeTest
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(TEST_SITE);
    }

    //3 Create AfterTest method which closes Chrome window
    @AfterTest
    public void  afterTest(){
        driver.close();
    }

    //4 Create a new test which opens site by url
    @Test
    public void openTestSiteByUrl() {
        Assert.assertEquals(driver.getCurrentUrl(), TEST_SITE);//, String.format("Actual: %s but expected: %s", driver.getCurrentUrl(), TEST_SITE));
    }

    //5 Create a new test which gets title of Chrome
    @Test
    public void browserTitleCheck(){
        Assert.assertEquals(driver.getTitle(), TEST_SITE_TITLE);
    }

    //6 Create a new test which log in the test website
    @Test
    public void testSiteLogIn(){
        driver.findElementByCssSelector("[href=\"#\"]").click();
        driver.findElement(By.id("Login")).sendKeys(LOGIN);
        driver.findElement(By.id("Password")).sendKeys(PASSWORD);
        driver.findElementByCssSelector("[type=\"submit\"]").click();
        Assert.assertTrue(driver.findElementByClassName("logout").isDisplayed(), "Logout button isn\'t displayed. Login wasn\'t successful.");
    }

    //7 Create a new test which checks user name
    @Test
    public void userNameCheck(){
        WebElement userName = driver.findElementByClassName("profile-photo");
        Assert.assertNotEquals(userName.findElement(By.tagName("span")).getAttribute("class"), USERNAME_CLASS_VALUE, String.format("User name isn\'t displayed because web element class value is \"%s\"", USERNAME_CLASS_VALUE));
        Assert.assertEquals(userName.getAttribute("innerText"), USERNAME);
    }

    //8 Create a new test which checks title of Chrome
    @Test
    public void browserTitleSecondCheck(){
        Assert.assertEquals(driver.getTitle(), TEST_SITE_TITLE);
    }

    //9 Create a new test which checks presence of images on the home page
    @Test
    public void homePageImageDisplayTest(){
        List<WebElement> images = driver.findElementsByClassName("benefit-icon");
        Assert.assertEquals(images.size(), IMAGES_COUNT, String.format("There are %d images on the page which is lesser then required (%d)",images.size(), IMAGES_COUNT));
        for(WebElement e:images)
            Assert.assertTrue(e.isDisplayed(), String.format("%s image isn't displayed.", e.findElement(By.tagName("span")).getAttribute("class")));
    }

    //10 Create a new test which checks presence and correctness of under images texts on the home page
    @Test
    public void homePageUnderImageTextTest(){
        List<WebElement> texts = driver.findElementsByClassName("benefit-txt");
        Assert.assertEquals(texts.size(), TEXTS_COUNT, String.format("There are %d texts on the page which is lesser then required (%d)",texts.size(), TEXTS_COUNT));
        for(WebElement e:texts)
            Assert.assertTrue(e.isDisplayed(), String.format("This text isn't displayed: \"%s\"", e.getAttribute("innerText")));
        for(int i = 0; i < texts.size();i++)
            Assert.assertEquals(texts.get(i).getAttribute("innerText"), TEXTS[i]);
    }

    //11
    @Test
    public void homePageMainHeaderTextTest(){
        WebElement mainHeaderText = driver.findElementByClassName("main-title");
        Assert.assertTrue(mainHeaderText.isDisplayed(), String.format("Main header text isn\'t displayed."));
        Assert.assertEquals(mainHeaderText.getAttribute("innerText"), MAIN_HEADER_TEXT);
        WebElement headerText = driver.findElementByClassName("main-txt");
        Assert.assertTrue(headerText.isDisplayed(), String.format("Header text isn\'t displayed."));
        Assert.assertEquals(headerText.getAttribute("innerText"), HEADER_TEXT);
    }
}
