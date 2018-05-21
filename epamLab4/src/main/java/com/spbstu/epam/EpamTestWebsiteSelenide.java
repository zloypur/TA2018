package com.spbstu.epam;

import com.spbstu.epam.pages.DifferentElementsPageSelenide;
import com.spbstu.epam.pages.HomePageSelenide;

public class EpamTestWebsiteSelenide {
    static public HomePageSelenide homePageSelenide;
    static public DifferentElementsPageSelenide differentElementsPageSelenide;

    public static void init() {
        homePageSelenide = new HomePageSelenide();
        differentElementsPageSelenide = new DifferentElementsPageSelenide();
    }
}
