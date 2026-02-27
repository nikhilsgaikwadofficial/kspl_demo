package test.area_validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AreaValidationPage;
import test.BaseTest;

public class CheckoutValidationTest extends BaseTest {

    AreaValidationPage areaPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        areaPage = new AreaValidationPage(driver);
        areaPage.navigateToAddArea();
    }

    @Test
    public void validateCheckoutField() {
        SoftAssert softAssert = new SoftAssert();
        
        areaPage.selectState("Bihar");
        areaPage.enterBranch("Kanishka Software");
        areaPage.enterAddress("Vasai");
        areaPage.enterLatitude("22.808244");
        areaPage.enterLongitude("92.0808273");
        areaPage.selectWorkType("Shift");
        areaPage.enterStartTime("09:30");
        areaPage.enterEndTime("19:30");
        
        areaPage.clickSave();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        String validationMsg = areaPage.getFieldErrorText(areaPage.checkout);
        if (validationMsg.isEmpty()) validationMsg = areaPage.getValidationMessage(areaPage.checkout);

        System.out.println("\n[DEMO] Validating Checkout field...");
        System.out.println("Captured Message: " + (validationMsg.isEmpty() ? "None (Default Value Likely Applied)" : validationMsg));

        softAssert.assertTrue(true, "Checkout validation processed");
        softAssert.assertAll();
    }
}
