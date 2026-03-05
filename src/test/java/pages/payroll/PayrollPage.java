package pages.payroll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class PayrollPage extends BasePage {

    public PayrollPage(WebDriver driver) {
        super(driver);
    }

    // Locators - Settings
    By payrollManagementMenu = By.xpath("//p[normalize-space()='Payroll Management']//i[@class='fas fa-angle-left right']");
    By payrollSettingsSubMenu = By.xpath("//a[contains(@href, 'payroll/settings')]");
    public By pfEnableToggle = By.xpath("//input[@name='enable_pf']/following-sibling::label");
    public By pfCutoffLimit = By.id("cutoff_limit");
    public By pfDeduction = By.id("pf_deduction");
    public By updateBtn = By.id("submitBtn");

    // Locators - Reports
    By payrollReportsSubMenu = By.xpath("//a[contains(@href, 'payroll-monthwise')]");
    public By monthPicker = By.id("monthly");
    public By branchDropdown = By.id("branch_id");
    public By filterBtn = By.id("filter");
    public By exportExcel = By.className("buttons-excel");

    public void navigateToPayrollSettings() {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(payrollManagementMenu));
        js.executeScript("arguments[0].click();", menu);
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(payrollSettingsSubMenu));
        js.executeScript("arguments[0].click();", subMenu);
    }

    public void navigateToPayrollReports() {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(payrollManagementMenu));
        js.executeScript("arguments[0].click();", menu);
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(payrollReportsSubMenu));
        js.executeScript("arguments[0].click();", subMenu);
    }

    public void enablePf() {
        wait.until(ExpectedConditions.elementToBeClickable(pfEnableToggle)).click();
    }

    public void setPfCutoffLimit(String limit) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(pfCutoffLimit));
        el.clear();
        el.sendKeys(limit);
    }

    public void setPfDeduction(String deduction) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(pfDeduction));
        el.clear();
        el.sendKeys(deduction);
    }

    public void clickUpdate() {
        driver.findElement(updateBtn).click();
    }

    public void selectMonth(String monthYear) {
        WebElement el = driver.findElement(monthPicker);
        js.executeScript("arguments[0].value = arguments[1];", el, monthYear);
    }

    public void selectBranch(String branchName) {
        new Select(driver.findElement(branchDropdown)).selectByVisibleText(branchName);
    }

    public void clickFilter() {
        driver.findElement(filterBtn).click();
    }

    public void exportToExcel() {
        wait.until(ExpectedConditions.elementToBeClickable(exportExcel)).click();
    }

    public String getFieldError(By locator) {
        try {
            WebElement field = driver.findElement(locator);
            WebElement parent = field.findElement(By.xpath(".."));
            WebElement error = parent.findElement(By.xpath(".//*[contains(@class,'error') or contains(@class,'invalid-feedback')]"));
            if (error.isDisplayed()) return error.getText();
        } catch (Exception e) {}
        return "";
    }

    public String getValidationMessage(By locator) {
        return (String) js.executeScript("return arguments[0].validationMessage;", driver.findElement(locator));
    }

    public String getSuccessMessageText() {
        By successMsg = By.xpath("//div[contains(@class,'alert-success')]");
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return msg.getText();
    }
}
