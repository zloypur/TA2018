package com.spbstu.epam.site.sections;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.spbstu.epam.entities.User;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends Form<User> {
    @FindBy(css = "#Login")
    public TextField name;

    @FindBy(css = "#Password")
    public TextField password;

    @FindBy(css = "form .btn-login")
    public Button submit;
}
