package pages.area;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.BasePage;
import java.time.Duration;
import java.util.List;

public class AreaPage extends BasePage {

    public AreaPage(WebDriver driver) {
        super(driver);
    }

    public By masterMenu = By.xpath("(//i[@class='fa fa-database '])");
    public By areaMenu = By.xpath("(//i[@class='fa fa-map-marked-alt '])");
    public By addAreaLink = By.xpath("(//i[@class='fa fa-edit '])[1]");

    public By stateSelect = By.xpath("//select[@id='states']");
    public By branchInput = By.xpath("//input[@name='name']");
    public By addressInput = By.xpath("//input[@name='address']");
    public By latitudeInput = By.xpath("//input[@name='lat']");
    public By longitudeInput = By.xpath("//input[@name='long']");
    public By workTypeSelect = By.xpath("//select[@id='work_type']");
    public By startTimeInput = By.xpath("//input[@name='start_time']");
    public By endTimeInput = By.xpath("//input[@name='end_time']");
    public By genericErrorMessage = By.xpath("//div[contains(@class,'alert-danger') or contains(@class,'alert-warning')]");
    public By submitButton = By.xpath("//button[@id='saveLocationData']");

    public void navigateToAddArea() {
        WebElement master = wait.until(ExpectedConditions.elementToBeClickable(masterMenu));
        js.executeScript("arguments[0].click();", master);
        
        WebElement area = wait.until(ExpectedConditions.elementToBeClickable(areaMenu));
        js.executeScript("arguments[0].click();", area);
        
        WebElement add = wait.until(ExpectedConditions.elementToBeClickable(addAreaLink));
        js.executeScript("arguments[0].click();", add);
    }

    public void selectState(String stateName) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(stateSelect))).selectByVisibleText(stateName);
    }

    public void setBranch(String branchName) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(branchInput));
        el.clear();
        el.sendKeys(branchName);
    }

    public void setAddress(String address) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput));
        el.clear();
        el.sendKeys(address);
    }

    public void setLatitude(String lat) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(latitudeInput));
        el.clear();
        el.sendKeys(lat);
    }

    public void setLongitude(String lon) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(longitudeInput));
        el.clear();
        el.sendKeys(lon);
    }

    public void selectWorkType(String workType) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(workTypeSelect))).selectByVisibleText(workType);
    }

    public void setStartTime(String rawTime) {
        String time = normalizeToHHmm(rawTime);
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(startTimeInput));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        js.executeScript(
            "arguments[0].value = arguments[1];" +
            "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
            "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", el, time);
    }

    public void setEndTime(String rawTime) {
        String time = normalizeToHHmm(rawTime);
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(endTimeInput));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        js.executeScript(
            "arguments[0].value = arguments[1];" +
            "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
            "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", el, time);
    }

    private String normalizeToHHmm(String input) {
        if (input == null || input.trim().isEmpty()) return "";
        String s = input.trim().replace('.', ':');
        String[] parts = s.split(":");
        if (parts.length >= 2) {
            String hh = parts[0];
            String mm = parts[1];
            if (hh.length() == 1) hh = "0" + hh;
            if (mm.length() == 1) mm = "0" + mm;
            return hh + ":" + mm;
        }
        if (s.matches("^\\d{1,2}$")) {
            if (s.length() == 1) s = "0" + s;
            return s + ":00";
        }
        return s;
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
            // Search up to 2 levels (Field -> Wrapper -> FormGroup)
            for (int i = 0; i < 2; i++) {
                parent = parent.findElement(By.xpath(".."));
                List<WebElement> errors = parent.findElements(
                        By.xpath(".//*[not(self::select)][contains(@class,'error') or contains(@class,'invalid-feedback') or contains(@class,'invalid') or contains(@class,'text-danger')]"));
                for (WebElement error : errors) {
                    if (error.isDisplayed() && !error.getText().trim().isEmpty()) {
                        String text = error.getText().trim();
                        // Filter out multi-line text (like dropdown options)
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

    public String getGenericErrorMessage() {
        try {
            // Use a short wait for generic error as it might be a toast or dynamic alert
            WebElement msg = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(genericErrorMessage));
            return msg.getText();
        } catch (Exception e) {}
        return "";
    }

    public String getSuccessMessageText() {
        By successMsg = By.xpath("//div[contains(@class,'alert-success')]");
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return msg.getText();
    }
}
