package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddAreaPage extends BasePage {
        public AddAreaPage(WebDriver driver){
            super(driver);
            js = (JavascriptExecutor) driver;
            this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        By master = By.xpath("(//i[@class='fa fa-database '])");
        By area=By.xpath("(//i[@class='fa fa-map-marked-alt '])");
        By add=By.xpath("(//i[@class='fa fa-edit '])[1]");
        By state=By.xpath("//select[@id='states']");
        By branch=By.xpath("//input[@name='name']");
        By address=By.xpath("//input[@name='address']");
        By latitude=By.xpath("//input[@name='lat']");
        By longitude=By.xpath("//input[@name='long']");
        By workType=By.xpath("//select[@id='work_type']");
        By startTime=By.xpath("//input[@name='start_time']");
        By endTime=By.xpath("//input[@name='end_time']");
        By checkout=By.xpath("//select[@id='enable_force_checkout']");
        By allowLate=By.xpath("//select[@name='allow_late']");
        By allowOt=By.xpath("//select[@name='allow_ot']");
        By radius=By.xpath("//select[@id='enable_radius']");
        By submit=By.xpath("//button[@id='saveLocationData']");

    By startTimeError = By.xpath("//small[contains(text(),'Start Time is required')]");
     By endTimeError = By.xpath("//small[contains(text(),'End Time is required')]");
     By successMessage = By.xpath("//div[contains(@class,'alert-success')]");


    public void clickMaster() {
            WebElement mast = wait.until(ExpectedConditions.visibilityOfElementLocated(master));
            js.executeScript("arguments[0].click();", mast);
        }
        public void clickArea() {
            WebElement areaa = wait.until(ExpectedConditions.visibilityOfElementLocated(area));
            js.executeScript("arguments[0].click();", areaa);
        }
        public void clickAdd(){
            WebElement addr = wait.until(ExpectedConditions.visibilityOfElementLocated(add));
            js.executeScript("arguments[0].click();", addr);}

        public void selectState(String stat) {
            new Select( driver.findElement(state)).selectByVisibleText(stat);
        }

        public void setBranch(String bran) {
            driver.findElement(branch).sendKeys(bran);
        }

        public void setAddress(String addres) {
            WebElement addresss=  driver.findElement(address);
            addresss.click(); addresss.sendKeys(addres);
        }

        public void setLatitude(String latitudee) {
            WebElement lati=  driver.findElement(latitude);
            lati.click(); lati.sendKeys(latitudee);
        }
        public void setLongitude(String longi){
            WebElement loni=  driver.findElement(longitude);
            loni.click(); loni.sendKeys(longi);
        }
        public void selectWorkType(String workType1){
            new Select(  driver.findElement(workType)).selectByVisibleText(workType1);

        }
        public void setStartTime(String start){
            setTime(startTime, start);
        }
        public void setEndTime(String end){
            setTime(endTime, end);
        }
        public void selectCheckout(String yesno ){
            new Select( driver.findElement(checkout)).selectByVisibleText(yesno);
            //Select s=new Select(check);
            //s.selectByVisibleText(yesno);
        }
        public void selectAllowLate(String allow){new Select(driver.findElement(allowLate)).selectByVisibleText(allow);}
        public void selectAllowOt(String ot){new Select(driver.findElement(allowOt)).selectByVisibleText(ot);}
        public void selectRadius (String radios){new Select(driver.findElement(radius)).selectByVisibleText(radios);}
        public void clickSubmit()  {

            WebElement saveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(submit));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});",saveBtn);

            js.executeScript("arguments[0].click();",saveBtn);
        }

        private void setTime(By locator, String rawTime) {
            String time = normalizeToHHmm(rawTime);

            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            el.click();

            // clear() is unreliable for <input type="time"> in some browsers
            el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            el.sendKeys(Keys.BACK_SPACE);
            el.sendKeys(time);

            // If the UI is controlled by JS (or input is readonly), sendKeys may not stick. Fall back to JS set + events.
            String after = el.getAttribute("value");
            if (after == null || after.isBlank()) {
                js.executeScript(
                        "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                        el,
                        time
                );
            }
        }

        private String normalizeToHHmm(String input) {
            if (input == null) return "";
            String s = input.trim();
            if (s.isEmpty()) return s;

            // Accept "8.00", "8:00", "08:00", "20.00"
            s = s.replace('.', ':');

            // "8" -> "8:00"
            if (s.matches("^\\d{1,2}$")) {
                s = s + ":00";
            }

            // "8:0" -> "8:00" (rare but cheap to support)
            if (s.matches("^\\d{1,2}:\\d{1}$")) {
                s = s + "0";
            }

            // Pad hour to 2 digits for better compatibility
            if (s.matches("^\\d{1,2}:\\d{2}$")) {
                String[] parts = s.split(":");
                String hh = parts[0];
                String mm = parts[1];
                if (hh.length() == 1) hh = "0" + hh;
                return hh + ":" + mm;
            }

            // If caller already provides HH:mm:ss, keep it (some UIs expect seconds)
            if (s.matches("^\\d{1,2}:\\d{2}:\\d{2}$")) {
                String[] parts = s.split(":");
                String hh = parts[0];
                if (hh.length() == 1) hh = "0" + hh;
                return hh + ":" + parts[1] + ":" + parts[2];
            }

            // Unknown format; return trimmed input as-is (better than silently mangling)
            return s;
        }



    public boolean isStartTimeErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(startTimeError)).isDisplayed();
    }

    public boolean isEndTimeErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(endTimeError)).isDisplayed();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

}
