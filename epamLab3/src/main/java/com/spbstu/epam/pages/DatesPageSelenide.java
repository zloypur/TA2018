package com.spbstu.epam.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.spbstu.epam.enums.DATES_PAGE_DATA.MAX_SLIDER_LENGTH;
import static java.lang.Math.abs;

public class DatesPageSelenide {
    @FindBy(css = ".ui-slider-handle")
    ElementsCollection sliderHandles;

    public DatesPageSelenide() {
        Selenide.page(this);
    }

    public void setSliderRange(int leftHandlePosition, int rightHandlePosition) {
        sliderHandles.shouldHaveSize(2);

        if (abs(leftHandlePosition) % 101 <= abs(rightHandlePosition) % 101) {
            leftHandlePosition = abs(leftHandlePosition) % 101;
            rightHandlePosition = abs(rightHandlePosition) % 101;
        } else {
            int tmp = leftHandlePosition;
            leftHandlePosition = abs(rightHandlePosition) % 101;
            rightHandlePosition = abs(tmp) % 101;
        }

        Actions dragAndDrop = new Actions(WebDriverRunner.getWebDriver());

        if (rightHandlePosition < Integer.parseInt(sliderHandles.first().getText())) {
            dragAndDrop.dragAndDropBy(sliderHandles.first(), (int) (MAX_SLIDER_LENGTH.getValue()
                    * (leftHandlePosition - 1 - Integer.parseInt(sliderHandles.first().getText())) / 100.), 0).perform();
            dragAndDrop.dragAndDropBy(sliderHandles.last(), (int) (MAX_SLIDER_LENGTH.getValue()
                    * (rightHandlePosition - 1 - Integer.parseInt(sliderHandles.last().getText())) / 100.), 0).perform();
        } else {
            dragAndDrop.dragAndDropBy(sliderHandles.last(), (int) (MAX_SLIDER_LENGTH.getValue()
                    * (rightHandlePosition - 1 - Integer.parseInt(sliderHandles.last().getText())) / 100.), 0).perform();
            dragAndDrop.dragAndDropBy(sliderHandles.first(), (int) (MAX_SLIDER_LENGTH.getValue()
                    * (leftHandlePosition - 1 - Integer.parseInt(sliderHandles.first().getText())) / 100.), 0).perform();

        }

        sliderHandles.first().shouldHave(text(Integer.toString(leftHandlePosition)));
        sliderHandles.last().shouldHave(text(Integer.toString(rightHandlePosition)));
    }
}
