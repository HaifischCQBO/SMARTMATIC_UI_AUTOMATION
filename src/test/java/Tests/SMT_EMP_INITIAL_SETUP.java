package Tests;

import Baseclass.BaseClass;
import Helpers.BrowserConfig;
import Helpers.Helpers;
import Pages.InitialSetup_Page;
import Pages.Page_Login;
import com.google.zxing.NotFoundException;
import io.qameta.allure.Feature;
import io.qase.api.annotation.CaseId;
import org.jboss.aerogear.security.otp.api.Base32;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class SMT_EMP_INITIAL_SETUP extends BaseClass {


    @Override
    @BeforeTest
    public void setUp() {
        BrowserConfig browserConfig = new BrowserConfig();
        this._driver = browserConfig.setUpBrowser(new Helpers().getXMLParameter("browser"));
        System.out.println(_driver);
        this.helpers = new Helpers(_driver);
        helpers.getURL(helpers.getXMLParameter("url") + "/login.html");

    }

    @Feature("FEATURE - EMP INITIAL CONFIGURATION")
    @CaseId(3)
    @Test(description = "This a process to configure initially the complete suite of the EMP. " +
            "Security and Custom Configuration took place in this test")
    public void SMT_EMP_INITIAL_SETUP() throws NotFoundException, IOException, Base32.DecodingException, URISyntaxException {
        Page_Login page_login = new Page_Login(_driver);
        page_login.firstLogin();

        InitialSetup_Page initialSetup_page = new InitialSetup_Page(_driver);
        /**Selecting option to Setup System*/
        initialSetup_page.StartInitialSetup();
        /** Wizard start */
        initialSetup_page.StartButton();
        /** Change default Admin user password */
        helpers.Print("Change default Admin user password");
        initialSetup_page.ChangePassword();

        /** After first execution should be commented */
        initialSetup_page.ReadAndSetTextFromQR();
        Assert.assertFalse(initialSetup_page.ValidateQRisCorrect(), "QR Code wasn't correct.");
        initialSetup_page.acceptCode();

        /** Create new Administration user */
        initialSetup_page.FillFormAdministrationUser();
        /** Create new Database Password */
        initialSetup_page.FillFormDatabaseCredentials();
        /** Set the Jurisdiction information */
        initialSetup_page.FillFormJurisdictionInformation();
        /** Set the Jurisdiction Rules */
        initialSetup_page.SelectJurisdictionRules();
        /** Setup security information */
        initialSetup_page.SetSecurityInformation();
        /** Review the initial setup information */
        initialSetup_page.AssertInitialSetup();
        initialSetup_page.endInitialSetup();
        /** Masterkey providing */
        initialSetup_page.enterMasterKey();






    }

}
