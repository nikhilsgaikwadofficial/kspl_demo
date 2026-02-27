package test;

import org.testng.annotations.Test;
import pages.DeleteAreaPage;

public class DeleteAreaTest extends BaseTest {
    @Test

    public void verify_deleteArea(){
        DeleteAreaPage dap=new DeleteAreaPage(driver);
        dap.clickMaster();
        dap.clickArea();
        dap.clickList();
        dap.deleteAreaAndAcceptAlert();
    }
}
