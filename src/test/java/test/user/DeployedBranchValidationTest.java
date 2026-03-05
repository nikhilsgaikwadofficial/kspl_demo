package test.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.user.UserPage;
import test.BaseTest;

public class DeployedBranchValidationTest extends BaseTest {

    @Test
    public void testDeployedBranchValidation() {
        UserPage userPage = new UserPage(driver);
        userPage.navigateToAddUser();
        
        userPage.setEmployeeId("TEST_BRANCH_" + System.currentTimeMillis());
        userPage.setName("Branch Test User");
        userPage.setMobile("9000000003");
        userPage.setEmail("branch@test.com");
        userPage.setPassword("Pass@123");
        
        userPage.selectRole("Level 1");
        userPage.selectDesignation("F&B Executive");
        // Skip Deployed Branch selection
        
        userPage.clickSubmit();
        
        String error = userPage.getFieldError(userPage.branchSelect);
        if (error.isEmpty()) error = userPage.getValidationMessage(userPage.branchSelect);
        
        Assert.assertFalse(error.isEmpty(), "Error message should be shown for unselected Deployed Branch");
    }
}
