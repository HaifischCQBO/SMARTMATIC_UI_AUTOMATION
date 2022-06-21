package Pages;

import Helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JurisdictionDashboard_Page {
    public WebDriver driver;
    public Helpers helpers;

    public JurisdictionDashboard_Page(WebDriver driver){
        this.driver = driver;
        helpers = new Helpers(driver);
    }

    // WEB ELEMENT // WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT

    By navbar_li = By.xpath("//li[@is-open=\"isMenuOpen\"]");
    By SystemAdministration_navbar_link = By.xpath("//a[text()='System Administration']");

    // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES

    public void goToSystemAdministration(){

        helpers.Click(navbar_li);
        helpers.Click(SystemAdministration_navbar_link);


    }

}
