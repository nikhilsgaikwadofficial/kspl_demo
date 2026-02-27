package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddUserValidationPage;

public class AddUserValidationTest extends BaseTest {

    AddUserValidationPage validationPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        validationPage = new AddUserValidationPage(driver);
        validationPage.clickMaster();
        validationPage.clickUser();
        validationPage.clickAddUser();
    }

    @DataProvider(name = "userData")
    public Object[][] userData() {
        // Format: { id, name, mobile, email, password, role, designation, branch, isValid, scenario }
        return new Object[][] {
            { "EMP001", "John Doe", "9876543210", "john@example.com", "Pass@123", "Level 1", "F&B Executive", "ghatkopar(S8ul bootcamp)", true, "Successful Creation" },
            { "", "Jane Doe", "9876543211", "jane@example.com", "Pass@123", "Level 1", "F&B Executive", "ghatkopar(S8ul bootcamp)", false, "Validation - Emp ID Empty" },
            { "EMP002", "", "9876543212", "test2@example.com", "Pass@123", "Level 1", "F&B Executive", "ghatkopar(S8ul bootcamp)", false, "Validation - Name Empty" },
            { "EMP003", "Mobile User", "", "test3@example.com", "Pass@123", "Level 1", "F&B Executive", "ghatkopar(S8ul bootcamp)", false, "Validation - Mobile Empty" },
            { "EMP004", "Email User", "9876543213", "", "Pass@123", "Level 1", "F&B Executive", "ghatkopar(S8ul bootcamp)", false, "Validation - Email Empty" },
            { "EMP005", "Pass User", "9876543214", "test5@example.com", "", "Level 1", "F&B Executive", "ghatkopar(S8ul bootcamp)", false, "Validation - Password Empty" },
            { "EMP006", "Role User", "9876543215", "test6@example.com", "Pass@123", null, "F&B Executive", "ghatkopar(S8ul bootcamp)", false, "Validation - Role Not Selected" },
            { "EMP007", "Desig User", "9876543216", "test7@example.com", "Pass@123", "Level 1", null, "ghatkopar(S8ul bootcamp)", false, "Validation - Designation Not Selected" },
            { "EMP008", "Branch User", "9876543217", "test8@example.com", "Pass@123", "Level 1", "F&B Executive", null, false, "Validation - Branch Not Selected" },
            { "EMP009", "Invalid Mob", "12345", "test9@example.com", "Pass@123", "Level 1", "F&B Executive", "ghatkopar(S8ul bootcamp)", false, "Validation - Invalid Mobile format" }
        };
    }

    @Test(dataProvider = "userData")
    public void validateAddUser(String id, String name, String mob, String mail, String pass, String role, String desig, String branch, boolean isValid, String scenario) {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("Executing Scenario: " + scenario);
        System.out.println("Employee ID: " + id);
        System.out.println("Name: " + name);

        validationPage.navigateToAddUser();

        // Fill all fields manually
        if (id != null) validationPage.enterEmpId(id);
        if (name != null) validationPage.enterEmpName(name);
        if (mob != null) {
            try {
                validationPage.enterMobile(mob);
            } catch (IllegalArgumentException e) {
                System.out.println("Caught Expected Error: " + e.getMessage());
                softAssert.assertTrue(!isValid, "Caught error but scenario was expected to be valid.");
                try { Thread.sleep(5000); } catch (InterruptedException ignored) {}
                return;
            }
        }
        if (mail != null) validationPage.enterEmail(mail);
        if (pass != null) validationPage.enterPassword(pass);
        if (role != null) validationPage.selectRole(role);
        if (desig != null) validationPage.selectDesignation(desig);
        if (branch != null) validationPage.selectBranch(branch);

        validationPage.clickSubmit();

        if (isValid) {
            System.out.println("Expected Result: Success");
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
            boolean success = validationPage.isSuccessMessageDisplayed();
            softAssert.assertTrue(success, "User creation failed for: " + scenario);
        } else {
            System.out.println("Expected Result: Validation Error");
            // Check if any error is displayed (simplifying like LoginValidation)
            boolean errorFound = validationPage.isEmpIdErrorDisplayed() || 
                                validationPage.isEmpNameErrorDisplayed() || 
                                validationPage.isMobileErrorDisplayed() || 
                                validationPage.isEmailErrorDisplayed() || 
                                validationPage.isPasswordErrorDisplayed() || 
                                validationPage.isRoleErrorDisplayed() || 
                                validationPage.isDesignationErrorDisplayed() || 
                                validationPage.isBranchErrorDisplayed();
            
            softAssert.assertTrue(errorFound, "Expected validation error was NOT shown for: " + scenario);
        }

        //System.out.println("Waiting 5 seconds after execution...");
        try { Thread.sleep(5000); } catch (InterruptedException ignored) {}
        softAssert.assertAll();
    }
}
