package com.spbstu.epam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum HOME_PAGE_DATA {
    LOGIN("epam"), PASSWORD("1234"), USERNAME(" PITER CHAILOVSKII"), IMAGES_COUNT(4), TEXTS_COUNT(4),
    BENEFITS_TEXT(Arrays.asList("To include good practices\nand ideas from successful\nEPAM projec",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…")),
    MAIN_HEADER_TEXT("EPAM FRAMEWORK WISHES…"), HEADER_TEXT("LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT," +
            " SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, " +
            "QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT " +
            "DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."),
    HEADER_DROPDOWN_SERVICE_MENU_OPTIONS_TEXT(Arrays.asList("SUPPORT", "DATES", "COMPLEX TABLE", "SIMPLE TABLE",
            "TABLE WITH PAGES", "DIFFERENT ELEMENTS")),
    LEFT_SIDE_SERVICE_MENU_OPTIONS_TEXT(Arrays.asList("Support", "Dates", "Complex Table", "Simple Table",
            "Table with pages", "Different elements"));

    private Object value;
}
