package test.notice;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.notice.NoticePage;
import test.BaseTest;

public class SpecialCharTitleTest extends BaseTest {

    @Test
    public void testSpecialCharTitle() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.navigateToAddNotice();
        
        String specialTitle = "Urgent! 🚀 #Notice-2026";
        noticePage.setTitle(specialTitle);
        noticePage.setMessage("This notice contains special characters in the title.");
        noticePage.clickSubmit();
        
        String successMsg = noticePage.getSuccessMessageText();
        Assert.assertTrue(successMsg.contains("success") || successMsg.contains("added"), 
                "Success message mismatch for special characters! Actual: " + successMsg);
    }
}
