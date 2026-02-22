package test;



import org.testng.annotations.Test;
import pages.LeaveBalancePage;


    public class LeaveBalanceTest extends BaseTest{



        @Test
        public void verify_leaveBalance(){
            LeaveBalancePage lbp=new LeaveBalancePage(driver);
            lbp.clickLeaveMenu();
            lbp.setAddLBal();
            lbp.setEmpId("101");
            lbp.setPaidLeave("10");
            lbp.setCasualLeave("12");
            lbp.setSickLeave("5");
            lbp.selectYears("2026");
            lbp.setSubmit();

        }
    }










