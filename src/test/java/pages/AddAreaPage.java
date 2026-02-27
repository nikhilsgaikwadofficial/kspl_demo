package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class AddAreaPage extends BasePage {

    public AddAreaPage(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Navigation locators
    public By master = By.xpath("(//i[@class='fa fa-database '])");
    public By area = By.xpath("(//i[@class='fa fa-map-marked-alt '])");
    public By add = By.xpath("(//i[@class='fa fa-edit '])[1]");

    // Form field locators
    public By state = By.xpath("//select[@id='states']");
    public By branch = By.xpath("//input[@name='name']");
    public By address = By.xpath("//input[@name='address']");
    public By latitude = By.xpath("//input[@name='lat']");
    public By longitude = By.xpath("//input[@name='long']");
    public By workType = By.xpath("//select[@id='work_type']");
    public By startTime = By.xpath("//input[@name='start_time']");
    public By endTime = By.xpath("//input[@name='end_time']");
    public By checkout = By.xpath("//select[@id='enable_force_checkout']");
    public By allowLate = By.xpath("//select[@name='allow_late']");
    public By allowOt = By.xpath("//select[@name='allow_ot']");
    public By radius = By.xpath("//select[@id='enable_radius']");
    public By submit = By.xpath("//button[@id='saveLocationData']");

    // Error locators (based on CreateUserPage pattern)
    public By stateError = By.xpath("//div[contains(text(),'The state field is required')]");
    public By branchError = By.xpath("//div[contains(text(),'The name field is required')]");
    public By addressError = By.xpath("//div[contains(text(),'The address field is required')]");
    public By latitudeError = By.xpath("//div[contains(text(),'The lat field is required')]");
    public By longitudeError = By.xpath("//div[contains(text(),'The long field is required')]");
    public By workTypeError = By.xpath("//div[contains(text(),'The work type field is required')]");
    public By startTimeError = By.xpath("//small[contains(text(),'Start Time is required')]");
    public By endTimeError = By.xpath("//small[contains(text(),'End Time is required')]");
    public By checkoutError = By.xpath("//div[contains(text(),'The enable force checkout field is required')]");
    public By allowLateError = By.xpath("//div[contains(text(),'The allow late field is required')]");
    public By allowOtError = By.xpath("//div[contains(text(),'The allow ot field is required')]");
    public By radiusError = By.xpath("//div[contains(text(),'The enable radius field is required')]");

    public By successMessage = By.xpath("//div[contains(@class,'alert-success')]");
    public By genericErrorMessage = By.xpath("//div[contains(@class,'alert-danger') or contains(@class,'alert-warning')]");

    // ==================== Validation Helper Methods ====================

    private boolean isElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isStateErrorDisplayed() { return isElementDisplayed(stateError); }
    public boolean isBranchErrorDisplayed() { return isElementDisplayed(branchError); }
    public boolean isAddressErrorDisplayed() { return isElementDisplayed(addressError); }
    public boolean isLatitudeErrorDisplayed() { return isElementDisplayed(latitudeError); }
    public boolean isLongitudeErrorDisplayed() { return isElementDisplayed(longitudeError); }
    public boolean isWorkTypeErrorDisplayed() { return isElementDisplayed(workTypeError); }
    public boolean isStartTimeErrorDisplayed() { return isElementDisplayed(startTimeError); }
    public boolean isEndTimeErrorDisplayed() { return isElementDisplayed(endTimeError); }
    public boolean isCheckoutErrorDisplayed() { return isElementDisplayed(checkoutError); }
    public boolean isAllowLateErrorDisplayed() { return isElementDisplayed(allowLateError); }
    public boolean isAllowOtErrorDisplayed() { return isElementDisplayed(allowOtError); }
    public boolean isRadiusErrorDisplayed() { return isElementDisplayed(radiusError); }

    public boolean isSuccessMessageDisplayed() { return isElementDisplayed(successMessage); }
    public boolean isGenericErrorMessageDisplayed() { return isElementDisplayed(genericErrorMessage); }

    // ==================== Navigation and Interaction ====================

    public void clickMaster() {
        WebElement mast = wait.until(ExpectedConditions.visibilityOfElementLocated(master));
        js.executeScript("arguments[0].click();", mast);
    }

    public void clickArea() {
        WebElement areaa = wait.until(ExpectedConditions.visibilityOfElementLocated(area));
        js.executeScript("arguments[0].click();", areaa);
    }

    public void clickAdd() {
        WebElement addr = wait.until(ExpectedConditions.visibilityOfElementLocated(add));
        js.executeScript("arguments[0].click();", addr);
    }

    public void selectState(String stat) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(state));
        if (stat != null && !stat.isEmpty()) {
            new Select(driver.findElement(state)).selectByVisibleText(stat);
        }
    }

    public void setBranch(String bran) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(branch));
        driver.findElement(branch).sendKeys(bran);
    }

    public void setAddress(String addres) {
        WebElement addresss = wait.until(ExpectedConditions.visibilityOfElementLocated(address));
        addresss.click();
        addresss.sendKeys(addres);
    }

    public void setLatitude(String latitudee) {
        WebElement lati = wait.until(ExpectedConditions.visibilityOfElementLocated(latitude));
        lati.click();
        lati.sendKeys(latitudee);
    }

    public void setLongitude(String longi) {
        WebElement loni = wait.until(ExpectedConditions.visibilityOfElementLocated(longitude));
        loni.click();
        loni.sendKeys(longi);
    }

    public void selectWorkType(String workType1) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(workType));
        if (workType1 != null && !workType1.isEmpty()) {
            new Select(driver.findElement(workType)).selectByVisibleText(workType1);
        }
    }

    public void setStartTime(String start) { setTime(startTime, start); }
    public void setEndTime(String end) { setTime(endTime, end); }

    public void selectCheckout(String yesno) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkout));
        if (yesno != null && !yesno.isEmpty()) {
            new Select(driver.findElement(checkout)).selectByVisibleText(yesno);
        }
    }

    public void selectAllowLate(String allow) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(allowLate));
        if (allow != null && !allow.isEmpty()) {
            new Select(driver.findElement(allowLate)).selectByVisibleText(allow);
        }
    }

    public void selectAllowOt(String ot) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(allowOt));
        if (ot != null && !ot.isEmpty()) {
            new Select(driver.findElement(allowOt)).selectByVisibleText(ot);
        }
    }

    public void selectRadius(String radios) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(radius));
        if (radios != null && !radios.isEmpty()) {
            new Select(driver.findElement(radius)).selectByVisibleText(radios);
        }
    }

    public void clickSubmit() {
        WebElement saveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(submit));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);
        js.executeScript("arguments[0].click();", saveBtn);
    }

    private void setTime(By locator, String rawTime) {
        String time = normalizeToHHmm(rawTime);
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.BACK_SPACE);
        el.sendKeys(time);
        String after = el.getAttribute("value");
        if (after == null || after.isBlank()) {
            js.executeScript(
                    "arguments[0].value = arguments[1];" +
                    "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                    "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", el, time);
        }
    }

    private String normalizeToHHmm(String input) {
        if (input == null) return "";
        String s = input.trim();
        if (s.isEmpty()) return s;
        s = s.replace('.', ':');
        if (s.matches("^\\d{1,2}$")) s = s + ":00";
        if (s.matches("^\\d{1,2}:\\d{1}$")) s = s + "0";
        if (s.matches("^\\d{1,2}:\\d{2}$")) {
            String[] parts = s.split(":");
            String hh = parts[0];
            String mm = parts[1];
            if (hh.length() == 1) hh = "0" + hh;
            return hh + ":" + mm;
        }
        if (s.matches("^\\d{1,2}:\\d{2}:\\d{2}$")) {
            String[] parts = s.split(":");
            String hh = parts[0];
            if (hh.length() == 1) hh = "0" + hh;
            return hh + ":" + parts[1] + ":" + parts[2];
        }
        return s;
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

    public boolean assertStateField() { return isStateErrorDisplayed(); }
    public boolean assertBranchField() { return isBranchErrorDisplayed(); }
    public boolean assertAddressField() { return isAddressErrorDisplayed(); }
    public boolean assertLatitudeField() { return isLatitudeErrorDisplayed(); }
    public boolean assertLongitudeField() { return isLongitudeErrorDisplayed(); }
    public boolean assertWorkTypeField() { return isWorkTypeErrorDisplayed(); }
    public boolean assertStartTimeField() { return isStartTimeErrorDisplayed(); }
    public boolean assertEndTimeField() { return isEndTimeErrorDisplayed(); }
    public boolean assertCheckoutField() { return isCheckoutErrorDisplayed(); }
    public boolean assertAllowLateField() { return isAllowLateErrorDisplayed(); }
    public boolean assertAllowOtField() { return isAllowOtErrorDisplayed(); }
    public boolean assertRadiusField() { return isRadiusErrorDisplayed(); }
}
