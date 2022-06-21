package Baseclass;

import Helpers.Helpers;
import Helpers.BrowserConfig;
import Pages.Page_Login;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseClass {
    public WebDriver _driver;
    public Helpers helpers;

    @BeforeMethod(description = "Browser configuration and test set up")
    public void setUp() {
        BrowserConfig browserConfig = new BrowserConfig();
        this._driver = browserConfig.setUpBrowser(new Helpers().getXMLParameter("browser"));
        this.helpers = new Helpers(_driver);
        helpers.Print("I STILL STANDING");
        helpers.getURL(helpers.getXMLParameter("url")+"/login.html");
        Page_Login page_login = new Page_Login(_driver);
        page_login.makeLogin();

    }

    @AfterMethod(description = "Closing and Cleaning Test", alwaysRun = true)
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            helpers.takeScreenshot("AT FAIL------" + result.getName());
            helpers.takeScreenshotAllureFAIL();

        } else if (result.getStatus() == ITestResult.SKIP) {
            helpers.takeScreenshot("AT SKIP------" + result.getName());
            helpers.takeScreenshotAllureFAIL();

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            helpers.takeScreenshot("AT PASS------" + result.getName());
            helpers.takeScreenshotAllurePASS();

        }
      //_driver.quit();


    }
}
