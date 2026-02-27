package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AreaValidationPage extends BasePage {

    public AreaValidationPage(WebDriver driver) {
        super(driver);
    }


    By master = By.xpath("(//i[@class='fa fa-database '])");
    By area = By.xpath("(//i[@class='fa fa-map-marked-alt '])");
    By add = By.xpath("(//i[@class='fa fa-edit '])[1]");
    public By state = By.xpath("//select[@id='states']");
   public By branch = By.xpath("//input[@name='name']");
  public   By address = By.xpath("//input[@name='address']");
   public By latitudeField = By.xpath("//input[@name='lat']");
    public By longitudeField = By.xpath("//input[@name='long']");
    public By workType = By.xpath("//select[@id='work_type']");
    public By startTime = By.xpath("//input[@name='start_time']");
    public By endTime = By.xpath("//input[@name='end_time']");
    public By checkout = By.xpath("//select[@id='enable_force_checkout']");
    public By allowLate = By.xpath("//select[@name='allow_late']");
    public By allowOt = By.xpath("//select[@name='allow_ot']");
    public By radius = By.xpath("//select[@id='enable_radius']");
    public By saveButton = By.xpath("//button[@id='saveLocationData']");
    public By errorMessages = By.xpath("//div[contains(@class,'error')]");



    public void navigateToAddArea() {
        WebElement mast = wait.until(ExpectedConditions.visibilityOfElementLocated(master));
        js.executeScript("arguments[0].click();", mast);

        WebElement ar = wait.until(ExpectedConditions.visibilityOfElementLocated(area));
        js.executeScript("arguments[0].click();", ar);

        WebElement ad = wait.until(ExpectedConditions.visibilityOfElementLocated(add));
        js.executeScript("arguments[0].click();", ad);
    }

    public void clickSave() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        js.executeScript("arguments[0].scrollIntoView(true);", saveBtn);
        js.executeScript("arguments[0].click();", saveBtn);
    }

   

    public boolean isFieldDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFieldEmpty(By locator) {
        WebElement element = driver.findElement(locator);
        if (element.getTagName().equalsIgnoreCase("select")) {
            String value = new Select(element).getFirstSelectedOption().getAttribute("value");
            return value == null || value.isEmpty();
        }
        String value = element.getAttribute("value");
        return value == null || value.isEmpty();
    }

    public boolean isFieldRequired(By locator) {
        String required = driver.findElement(locator).getAttribute("required");
        return required != null;
    }

    public String getValidationMessage(By locator) {
        WebElement element = driver.findElement(locator);
        return (String) js.executeScript("return arguments[0].validationMessage;", element);
    }

    public boolean hasFieldError(By locator) {
        try {
            WebElement field = driver.findElement(locator);
            WebElement parent = field.findElement(By.xpath(".."));
            List<WebElement> errors = parent.findElements(
                    By.xpath(".//*[contains(@class,'error') or contains(@class,'invalid-feedback')]"));
            for (WebElement error : errors) {
                if (error.isDisplayed() && !error.getText().isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getFieldErrorText(By locator) {
        try {
            WebElement field = driver.findElement(locator);
            WebElement parent = field;
            // Search up to 3 levels for an error message div
            for (int i = 0; i < 3; i++) {
                parent = parent.findElement(By.xpath(".."));
                List<WebElement> errors = parent.findElements(
                        By.xpath(".//*[contains(@class,'error') or contains(@class,'invalid-feedback') or contains(@class,'text-danger')]"));
                for (WebElement error : errors) {
                    if (error.isDisplayed() && !error.getText().trim().isEmpty()) {
                        return error.getText().trim();
                    }
                }
            }
        } catch (Exception e) {}
        return "";
    }

    public String getDefaultDropdownText(By locator) {
        return new Select(driver.findElement(locator)).getFirstSelectedOption().getText();
    }

    public int getErrorCount() {
        List<WebElement> errors = driver.findElements(errorMessages);
        int count = 0;
        for (WebElement e : errors) {
            if (e.isDisplayed()) count++;
        }
        return count;
    }



    public void enterBranch(String value) {
        driver.findElement(branch).sendKeys(value);
    }

    public void enterAddress(String value) {
        driver.findElement(address).sendKeys(value);
    }

    public void enterLatitude(String value) {
        driver.findElement(latitudeField).sendKeys(value);
    }

    public void enterLongitude(String value) {
        driver.findElement(longitudeField).sendKeys(value);
    }

    public void enterStartTime(String value) {
        driver.findElement(startTime).sendKeys(value);
    }

    public void enterEndTime(String value) {
        driver.findElement(endTime).sendKeys(value);
    }

    public void selectState(String visibleText) {
        if (visibleText == null || visibleText.isEmpty()) return;
        new Select(driver.findElement(state)).selectByVisibleText(visibleText);
    }

    public void selectWorkType(String visibleText) {
        if (visibleText == null || visibleText.isEmpty()) return;
        new Select(driver.findElement(workType)).selectByVisibleText(visibleText);
    }

    public void selectCheckout(String visibleText) {
        if (visibleText == null || visibleText.isEmpty()) return;
        new Select(driver.findElement(checkout)).selectByVisibleText(visibleText);
    }

    public void selectAllowLate(String visibleText) {
        if (visibleText == null || visibleText.isEmpty()) return;
        new Select(driver.findElement(allowLate)).selectByVisibleText(visibleText);
    }

    public void selectAllowOt(String visibleText) {
        if (visibleText == null || visibleText.isEmpty()) return;
        new Select(driver.findElement(allowOt)).selectByVisibleText(visibleText);
    }

    public void selectRadius(String visibleText) {
        if (visibleText == null || visibleText.isEmpty()) return;
        new Select(driver.findElement(radius)).selectByVisibleText(visibleText);
    }



    public String getBranchValue() {
        return driver.findElement(branch).getAttribute("value");
    }

    public String getAddressValue() {
        return driver.findElement(address).getAttribute("value");
    }

    public String getLatitudeValue() {
        return driver.findElement(latitudeField).getAttribute("value");
    }

    public String getLongitudeValue() {
        return driver.findElement(longitudeField).getAttribute("value");
    }

    public String getStartTimeValue() {
        return driver.findElement(startTime).getAttribute("value");
    }

    public String getEndTimeValue() {
        return driver.findElement(endTime).getAttribute("value");
    }

    // ==================== Field-Level Assertion Methods ====================


    public boolean assertStateField() {
        return isFieldDisplayed(state) && (isFieldEmpty(state) || hasFieldError(state) || !getValidationMessage(state).isEmpty());
    }


    public boolean assertBranchField() {
        return isFieldDisplayed(branch) && (isFieldEmpty(branch) || hasFieldError(branch) || !getValidationMessage(branch).isEmpty());
    }


    public boolean assertAddressField() {
        return isFieldDisplayed(address) && (isFieldEmpty(address) || hasFieldError(address) || !getValidationMessage(address).isEmpty());
    }


    public boolean assertLatitudeField() {
        return isFieldDisplayed(latitudeField) && (isFieldEmpty(latitudeField) || hasFieldError(latitudeField) || !getValidationMessage(latitudeField).isEmpty());
    }


    public boolean assertLongitudeField() {
        return isFieldDisplayed(longitudeField) && (isFieldEmpty(longitudeField) || hasFieldError(longitudeField) || !getValidationMessage(longitudeField).isEmpty());
    }


    public boolean assertWorkTypeField() {
        return isFieldDisplayed(workType) && (isFieldEmpty(workType) || hasFieldError(workType) || !getValidationMessage(workType).isEmpty());
    }


    public boolean assertStartTimeField() {
        return isFieldDisplayed(startTime) && (isFieldEmpty(startTime) || hasFieldError(startTime) || !getValidationMessage(startTime).isEmpty());
    }


    public boolean assertEndTimeField() {
        return isFieldDisplayed(endTime) && (isFieldEmpty(endTime) || hasFieldError(endTime) || !getValidationMessage(endTime).isEmpty());
    }


    public boolean assertCheckoutField() {
        return isFieldDisplayed(checkout) && (isFieldEmpty(checkout) || hasFieldError(checkout) || !getValidationMessage(checkout).isEmpty());
    }


    public boolean assertAllowLateField() {
        return isFieldDisplayed(allowLate) && (isFieldEmpty(allowLate) || hasFieldError(allowLate) || !getValidationMessage(allowLate).isEmpty());
    }


    public boolean assertAllowOtField() {
        return isFieldDisplayed(allowOt) && (isFieldEmpty(allowOt) || hasFieldError(allowOt) || !getValidationMessage(allowOt).isEmpty());
    }


    public boolean assertRadiusField() {
        return isFieldDisplayed(radius) && (isFieldEmpty(radius) || hasFieldError(radius) || !getValidationMessage(radius).isEmpty());
    }
}
