package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CreateUserPage;

public class CreateUserValidationTest extends BaseTest {

    CreateUserPage createUser;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        createUser = new CreateUserPage(driver);
        createUser.clickMaster();
        createUser.clickUser();
        createUser.clickAddUser();
    }

   
    private void submitEmptyForm() {
        createUser.clickSubmit();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }


    @Test(priority = 1)
    public void validateEmptyFormSubmission() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("=== TC-01: Empty Form Submission ===");

        submitEmptyForm();

        boolean anyError = createUser.isEmpIdErrorDisplayed()
                || createUser.isEmpNameErrorDisplayed()
                || createUser.isMobileErrorDisplayed()
                || createUser.isEmailErrorDisplayed()
                || createUser.isPasswordErrorDisplayed()
                || createUser.isRoleErrorDisplayed()
                || createUser.isDesignationErrorDisplayed()
                || createUser.isBranchErrorDisplayed();

       // System.out.println("At least one validation error displayed: " + anyError);
        softAssert.assertTrue(anyError,
                "No validation errors were displayed when the form was submitted empty.");
        softAssert.assertAll();
    }

    // ─── TC-02: Employee ID ─────────────────────────────────────────────────────
    @Test(priority = 2, dependsOnMethods = "validateEmptyFormSubmission")
    public void validateEmpIdFieldMandatory() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("=== TC-02: Validating Employee ID Field ===");

        boolean errorDisplayed = createUser.isEmpIdErrorDisplayed();
        if (errorDisplayed) {
            System.out.println("  PASS: Error message displayed → 'Employee ID field is required'");
        } else {
            System.out.println("  FAIL: 'Employee ID field is required' error was NOT shown when left empty.");
        }

        softAssert.assertTrue(errorDisplayed,
                "FAIL: 'Employee ID field is required' error was NOT shown when left empty.");
        softAssert.assertAll();
    }

    // ─── TC-03: Employee Name ───────────────────────────────────────────────────
    @Test(priority = 3, dependsOnMethods = "validateEmptyFormSubmission")
    public void validateEmpNameFieldMandatory() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("=== TC-03: Validating Employee Name Field ===");

        boolean errorDisplayed = createUser.isEmpNameErrorDisplayed();
        if (errorDisplayed) {
            System.out.println("  PASS: Error message displayed → 'Name field is required'");
        } else {
            System.out.println("  FAIL: 'Name field is required' error was NOT shown when left empty.");
        }

        softAssert.assertTrue(errorDisplayed,
                "FAIL: 'Name field is required' error was NOT shown when left empty.");
        softAssert.assertAll();
    }

    // ─── TC-04: Mobile Number ───────────────────────────────────────────────────
    @Test(priority = 4, dependsOnMethods = "validateEmptyFormSubmission")
    public void validateMobileFieldMandatory() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("=== TC-04: Validating Mobile Number Field ===");

        boolean errorDisplayed = createUser.isMobileErrorDisplayed();
        if (errorDisplayed) {
            System.out.println("  PASS: Error message displayed → 'Mobile Number field is required'");
        } else {
            System.out.println("  FAIL: 'Mobile Number field is required' error was NOT shown when left empty.");
        }

        softAssert.assertTrue(errorDisplayed,
                "FAIL: 'Mobile Number field is required' error was NOT shown when left empty.");
        softAssert.assertAll();
    }

    // ─── TC-05: Email ───────────────────────────────────────────────────────────
    @Test(priority = 5, dependsOnMethods = "validateEmptyFormSubmission")
    public void validateEmailFieldMandatory() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("=== TC-05: Validating Email Field ===");

        boolean errorDisplayed = createUser.isEmailErrorDisplayed();
        if (errorDisplayed) {
            System.out.println("  PASS: Error message displayed → 'Email field is required'");
        } else {
            System.out.println("  FAIL: 'Email field is required' error was NOT shown when left empty.");
        }

        softAssert.assertTrue(errorDisplayed,
                "FAIL: 'Email field is required' error was NOT shown when left empty.");
        softAssert.assertAll();
    }

    // ─── TC-06: Password ────────────────────────────────────────────────────────
    @Test(priority = 6, dependsOnMethods = "validateEmptyFormSubmission")
    public void validatePasswordFieldMandatory() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("=== TC-06: Validating Password Field ===");

        boolean errorDisplayed = createUser.isPasswordErrorDisplayed();
        if (errorDisplayed) {
            System.out.println("  PASS: Error message displayed → 'Password field is required'");
        } else {
            System.out.println("  FAIL: 'Password field is required' error was NOT shown when left empty.");
        }

        softAssert.assertTrue(errorDisplayed,
                "FAIL: 'Password field is required' error was NOT shown when left empty.");
        softAssert.assertAll();
    }

    // ─── TC-07: Role ────────────────────────────────────────────────────────────
    @Test(priority = 7, dependsOnMethods = "validateEmptyFormSubmission")
    public void validateRoleFieldMandatory() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("=== TC-07: Validating Role Field ===");

        boolean errorDisplayed = createUser.isRoleErrorDisplayed();
        if (errorDisplayed) {
            System.out.println("  PASS: Error message displayed → 'Role is required'");
        } else {
            System.out.println("  FAIL: 'Role is required' error was NOT shown when left unselected.");
        }

        softAssert.assertTrue(errorDisplayed,
                "FAIL: 'Role is required' error was NOT shown when left unselected.");
        softAssert.assertAll();
    }

    // ─── TC-08: Designation ─────────────────────────────────────────────────────
    @Test(priority = 8, dependsOnMethods = "validateEmptyFormSubmission")
    public void validateDesignationFieldMandatory() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("=== TC-08: Validating Designation Field ===");

        boolean errorDisplayed = createUser.isDesignationErrorDisplayed();
        if (errorDisplayed) {
            System.out.println("  PASS: Error message displayed → 'Designation field is required'");
        } else {
            System.out.println("  FAIL: 'Designation field is required' error was NOT shown when left unselected.");
        }

        softAssert.assertTrue(errorDisplayed,
                "FAIL: 'Designation field is required' error was NOT shown when left unselected.");
        softAssert.assertAll();
    }

    // ─── TC-09: Branch ──────────────────────────────────────────────────────────
    @Test(priority = 9, dependsOnMethods = "validateEmptyFormSubmission")
    public void validateBranchFieldMandatory() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("=== TC-09: Validating Branch Field ===");

        boolean errorDisplayed = createUser.isBranchErrorDisplayed();
        if (errorDisplayed) {
            System.out.println("  PASS: Error message displayed → 'Branch is required'");
        } else {
            System.out.println("  FAIL: 'Branch is required' error was NOT shown when left unselected.");
        }

        softAssert.assertTrue(errorDisplayed,
                "FAIL: 'Branch is required' error was NOT shown when left unselected.");
        softAssert.assertAll();
    }
}
