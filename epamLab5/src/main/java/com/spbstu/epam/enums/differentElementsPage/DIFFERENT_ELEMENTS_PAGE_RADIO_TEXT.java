package com.spbstu.epam.enums.differentElementsPage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DIFFERENT_ELEMENTS_PAGE_RADIO_TEXT {
    LEFT_RADIO_TEXT("Gold"), MID_LEFT_RADIO_TEXT("Silver"),
    MID_RIGHT_RADIO_TEXT("Bronze"), RIGHT_RADIO_TEXT("Selen");

    private String value;
}
