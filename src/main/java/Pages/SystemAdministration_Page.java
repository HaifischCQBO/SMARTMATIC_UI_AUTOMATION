package Pages;

import Helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SystemAdministration_Page {
    public WebDriver driver;
    public Helpers helpers;

    public SystemAdministration_Page(WebDriver driver){
        this.driver = driver;
        helpers = new Helpers(driver);
    }

    // WEB ELEMENT // WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT

    By DataManagement_Importdata_div = By.xpath("//div[contains(text(),'Import data')]");

    // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES

    public void goToImportData(){
        helpers.Click(DataManagement_Importdata_div);
    }


}
