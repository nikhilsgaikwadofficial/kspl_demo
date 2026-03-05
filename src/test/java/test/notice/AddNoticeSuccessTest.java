package test.notice;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.notice.NoticePage;
import test.BaseTest;

public class AddNoticeSuccessTest extends BaseTest {

    @Test
    public void testAddNoticeSuccess() {
        NoticePage noticePage = new NoticePage(driver);
        noticePage.navigateToAddNotice();
        
        noticePage.setTitle("Staff Meeting");
        noticePage.setMessage("Meeting at 4 PM in conference room.");
        noticePage.clickSubmit();
        
        String successMsg = noticePage.getSuccessMessageText();
        Assert.assertTrue(successMsg.contains("success") || successMsg.contains("added"), 
                "Success message mismatch! Actual: " + successMsg);
    }
}
