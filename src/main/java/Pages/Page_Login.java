package Pages;

import Helpers.Helpers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page_Login {
    public WebDriver driver;
    public Helpers helpers;

    public Page_Login(WebDriver driver){
        this.driver = driver;
        helpers = new Helpers(driver);
    }

    // WEB ELEMENT // WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT

    By username_input = By.id("usernameInput");
    By password_input = By.id("passwordInput");
    By signin_link = By.id("sign-in-button");



    // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES


    @Step("First Login after fresh deployment of EMP")
    public void firstLogin(){
        helpers.sendText(username_input, helpers.getXMLParameter("DefaultAdmin_username"));
        helpers.sendText(password_input, helpers.getXMLParameter("DefaultAdmin_defaultPassword"));
        helpers.Click(signin_link);
    }

    @Step("Login with user and password credentials")
    public void makeLogin(){
        helpers.sendText(username_input, helpers.getXMLParameter("DefaultAdmin_username"));
        helpers.sendText(password_input, helpers.getXMLParameter("DefaultAdmin_password"));
        helpers.Click(signin_link);
    }

}
