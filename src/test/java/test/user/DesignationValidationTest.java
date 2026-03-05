package test.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.user.UserPage;
import test.BaseTest;

public class DesignationValidationTest extends BaseTest {

    @Test
    public void testDesignationValidation() {
        UserPage userPage = new UserPage(driver);
        userPage.navigateToAddUser();
        
        userPage.setEmployeeId("TEST_DESIG_" + System.currentTimeMillis());
        userPage.setName("Designation Test User");
        userPage.setMobile("9000000002");
        userPage.setEmail("desig@test.com");
        userPage.setPassword("Pass@123");
        
        userPage.selectRole("Level 1");
        // Skip Designation selection
        userPage.selectBranch("ghatkopar(S8ul bootcamp)");
        
        userPage.clickSubmit();
        
        String error = userPage.getFieldError(userPage.designationSelect);
        if (error.isEmpty()) error = userPage.getValidationMessage(userPage.designationSelect);
        
        Assert.assertFalse(error.isEmpty(), "Error message should be shown for unselected Designation");
    }
}
