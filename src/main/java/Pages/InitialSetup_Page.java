package Pages;

import Helpers.Helpers;
import Helpers.topt_decode;
import com.google.zxing.NotFoundException;
import org.jboss.aerogear.security.otp.api.Base32;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InitialSetup_Page {
    public WebDriver driver;
    public Helpers helpers;
    public topt_decode topt;

    public InitialSetup_Page(WebDriver driver) {
        this.driver = driver;
        this.helpers = new Helpers(driver);
        this.topt = new topt_decode(driver);
    }

    // WEB ELEMENT // WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT //WEB ELEMENT


    By BeginInitialSetup_Button = By.id("beginInitialSetupBtn");
    By Start_Button = By.id("startBtn");
    /**
     * Change Password
     **/
    By newPasswordField_Input = By.id("password");
    By configNewPasswordField_Input = By.id("confirm-password");
    By next_Button = By.id("nextBtn");
    /**
     * QR TOTP
     **/
    By qr_auth_validation = By.xpath("//img[@alt='two dimensions QR']");
    By pin_qr_input = By.id("pin");
    /**
     * Administrator User
     **/
    By administrator_user_first_name_input = By.id("name");
    By administrator_user_last_name_input = By.name("lastname");
    By administrator_username_input = By.name("username");
    By administrator_user_password_input = By.name("password");
    By administrator_user_confirm_password_input = By.name("confirmPassword");
    /**
     * Database Information
     **/
    By database_password = By.name("newPassword");
    By database_confirm_password = By.name("confirmPassword");
    /**
     * Jurisdiction Information
     **/
    By jurisdiction_name_input = By.id("jurisdictionName");
    By jurisdiction_displayName_input = By.id("displayName");
    By jurisdiction_state_input = By.id("jurisdictionState");
    By jurisdiction_city_input = By.id("jurisdictionCity");
    By jurisdiction_logo_input = By.id("file-input-jurisdiction-logo");

    By votingTypesEnabled_ElectionDayCenters_chk = By.xpath("//check-box[@checkbox-id='checkbox_Polling Place Election Day']");
    By votingTypesEnabled_ElectionDayCenters_chk_css = By.cssSelector(".checkbox");

    By jurisdiction_param_name = By.cssSelector(".form-eEvent-electoral-rules > .row > .col-xs-12 > .col-xs-6 .param-name");

    By masterKeyPassphrase_input = By.id("masterKeyPassphrase");

    /**
     * REVIEW INFORMATION
     **/
    By Administrator_user_first_name = By.xpath("//*[@id=\"form-views\"]/div[1]/div[2]/div/ui-view/div/div/div[3]/div[2]/div[1]/div[2]/span");
    By Administrator_user_first_last_name =By.xpath("//*[@id=\"form-views\"]/div[1]/div[2]/div/ui-view/div/div/div[3]/div[2]/div[1]/div[4]/span");
    By Administrator_user_username = By.xpath("//*[@id=\"form-views\"]/div[1]/div[2]/div/ui-view/div/div/div[3]/div[2]/div[2]/div[2]/span");
    By Administrator_user_role = By.xpath("//*[@id=\"form-views\"]/div[1]/div[2]/div/ui-view/div/div/div[3]/div[2]/div[2]/div[4]/span");
    By Jurisdiction_name = By.xpath("//*[@id=\"form-views\"]/div[1]/div[2]/div/ui-view/div/div/div[5]/div[2]/div[1]/div[2]/span");
    By Jurisdiction_display_name = By.xpath("//*[@id=\"form-views\"]/div[1]/div[2]/div/ui-view/div/div/div[5]/div[2]/div[1]/div[4]/span");
    By Jurisdiction_state = By.xpath("//*[@id=\"form-views\"]/div[1]/div[2]/div/ui-view/div/div/div[5]/div[2]/div[2]/div[2]/span");
    By Jurisdiction_city = By.xpath("//*[@id=\"form-views\"]/div[1]/div[2]/div/ui-view/div/div/div[5]/div[2]/div[2]/div[4]/span");

    By finishBtn = By.id("finishBtn");
    By final_message_done = By.xpath("//*[@id=\"form-views\"]/div[1]/div/div/ui-view/div/div/p[2]");
    By masterKeyPassphrase_login_input = By.id("masterKeyPassphrase");
    By submitbutton = By.id("submit-button");

    // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES // FUNCIONES

    public void StartInitialSetup() {
        helpers.Click(BeginInitialSetup_Button);
    }

    public void StartButton() {

        helpers.Click(Start_Button);
    }

    public void ChangePassword() {
        helpers.sendText(newPasswordField_Input, helpers.getXMLParameter("DefaultAdmin_password"));
        helpers.sendText(configNewPasswordField_Input, helpers.getXMLParameter("DefaultAdmin_password"));
        helpers.Click(next_Button);
    }

    public void ReadAndSetTextFromQR() throws NotFoundException, IOException, Base32.DecodingException, URISyntaxException {
        helpers.sendText(pin_qr_input, topt.GenerateCodeMFA(qr_auth_validation));
    }

    public boolean ValidateQRisCorrect() {
        if (helpers.ElementIsEnabled(next_Button)) {
            return true;
        } else {
            return false;
        }
    }

    public void acceptCode() {
        helpers.Click(next_Button);
    }

    public void FillFormAdministrationUser() {
        helpers.sendText(administrator_user_first_name_input, helpers.getXMLParameter("Admin_name"));
        helpers.Pause(2);
        helpers.sendText(administrator_user_last_name_input, helpers.getXMLParameter("Admin_lastname"));
        helpers.sendText(administrator_username_input, helpers.getXMLParameter("Admin_username"));
        helpers.sendText(administrator_user_password_input, helpers.getXMLParameter("Admin_password"));
        helpers.sendText(administrator_user_confirm_password_input, helpers.getXMLParameter("Admin_password"));
        helpers.Click(next_Button);
    }

    public void FillFormDatabaseCredentials() {
        helpers.sendText(database_password, helpers.getXMLParameter("database_password"));
        helpers.sendText(database_confirm_password, helpers.getXMLParameter("database_password"));
        helpers.Click(next_Button);
    }

    public void FillFormJurisdictionInformation() {
        helpers.sendText(jurisdiction_name_input, helpers.getXMLParameter("jurisdiction_name"));
        helpers.Pause(2);
        helpers.sendText(jurisdiction_displayName_input, helpers.getXMLParameter("jurisdiction_displayName"));
        helpers.sendText(jurisdiction_state_input, helpers.getXMLParameter("jurisdiction_state"));
        helpers.sendText(jurisdiction_city_input, helpers.getXMLParameter("jurisdiction_city"));
        File file = new File(helpers.getXMLParameter("jurisdiction_logo"));
        String path = file.getAbsolutePath();
        helpers.sendImage(jurisdiction_logo_input, path);

        helpers.Print("******************************CHECKBOX SELECTION********************************************");
        List<WebElement> checkboxs = driver.findElements(votingTypesEnabled_ElectionDayCenters_chk_css);

        for (WebElement checkbox : checkboxs) {

            WebElement checkbox_input = checkbox.findElement(By.tagName("input"));

            if (Boolean.parseBoolean(helpers.getXMLParameter(checkbox_input.getAttribute("id")))) {
                helpers.Print("FINAL STATE IS : " + true);
                //IF TRUE CHECKBOX SHOULD BE SELECTED BY THE END.
                if (!checkbox_input.isSelected()) {
                    helpers.Print("IF FROM ELEMENT STATE: " + checkbox_input.isSelected());
                    //IF THE ELEMENT ISNT SELECTED, PROCEED TO CHECK IT
                    helpers.ClickJS(checkbox_input);
                }
            } else {
                //IF FALSE CHECKBOX SHOULD BE DESELECTED BY THE END.
                helpers.Print("FINAL STATE IS : " + false);
                if (checkbox_input.isSelected()) {
                    helpers.Print("IF FROM ELEMENT STATE: " + checkbox_input.isSelected());
                    //IF IS SELECTED, DESELECTED.
                    helpers.ClickJS(checkbox_input);
                }
            }
        }
        helpers.Pause(5);
        helpers.Click(next_Button);
    }

    public void SelectJurisdictionRules() {
        helpers.Print("******************************CHECKBOX SELECTION********************************************");
        List<WebElement> checkboxs = driver.findElements(jurisdiction_param_name);
        helpers.Print(String.valueOf(checkboxs.size()));
        for (WebElement checkbox : checkboxs) {

            //helpers.Print(checkbox.getAttribute("innerHTML"));
            String str = checkbox.getAttribute("innerHTML");
            str = str.replaceAll("[^a-zA-Z0-9]", "");
            helpers.Print(str);
            WebElement checkbox_input = checkbox.findElement(By.xpath("following-sibling::div//input"));

            if(str.equals("PartisanOffices")){
                continue;
            }
            if (Boolean.parseBoolean(helpers.getXMLParameter(str))) {
                helpers.Print("FINAL STATE IS : " + true);
                //IF TRUE CHECKBOX SHOULD BE SELECTED BY THE END.
                if (!checkbox_input.isSelected()) {
                    helpers.Print("IF FROM ELEMENT STATE: " + checkbox_input.isSelected());
                    //IF THE ELEMENT ISNT SELECTED, PROCEED TO CHECK IT
                    helpers.ClickJS(checkbox_input);
                }
            } else {
                //IF FALSE CHECKBOX SHOULD BE DESELECTED BY THE END.
                helpers.Print("FINAL STATE IS : " + false);
                if (checkbox_input.isSelected()) {
                    helpers.Print("IF FROM ELEMENT STATE: " + checkbox_input.isSelected());
                    //IF IS SELECTED, DESELECTED.
                    helpers.ClickJS(checkbox_input);
                }
            }
        }
        helpers.Click(next_Button);
    }

    public void SetSecurityInformation() {
        helpers.sendText(masterKeyPassphrase_input, helpers.getXMLParameter("security_masterKeyPass"));
        helpers.Click(next_Button);
    }

    public void AssertInitialSetup() {

        Assert.assertEquals(helpers.getText(Administrator_user_first_name),helpers.getXMLParameter("Admin_name"),"THE ADMIN NAME IS INCORRECT");
        Assert.assertEquals(helpers.getText(Administrator_user_first_last_name),helpers.getXMLParameter("Admin_lastname"),"THE ADMIN LAST NAME IS INCORRECT");
        Assert.assertEquals(helpers.getText(Administrator_user_username),helpers.getXMLParameter("Admin_username"),"THE ADMIN USERNAME IS INCORRECT");
        Assert.assertEquals(helpers.getText(Administrator_user_role),"Administrator","THE ADMIN ROLE IS INCORRECT");

        Assert.assertEquals(helpers.getText(Jurisdiction_name),helpers.getXMLParameter("jurisdiction_name"),"THE JURISDICTION NAME IS INCORRECT");
        Assert.assertEquals(helpers.getText(Jurisdiction_display_name),helpers.getXMLParameter("jurisdiction_displayName"),"THE JURISDICTION DISPLAY NAME IS INCORRECT");
        Assert.assertEquals(helpers.getText(Jurisdiction_state),helpers.getXMLParameter("jurisdiction_state"),"THE JURISDICTION STATE IS INCORRECT");
        Assert.assertEquals(helpers.getText(Jurisdiction_city),helpers.getXMLParameter("jurisdiction_city"),"THE JURISDICTION CITY IS INCORRECT");
        helpers.Click(next_Button);
    }
    public void endInitialSetup(){
        Assert.assertEquals(helpers.getText(final_message_done),"The information has been successfully uploaded", "THE INITIAL SETUP FAILED");
        helpers.ClickUntilEnabled(finishBtn);
    }
    public void enterMasterKey(){
        helpers.sendText(masterKeyPassphrase_login_input,helpers.getXMLParameter("security_masterKeyPass"));
        helpers.ClickUntilEnabled(submitbutton);
    }

}

