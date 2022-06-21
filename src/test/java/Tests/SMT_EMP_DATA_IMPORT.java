package Tests;

import Baseclass.BaseClass;
import Pages.ImportData_Page;
import Pages.JurisdictionDashboard_Page;
import Pages.SystemAdministration_Page;
import io.qameta.allure.Feature;
import io.qase.api.annotation.CaseId;
import org.testng.annotations.Test;

public class SMT_EMP_DATA_IMPORT extends BaseClass {


    @Feature("FEATURE - EMP DATA IMPORT")
    @CaseId(4)
    @Test(description = "This a process to import the initial data for EMP")
    public void SMT_EMP_IMPORT_INITIAL_DATA() {
        JurisdictionDashboard_Page jurisdictionDashboard_page = new JurisdictionDashboard_Page(_driver);
        /**Go to System Administration*/
        jurisdictionDashboard_page.goToSystemAdministration();
        SystemAdministration_Page systemAdministration_page = new SystemAdministration_Page(_driver);
        /**Go to Import Data Section*/
        systemAdministration_page.goToImportData();
        ImportData_Page importData_page = new ImportData_Page(_driver);
        /**Select files and upload to Importation*/
        importData_page.import_files();
        /**Dependency validation*/
        importData_page.validateFilesImported();









    }

}
