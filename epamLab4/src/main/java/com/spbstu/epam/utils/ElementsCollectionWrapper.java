package com.spbstu.epam.utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Condition.text;

@AllArgsConstructor
public class ElementsCollectionWrapper {
    private ElementsCollection collection;
    private String subElementCssSelector;

    public SelenideElement selectElement(String name) {
        return collection.find(text(name));
    }

    public SelenideElement selectSubElement(String elementName) {
        return collection.find(text(elementName)).$(subElementCssSelector);
    }
}
