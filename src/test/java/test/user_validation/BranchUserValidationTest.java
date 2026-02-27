package test.user_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddUserValidationPage;
import test.BaseTest;

public class BranchUserValidationTest extends BaseTest {

    AddUserValidationPage validationPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        validationPage = new AddUserValidationPage(driver);
        validationPage.navigateToAddUser();
    }

    @Test
    public void validateBranchField() {
        SoftAssert softAssert = new SoftAssert();
        

        validationPage.clickSubmit();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        boolean errorDisplayed = validationPage.isBranchErrorDisplayed();
        String validationMsg = validationPage.getFieldErrorText(validationPage.branch);
        if (validationMsg.isEmpty()) validationMsg = validationPage.getValidationMessage(validationPage.branch);

        System.out.println("\n[DEMO] Validating Branch field...");
        System.out.println("Error Displayed : " + errorDisplayed);
        System.out.println("Captured Validation Message: " + (validationMsg.isEmpty() ? "None" : validationMsg));

        softAssert.assertTrue(errorDisplayed || !validationMsg.isEmpty(), "Branch validation error should be displayed");
        softAssert.assertAll();
    }
}
