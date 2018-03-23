package com.spbstu.epam.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_LOG_CATEGORIES.LOG_CATEGORY_1;

public class DifferentElementsPageSelenide {
    ElementsCollection checkboxes = Selenide.$$(".label-checkbox");

    ElementsCollection radios = Selenide.$$(".label-radio");

    SelenideElement dropdown = Selenide.$(".colors .uui-form-element");

    SelenideElement defaultButton = Selenide.$("[value=\"Default Button\"]");

    SelenideElement button = Selenide.$("[value=\"Button\"]");

    SelenideElement leftSideSection = Selenide.$("#mCSB_1");

    SelenideElement rightSideSection = Selenide.$("#mCSB_2");

    ElementsCollection logRecords = Selenide.$$(".logs li");

    String checkboxInputCssSelector = "[type=checkbox]";

    String radioInputCssSelector = "[type=radio]";

    public void checkPageElements() {
        checkboxes.forEach(e -> e.shouldBe(visible));
        radios.forEach(e -> e.shouldBe(visible));
        dropdown.shouldBe(visible);
        defaultButton.shouldBe(visible);
        button.shouldBe(visible);
        leftSideSection.shouldBe(visible);
        rightSideSection.shouldBe(visible);
    }

    public void setCheckboxSelected(String s) {
        if (!checkboxes.find(text(s)).$(checkboxInputCssSelector).is(selected)) {
            checkboxes.find(text(s)).setSelected(true);
        }
        checkboxes.find(text(s)).$(checkboxInputCssSelector).shouldBe(checked);
    }

    public void setCheckboxUnselected(String s) {
        if (checkboxes.find(text(s)).$(checkboxInputCssSelector).is(selected)) {
            checkboxes.find(text(s)).setSelected(true);
        }
        checkboxes.find(text(s)).$(checkboxInputCssSelector).shouldNotBe(checked);
    }

    public void setRadioSelected(String s) {
        radios.find(text(s)).setSelected(true).$(radioInputCssSelector).shouldBe(selected);
    }

    public void setDropdownValue(String s) {
        dropdown.selectOption(s);
        dropdown.shouldHave(text(s));
    }

    public void checkLogs(List<String> names) {
        for (String name : names) {
            SelenideElement element = logRecords.findBy(text(name));
            String[] splittedString = element.getText().split("\\d\\d:\\d\\d:\\d\\d ")[1].split(": ");//this looks awful, but I haven't had a better idea
            String category = splittedString[0];
            String param = splittedString[1].split(" to ")[1];
            switch (category) {
                case "metal": {
                    if (logRecords.findBy(text(category)).getText().equals(element.getText())) {
                        radios.findBy(text(name)).$(radioInputCssSelector).shouldBe(selected);
                    } else {
                        radios.findBy(text(name)).$(radioInputCssSelector).shouldNotBe(selected);
                    }
                } break;
                case "Colors": {
                    if (logRecords.findBy(text(category)).getText().equals(element.getText())) {
                        dropdown.getSelectedOption().shouldHave(text(name));
                    } else {
                        dropdown.getSelectedOption().shouldNotHave(text(name));
                    }
                } break;
                default: {
                    if (Boolean.valueOf(param)) {
                        checkboxes.findBy(text(name)).$(checkboxInputCssSelector).shouldBe(selected);
                    } else {
                        checkboxes.findBy(text(name)).$(checkboxInputCssSelector).shouldNotBe(selected);
                    }
                } break;
            }
        }
    }

}