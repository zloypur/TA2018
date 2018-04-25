package com.spbstu.epam;

import com.spbstu.epam.pages.DifferentElementsPageJDI;
import com.spbstu.epam.pages.HomePageJDI;

public class EpamTestWebsiteSelenide {
    static public HomePageJDI homePageJDI;
    static public DifferentElementsPageJDI differentElementsPageJDI;

    public static void init() {
        homePageJDI = new HomePageJDI();
        differentElementsPageJDI = new DifferentElementsPageJDI();
    }
}
