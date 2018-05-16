package com.spbstu.epam;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.spbstu.epam.entities.Data;
import com.spbstu.epam.site.EpamTestWebsiteSelenide;
import com.spbstu.epam.utils.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.spbstu.epam.enums.HOME_PAGE_DATA.LOGIN;
import static com.spbstu.epam.enums.HOME_PAGE_DATA.PASSWORD;
import static com.spbstu.epam.site.EpamTestWebsiteSelenide.*;
import static com.spbstu.epam.utils.ResourceLoader.getData;

public class WebsiteTest extends TestNGBase {

    @DataProvider(name = "dataProvider")
    public Object[] createData() {
        return new Object[]{
                getData("data_1"),
                getData("data_2"),
                getData("data_3"),
                getData("data_4"),
                getData("data_5")
        };
    }

    /*
     * Create BeforeSuite method which get properties from test\resources\test.properties and configuring Selenide
     */
    @BeforeSuite
    public void beforeSuite() {
        driverFactory.setDriverPath(ConfigFactory.create(TestConfig.class).driverFolder());
        WebSite.init(EpamTestWebsiteSelenide.class);
        logger.info("Run Tests");
        driverFactory.getDriver();//start Chrome
    }

    /*
     * Create BeforeTest method which opens home page log in and navigates to metals and colors page
     */
    @BeforeTest
    public void beforeTest() {
        /*
         * open site home page
         */
        homePageJDI.open();
        homePageJDI.checkOpened();

        /*
         * log in website
         */
        login(LOGIN.getValue().toString(), PASSWORD.getValue().toString());

        /*
         * open site metals and colors page
         */
        openMetalsAndColorsPage();
        metalsAndColorsPageJDI.checkOpened();
    }

    /*
     * Create Test method which makes all the checks from the specification using pageObjects
     */
    @Test(dataProvider = "dataProvider")
    public void websiteTest(Data data) {
        System.out.println(data.toString());

        metalsAndColorsPageJDI.fillMetalsAndColorsForm(data);
    }


}
