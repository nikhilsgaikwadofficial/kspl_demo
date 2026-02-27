
    package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class DeleteAreaPage extends BasePage {

        WebDriver driver;

        public DeleteAreaPage(WebDriver driver) {
            super(driver);
            js = (JavascriptExecutor) driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        By master = By.xpath("(//i[@class='fa fa-database '])");
        By area = By.xpath("(//i[@class='fa fa-map-marked-alt '])");
        By List = By.xpath("//a[@href='https://ourattendance.com/web-portal_uat/public/areas']");
        By delete = By.xpath("(//i[@class='fa fa-trash'])[6]");


        public void clickMaster() {
            WebElement mast = wait.until(ExpectedConditions.visibilityOfElementLocated(master));
            js.executeScript("arguments[0].click();", mast);
        }

        public void clickArea() {
            WebElement areaa = wait.until(ExpectedConditions.visibilityOfElementLocated(area));
            js.executeScript("arguments[0].click();", areaa);
        }

        public void clickList() {
            WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(List));
            js.executeScript("arguments[0].click();", list);
        }

        public void clickDelete() {
            WebElement deletee = wait.until(ExpectedConditions.presenceOfElementLocated(delete));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", deletee);
            wait.until(ExpectedConditions.elementToBeClickable(deletee));
            js.executeScript("arguments[0].click();", deletee);
        }
        public void deleteAreaAndAcceptAlert() {

            WebElement deleteBtn = wait.until(ExpectedConditions.presenceOfElementLocated(delete));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", deleteBtn);

            try { Thread.sleep(500); } catch (InterruptedException e) { }

            // Click the <a> tag directly (which has the onclick confirm handler)
            js.executeScript("arguments[0].click();", deleteBtn);

            try {
                // Try handling native browser alert first
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                String alertText = alert.getText();
                System.out.println("Alert message: " + alertText);
                alert.accept();
            } catch (Exception e) {
                // If no native alert, it might be a SweetAlert/custom modal — click the confirm button

                try {
                    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
                            By.cssSelector(".swal2-confirm, .swal-button--confirm, button.confirm, .btn-ok, .btn-danger")));
                    confirmBtn.click();
                } catch (Exception ex) {
                    //System.out.println("No custom dialog found either: " + ex.getMessage());
                }
            }
        }

        // Optional: Cancel delete
        public void deleteAreaAndCancelAlert() {
            WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(delete));
            deleteIcon.click();

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss(); // Click Cancel
        }
    }





