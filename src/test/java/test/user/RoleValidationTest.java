package test.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.user.UserPage;
import test.BaseTest;

public class RoleValidationTest extends BaseTest {

    @Test
    public void testRoleValidation() {
        UserPage userPage = new UserPage(driver);
        userPage.navigateToAddUser();
        
        userPage.setEmployeeId("TEST_ROLE_" + System.currentTimeMillis());
        userPage.setName("Role Test User");
        userPage.setMobile("9000000001");
        userPage.setEmail("role@test.com");
        userPage.setPassword("Pass@123");
        
        // Skip Role selection
        userPage.selectDesignation("F&B Executive");
        userPage.selectBranch("ghatkopar(S8ul bootcamp)");
        
        userPage.clickSubmit();
        
        String error = userPage.getFieldError(userPage.roleSelect);
        if (error.isEmpty()) error = userPage.getValidationMessage(userPage.roleSelect);
        
        Assert.assertFalse(error.isEmpty(), "Error message should be shown for unselected Role");
    }
}
