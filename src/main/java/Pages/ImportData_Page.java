package Pages;

import Helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;

public class ImportData_Page {
    public WebDriver driver;
    public Helpers helpers;

    public ImportData_Page(WebDriver driver) {
        this.driver = driver;
        helpers = new Helpers(driver);
    }

    private List<String> files_to_import = Arrays.asList(
            "src/main/resources/usa/data_import/electoral_subdivisions.csv",
            "src/main/resources/usa/data_import/precincts.csv",
            "src/main/resources/usa/data_import/parties.csv",
            "src/main/resources/usa/data_import/districts.csv",
            "src/main/resources/usa/data_import/polling_places.csv",
            "src/main/resources/usa/data_import/stations.csv",
            "src/main/resources/usa/data_import/resources.zip");

    // WEB ELEMENT // WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT

    By drop_area_files_input = By.id("inputSelectFiles");
    By validate_files_button = By.xpath("//button[contains(text(),'Validate')]");


    // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES


    public void import_files() {
        WebElement inputField = helpers.returnWebElement(drop_area_files_input);

        inputField.sendKeys(
                helpers.returnAbsolutePath(files_to_import.get(0)) + "\n "
                        + helpers.returnAbsolutePath(files_to_import.get(1)) + "\n "
                        + helpers.returnAbsolutePath(files_to_import.get(2)) + "\n "
                        + helpers.returnAbsolutePath(files_to_import.get(3)) + "\n "
                        + helpers.returnAbsolutePath(files_to_import.get(4)) + "\n "
                        + helpers.returnAbsolutePath(files_to_import.get(5)) + "\n"
                        + helpers.returnAbsolutePath(files_to_import.get(6)));


    }
    public void validateFilesImported(){
        helpers.Click(validate_files_button);
    }



}
