package com.spbstu.epam.utils;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static com.spbstu.epam.utils.ConfigLoader.config;

public class CustomWebDriverProvider implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        capabilities.setCapability("screenResolution", "1440x900x24");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(
                    URI.create(String.format("http://%s/wd/hub", config().remoteDriverAddress())).toURL(),
                    capabilities
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().window().setSize(new Dimension(1440, 900));
        return driver;
    }
}