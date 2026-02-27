package test.user_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddUserValidationPage;
import test.BaseTest;

public class EmpNameValidationTest extends BaseTest {

    AddUserValidationPage validationPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        validationPage = new AddUserValidationPage(driver);
        validationPage.navigateToAddUser();
    }

    @Test
    public void validateEmpNameField() {
        SoftAssert softAssert = new SoftAssert();
        
        // Trigger validation by clicking submit with Name empty
        validationPage.clickSubmit();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        boolean errorDisplayed = validationPage.isEmpNameErrorDisplayed();
        String validationMsg = validationPage.getFieldErrorText(validationPage.empName);
        if (validationMsg.isEmpty()) validationMsg = validationPage.getValidationMessage(validationPage.empName);

        System.out.println("\n[DEMO] Validating Employee Name field...");
        System.out.println("Error Displayed (HTML): " + errorDisplayed);
        System.out.println("Captured Validation Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertTrue(errorDisplayed || !validationMsg.isEmpty(), "Employee Name validation error should be displayed");
        softAssert.assertAll();
    }
}
