package test.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.user.UserPage;
import test.BaseTest;

public class EmpNameValidationTest extends BaseTest {

    @Test
    public void testEmpNameValidation() {
        UserPage userPage = new UserPage(driver);
        userPage.navigateToAddUser();
        
        userPage.setEmployeeId("TEST_ID");
        // Leave Name empty
        userPage.setMobile("9876543210");
        userPage.setEmail("test@user.com");
        userPage.setPassword("Pass@123");
        userPage.selectRole("Level 1");
        userPage.selectDesignation("F&B Executive");
        userPage.selectBranch("ghatkopar(S8ul bootcamp)");
        
        userPage.clickSubmit();
        
        String error = userPage.getFieldError(userPage.nameInput);
        if (error.isEmpty()) error = userPage.getValidationMessage(userPage.nameInput);
        
        Assert.assertFalse(error.isEmpty(), "Error message should be shown for empty Name");
        Assert.assertTrue(error.toLowerCase().contains("required") || error.toLowerCase().contains("fill"), 
                "Error message should indicate Name is required: " + error);
    }
}
