package com.spbstu.epam.enums.differentElementsPage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DIFFERENT_ELEMENT_PAGE_CHECKBOX_TEXT {
    LEFT_CHECKBOX_TEXT("Water"), MID_LEFT_CHECKBOX_TEXT("Earth"),
    MID_RIGHT_CHECKBOX_TEXT("Wind"), RIGHT_CHECKBOX_TEXT("Fire");

    private String value;
}
