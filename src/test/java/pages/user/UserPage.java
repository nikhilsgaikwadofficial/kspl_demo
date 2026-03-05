package pages.user;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.BasePage;
import java.time.Duration;
import java.util.List;

public class UserPage extends BasePage {

    public UserPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    public By masterMenu = By.xpath("(//i[@class='fa fa-database '])");
    public By userMenu = By.xpath("(//i[@class='fa fa-user ']) ");
    public By addUserLink = By.xpath("//a[contains(@href,'/users/add')]");
    
    public By empIdInput = By.xpath("//input[@name='emp_id']");
    public By nameInput = By.xpath("//input[@name='name']");
    public By mobileInput = By.xpath("//input[@name='mobile_number']");
    public By emailInput = By.xpath("//input[@name='email']");
    public By passwordInput = By.xpath("//input[@id='password']");
    public By roleSelect = By.xpath("//select[@id='role']");
    public By designationSelect = By.xpath("//select[@id='designation']");
    public By branchSelect = By.xpath("//select[@name='area_id'][1]");
    public By submitButton = By.xpath("//button[@id='btn-admin-member-submit']");

    public void navigateToAddUser() {
        WebElement master = wait.until(ExpectedConditions.elementToBeClickable(masterMenu));
        js.executeScript("arguments[0].click();", master);
        
        WebElement user = wait.until(ExpectedConditions.elementToBeClickable(userMenu));
        js.executeScript("arguments[0].click();", user);
        
        WebElement add = wait.until(ExpectedConditions.elementToBeClickable(addUserLink));
        js.executeScript("arguments[0].click();", add);
    }

    public void setEmployeeId(String id) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(empIdInput));
        el.clear();
        el.sendKeys(id);
    }

    public void setName(String name) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        el.clear();
        el.sendKeys(name);
    }

    public void setMobile(String mobile) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileInput));
        el.clear();
        el.sendKeys(mobile);
    }

    public void setEmail(String email) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        el.clear();
        el.sendKeys(email);
    }

    public void setPassword(String password) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        el.clear();
        el.sendKeys(password);
    }

    public void selectRole(String role) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(roleSelect))).selectByVisibleText(role);
    }

    public void selectDesignation(String designation) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(designationSelect))).selectByVisibleText(designation);
    }

    public void selectBranch(String branch) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(branchSelect));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        new Select(el).selectByVisibleText(branch);
    }

    public void clickSubmit() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        js.executeScript("arguments[0].click();", btn);
    }

    public String getFieldError(By locator) {
        try {
            WebElement field = driver.findElement(locator);
            WebElement parent = field;
            for (int i = 0; i < 2; i++) {
                parent = parent.findElement(By.xpath(".."));
                List<WebElement> errors = parent.findElements(
                        By.xpath(".//*[not(self::select)][contains(@class,'error') or contains(@class,'invalid-feedback') or contains(@class,'invalid') or contains(@class,'text-danger')]"));
                for (WebElement error : errors) {
                    if (error.isDisplayed() && !error.getText().trim().isEmpty()) {
                        String text = error.getText().trim();
                        if (text.split("\n").length < 3) {
                            return text;
                        }
                    }
                }
            }
        } catch (Exception e) {}
        return "";
    }

    public String getValidationMessage(By locator) {
        try {
            return (String) js.executeScript("return arguments[0].validationMessage;", driver.findElement(locator));
        } catch (Exception e) {
            return "";
        }
    }

    public String getSuccessMessageText() {
        try {
            By successMsg = By.xpath("//div[contains(@class,'alert-success')]");
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
            return msg.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getGenericErrorMessage() {
        try {
            By genericError = By.xpath("//div[contains(@class,'alert-danger') or contains(@class,'alert-warning')]");
            WebElement msg = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(genericError));
            return msg.getText();
        } catch (Exception e) {}
        return "";
    }
}
