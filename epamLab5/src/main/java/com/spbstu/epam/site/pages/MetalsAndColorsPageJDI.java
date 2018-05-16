package com.spbstu.epam.site.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JComboBox;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropList;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import com.spbstu.epam.entities.Data;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


@JPage(url = "/page2.htm", title = "Metal and Colors")
public class MetalsAndColorsPageJDI extends WebPage {
    @FindBy(css = ".radio")
    public RadioButtons radioButtons;

    @JFindBy(css = "#elements-checklist | .checkbox label")
    public CheckList elementsCheckList;

    @JDropdown(root = @FindBy(css = ".colors"),
            list = @FindBy(tagName = "li"))
    public Dropdown colorsDropdown;

    @JComboBox(root = @FindBy(css = ".metals"),
            expand = @FindBy(css = ".caret"),
            list = @FindBy(tagName = "li"))
    public ComboBox metalsComboBox;

    @JDropList(root = @FindBy(css = ".salad"),
            expand = @FindBy(css = ".caret"),
            list = @FindBy(tagName = "li"))
    public DropList saladDropList;

    @FindBy(css = ".panel-body-list.results li")
    public TextList result;

    @FindBy(css = "#submit-button")
    public Button submit;

    public void fillMetalsAndColorsForm(Data data) {
        radioButtons.select(Integer.toString(data.getSummary()[0]));
        radioButtons.select(Integer.toString(data.getSummary()[1]));

//        elementsCheckList.uncheckAll();//this thing doesn't work
        for (String s : data.getElements()) {
            elementsCheckList.select(s);
        }

        colorsDropdown.select(data.getColor());

        metalsComboBox.select(data.getMetals());

        //can't perform any actions with DropList
        //due to issues with @JDropList.
        //It can't find locator.
//        saladDropList.uncheckAll();
//        for (String s : data.getVegetables()) {
//            saladDropList.check(s);
//        }

        submit.click();

        //cleanup check list
        for (String s : data.getElements()) {
            elementsCheckList.select(s);
        }

        Assert.assertEquals(data.getSummary()[0] + data.getSummary()[1],
                Integer.parseInt(result.getText(0).split(" ")[1]));
        for (String s : data.getElements()) {
            Assert.assertTrue(result.getText(1).contains(s),
                    String.format("Elements don't contain %s", s));
        }
        Assert.assertTrue(result.getText(2).contains(data.getColor()),
                String.format("Color doesn't contain %s", data.getColor()));
        Assert.assertTrue(result.getText(3).contains(data.getMetals()),
                String.format("Metal don't contain %s", data.getMetals()));
//        for (String s : data.getVegetables()) {
//            Assert.assertTrue(result.getText(4).contains(s),
//                    String.format("Vegetables don't contain %s", s));
//        }
    }
}
