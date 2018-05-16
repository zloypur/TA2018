package com.spbstu.epam.site;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import com.spbstu.epam.entities.User;
import com.spbstu.epam.site.pages.HomePageJDI;
import com.spbstu.epam.site.pages.MetalsAndColorsPageJDI;
import com.spbstu.epam.site.sections.LoginForm;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

@JSite("https://jdi-framework.github.io/tests/")
public class EpamTestWebsiteSelenide extends WebSite {
    public static HomePageJDI homePageJDI;

    public static MetalsAndColorsPageJDI metalsAndColorsPageJDI;

    public static LoginForm loginForm;

    @FindBy(css = ".profile-photo")
    public static Label profilePhoto;

    @FindBy(css = ".logout")
    public static Button logout;

    @FindBy(css = ".uui-navigation | [href=\"page2.htm\"]")
    public static Button metalsAndColorsPageButton;

    public static void openMetalsAndColorsPage() {
        metalsAndColorsPageButton.click();
    }

    public static void login(User user) {
        profilePhoto.click();
        loginForm.loginAs(user);
        Assert.assertTrue(logout.isDisplayed(), "Login failed");
    }

    public static void login(String name, String password) {
        login(new User(name, password));
    }
}
