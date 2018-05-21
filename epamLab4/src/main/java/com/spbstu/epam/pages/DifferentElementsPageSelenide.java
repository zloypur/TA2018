package com.spbstu.epam.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.spbstu.epam.utils.ElementsCollectionWrapper;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_LOG_CATEGORIES.LOG_CATEGORY_DROPDOWN;
import static com.spbstu.epam.enums.differentElementsPage.DIFFERENT_ELEMENTS_PAGE_LOG_CATEGORIES.LOG_CATEGORY_RADIOS;

public class DifferentElementsPageSelenide {
    @FindBy(css = ".label-checkbox")
    ElementsCollection checkboxes;

    @FindBy(css = ".label-radio")
    ElementsCollection radios;

    @FindBy(css = ".colors .uui-form-element")
    SelenideElement dropdown;

    @FindBy(css = "[value=\"Default Button\"]")
    SelenideElement defaultButton;

    @FindBy(css = "[value=\"Button\"]")
    SelenideElement button;

    @FindBy(css = "#mCSB_1")
    SelenideElement leftSideSection;

    @FindBy(css = "#mCSB_2")
    SelenideElement rightSideSection;

    @FindBy(css = ".logs li")
    ElementsCollection logRecords;

    final String CHECKBOX_INPUT_CSS_SELECTOR = "[type=checkbox]";

    final String RADIO_INPUT_CSS_SELECTOR = "[type=radio]";

    ElementsCollectionWrapper checkboxesWrapped;

    ElementsCollectionWrapper radiosWrapped;

    Deque<String> pageLogs = new LinkedList<>();

    public DifferentElementsPageSelenide() {
        Selenide.page(this);
    }

    @Step
    public void checkPageElements() {
        checkboxes.forEach(e -> e.shouldBe(visible));
        checkboxesWrapped = new ElementsCollectionWrapper(checkboxes, CHECKBOX_INPUT_CSS_SELECTOR);
        radios.forEach(e -> e.shouldBe(visible));
        radiosWrapped = new ElementsCollectionWrapper(radios, RADIO_INPUT_CSS_SELECTOR);
        dropdown.shouldBe(visible);
        defaultButton.shouldBe(visible);
        button.shouldBe(visible);
        leftSideSection.shouldBe(visible);
        rightSideSection.shouldBe(visible);
    }
    @Step
    public void setCheckboxSelected(String s) {
        if (!checkboxesWrapped.selectSubElement(s).is(checked)) {
            checkboxesWrapped.selectElement(s).setSelected(true);
        }
        checkboxesWrapped.selectSubElement(s).shouldBe(checked);
        pageLogs.addFirst(String.format("%s: condition changed to %s", s, true));
    }

    @Step
    public void setCheckboxUnselected(String s) {
        if (checkboxesWrapped.selectSubElement(s).is(checked)) {
            checkboxesWrapped.selectElement(s).setSelected(true);
        }
        checkboxesWrapped.selectSubElement(s).shouldNotBe(checked);
        pageLogs.addFirst(String.format("%s: condition changed to %s", s, false));
    }

    @Step
    public void setRadioSelected(String s) {
        radiosWrapped.selectElement(s).setSelected(true);
        radiosWrapped.selectSubElement(s).shouldBe(selected);
        pageLogs.addFirst(String.format("%s: value changed to %s", LOG_CATEGORY_RADIOS.getValue(), s));
    }

    @Step
    public void setDropdownValue(String s) {
        dropdown.selectOption(s);
        dropdown.shouldHave(text(s));
        pageLogs.addFirst(String.format("%s: value changed to %s", LOG_CATEGORY_DROPDOWN.getValue(), s));
    }

    @Step
    public void checkLogs(List<String> names) {
        logRecords.shouldHave(texts(new ArrayList(pageLogs)));
    }
}