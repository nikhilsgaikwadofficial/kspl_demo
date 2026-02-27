package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AreaValidationPage;

public class AreaValidationTest extends BaseTest {

    AreaValidationPage areaPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void navigateToForm() {
        areaPage = new AreaValidationPage(driver);
        areaPage.navigateToAddArea();
    }

    @DataProvider(name = "areaData")
    public Object[][] getAreaData() {
        return new Object[][]{
            // Scenario, State, Branch, Address, Lat, Long, WorkType, Start, End, Checkout, Late, OT, Radius, ExpectedField, ExpectedMsg
            {"Empty State", "", "", "", "", "", "Shift", "", "", "No", "No", "No", "No", "State", "State is required."},
            {"Empty Branch", "Bihar", "", "", "", "", "Shift", "", "", "No", "No", "No", "No", "Branch", "Name / Branch Id is required."},
            {"Empty Address", "Bihar", "Kanishka Software", "", "", "", "Shift", "", "", "No", "No", "No", "No", "Address", "Address is required."},
            {"Empty Latitude", "Bihar", "Kanishka Software", "Vasai", "", "", "Shift", "", "", "No", "No", "No", "No", "Latitude", "Latitude is required."},
            {"Empty Longitude", "Bihar", "Kanishka Software", "Vasai", "22.808244", "", "Shift", "", "", "No", "No", "No", "No", "Longitude", "Longitude is required."},
            {"Empty Start Time", "Bihar", "Kanishka Software", "Vasai", "22.808244", "92.0808273", "Shift", "", "", "No", "No", "No", "No", "StartTime", "Start Time is required."},
            {"Empty End Time", "Bihar", "Kanishka Software", "Vasai", "22.808244", "92.0808273", "Shift", "09:30", "", "No", "No", "No", "No", "EndTime", "End Time is required."}
        };
    }

    @Test(dataProvider = "areaData")
    public void validateAreaFields(String scenario, String stateVal, String branchVal, String addressVal, 
                                 String latVal, String longVal, String workTypeVal, String startVal, 
                                 String endVal, String checkoutVal, String lateVal, String otVal, 
                                 String radiusVal, String expectedField, String expectedMsg) {
        SoftAssert softAssert = new SoftAssert();
        
        driver.navigate().refresh();
        areaPage.navigateToAddArea();

        if (!stateVal.isEmpty()) areaPage.selectState(stateVal);
        if (!branchVal.isEmpty()) areaPage.enterBranch(branchVal);
        if (!addressVal.isEmpty()) areaPage.enterAddress(addressVal);
        if (!latVal.isEmpty()) areaPage.enterLatitude(latVal);
        if (!longVal.isEmpty()) areaPage.enterLongitude(longVal);
        if (!workTypeVal.isEmpty()) areaPage.selectWorkType(workTypeVal);
        if (!startVal.isEmpty()) areaPage.enterStartTime(startVal);
        if (!endVal.isEmpty()) areaPage.enterEndTime(endVal);
        if (!checkoutVal.isEmpty()) areaPage.selectCheckout(checkoutVal);
        if (!lateVal.isEmpty()) areaPage.selectAllowLate(lateVal);
        if (!otVal.isEmpty()) areaPage.selectAllowOt(otVal);
        if (!radiusVal.isEmpty()) areaPage.selectRadius(radiusVal);

        areaPage.clickSave();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        String actualMsg = "";
        switch (expectedField) {
            case "State": actualMsg = areaPage.getFieldErrorText(areaPage.state); break;
            case "Branch": actualMsg = areaPage.getFieldErrorText(areaPage.branch); break;
            case "Address": actualMsg = areaPage.getFieldErrorText(areaPage.address); break;
            case "Latitude": actualMsg = areaPage.getFieldErrorText(areaPage.latitudeField); break;
            case "Longitude": actualMsg = areaPage.getFieldErrorText(areaPage.longitudeField); break;
            case "StartTime": actualMsg = areaPage.getFieldErrorText(areaPage.startTime); break;
            case "EndTime": actualMsg = areaPage.getFieldErrorText(areaPage.endTime); break;
        }

        if (actualMsg.isEmpty()) {
            switch (expectedField) {
                case "State": actualMsg = areaPage.getValidationMessage(areaPage.state); break;
                case "Branch": actualMsg = areaPage.getValidationMessage(areaPage.branch); break;
                case "Address": actualMsg = areaPage.getValidationMessage(areaPage.address); break;
                case "Latitude": actualMsg = areaPage.getValidationMessage(areaPage.latitudeField); break;
                case "Longitude": actualMsg = areaPage.getValidationMessage(areaPage.longitudeField); break;
                case "StartTime": actualMsg = areaPage.getValidationMessage(areaPage.startTime); break;
                case "EndTime": actualMsg = areaPage.getValidationMessage(areaPage.endTime); break;
            }
        }

        softAssert.assertEquals(actualMsg, expectedMsg, "Validation message mismatch for " + expectedField + " in scenario: " + scenario);
        softAssert.assertAll();
    }
}
