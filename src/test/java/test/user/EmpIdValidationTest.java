package test.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.user.UserPage;
import test.BaseTest;

public class EmpIdValidationTest extends BaseTest {

    @Test
    public void testEmpIdValidation() {
        UserPage userPage = new UserPage(driver);
        userPage.navigateToAddUser();
        
        // Leave Emp ID empty, fill others
        userPage.setName("Test User");
        userPage.setMobile("9876543210");
        userPage.setEmail("test@user.com");
        userPage.setPassword("Pass@123");
        userPage.selectRole("Level 1");
        userPage.selectDesignation("F&B Executive");
        userPage.selectBranch("ghatkopar(S8ul bootcamp)");
        
        userPage.clickSubmit();
        
        String error = userPage.getFieldError(userPage.empIdInput);
        if (error.isEmpty()) error = userPage.getValidationMessage(userPage.empIdInput);
        
        Assert.assertFalse(error.isEmpty(), "Error message should be shown for empty Employee ID");
        Assert.assertTrue(error.toLowerCase().contains("required") || error.toLowerCase().contains("fill"), 
                "Error message should indicate Employee ID is required: " + error);
    }
}
