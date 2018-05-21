package com.spbstu.epam;

import com.spbstu.epam.pages.DatesPageSelenide;
import com.spbstu.epam.pages.DifferentElementsPageSelenide;
import com.spbstu.epam.pages.HomePageSelenide;

public class EpamTestWebsiteSelenide {
    static public HomePageSelenide homePageSelenide;
    static public DifferentElementsPageSelenide differentElementsPageSelenide;
    static public DatesPageSelenide datesPageSelenide;


    public static void init() {
        homePageSelenide = new HomePageSelenide();
        differentElementsPageSelenide = new DifferentElementsPageSelenide();
        datesPageSelenide = new DatesPageSelenide();
    }
}
