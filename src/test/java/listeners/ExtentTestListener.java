package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportsManager;

public class ExtentTestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentReportsManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        if (description == null || description.isEmpty()) {
            description = testName;
        }
        ExtentTest test = ExtentReportsManager.startTest(testName, description);
        
        // Add test parameters if available
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0) {
            StringBuilder params = new StringBuilder("Test Parameters: ");
            for (Object param : parameters) {
                params.append(param).append(" ");
            }
            test.info(MarkupHelper.createLabel(params.toString(), ExtentColor.BLUE));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentReportsManager.getTest();
        if (test != null) {
            test.log(Status.PASS, MarkupHelper.createLabel("Test Case PASSED", ExtentColor.GREEN));
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentReportsManager.getTest();
        if (test != null) {
            test.log(Status.FAIL, MarkupHelper.createLabel("Test Case FAILED", ExtentColor.RED));
            test.fail(result.getThrowable());
            
            // Add failure details
            if (result.getThrowable() != null) {
                test.fail(result.getThrowable().getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentReportsManager.getTest();
        if (test != null) {
            test.log(Status.SKIP, MarkupHelper.createLabel("Test Case SKIPPED", ExtentColor.YELLOW));
            if (result.getThrowable() != null) {
                test.skip(result.getThrowable());
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportsManager.endTest();
    }
}
