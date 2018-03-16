package com.spbstu.epam;

import com.spbstu.epam.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class EpamTestWebsite
{
    public static HomePage homePage;

    public static void init(WebDriver driver) {
        homePage = PageFactory.initElements(driver, HomePage.class);
    }
}
