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

    public void checkPageElements(){
        checkboxes.forEach(e -> e.shouldBe(visible));
        radios.forEach(e -> e.shouldBe(visible));
        dropdown.shouldBe(visible);
        defaultButton.shouldBe(visible);
        button.shouldBe(visible);
        leftSideSection.shouldBe(visible);
        rightSideSection.shouldBe(visible);
    }

    public void setCheckboxSelected(String s){
        checkboxes.find(text(s)).setSelected(true).$(checkboxInputCssSelector).shouldBe(checked);
    }

    public void setRadioSelected(String s){
        radios.find(text(s)).setSelected(true).$(radioInputCssSelector).shouldBe(selected);
    }

    public void setDropdownValue(String s){
        dropdown.selectOption(s);
        dropdown.shouldHave(text(s));
    }

    public void checkLogs(List<String> names){
        logRecords.shouldHave(texts(names));

        boolean radioFlag = true;
        boolean colorFlag = true;
        Map<String, Boolean> checkboxFlags = new TreeMap<>();
        for(SelenideElement e : logRecords){
            List<String> splitedString = Arrays.asList(e.getText().split("\\d\\d:\\d\\d:\\d\\d ")[1].split(": "));

            String param = splitedString.get(1).split(" to ")[1];
            String category = splitedString.get(0);

            switch (category){
                case "metal": {
                    if(radioFlag) {
                        radios.find(text(param)).$(radioInputCssSelector).shouldBe(selected);
                        radioFlag = false;
                    }else{
                        radios.find(text(param)).$(radioInputCssSelector).shouldNotBe(selected);
                    }
                }break;
                case "Colors": {
                    if(colorFlag) {
                        dropdown.getSelectedOption().shouldHave(text(param));
                        colorFlag = false;
                    }else{
                        dropdown.getSelectedOption().shouldNotHave(text(param));
                    }
                }break;
                default: {
                    if(new Boolean(param)){
                        if(!checkboxFlags.get(category)) {
                            checkboxes.findBy(text(category)).$(checkboxInputCssSelector).shouldBe(checked);
                            checkboxFlags.put(category, true);
                        }
                    }else{
                        if(!checkboxFlags.get(category)) {
                            checkboxes.findBy(text(category)).$(checkboxInputCssSelector).shouldBe(checked);
                            checkboxFlags.put(category, true);
                        }
                    }
                }break;
            }
        }
    }
}
