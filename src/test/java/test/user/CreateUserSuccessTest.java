package test.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.user.UserPage;
import test.BaseTest;

public class CreateUserSuccessTest extends BaseTest {

    @Test
    public void testCreateUserSuccess() {
        UserPage userPage = new UserPage(driver);
        userPage.navigateToAddUser();
        
        userPage.setEmployeeId("MOD_" + System.currentTimeMillis());
        userPage.setName("Modular User");
        userPage.setMobile("9876543210");
        userPage.setEmail("mod@user.com");
        userPage.setPassword("Pass@123");
        
        userPage.selectRole("Level 1");
        userPage.selectDesignation("F&B Executive");
        userPage.selectBranch("ghatkopar(S8ul bootcamp)");
        
        userPage.clickSubmit();
        
        String successMsg = userPage.getSuccessMessageText();
        Assert.assertFalse(successMsg.isEmpty(), "Success message should be displayed after creating user");
        Assert.assertTrue(successMsg.toLowerCase().contains("success") || successMsg.toLowerCase().contains("created"), 
                "Success message should indicate successful creation: " + successMsg);
    }
}
