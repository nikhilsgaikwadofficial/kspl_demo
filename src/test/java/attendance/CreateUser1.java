package attendance;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateUser1 {


        public static void main(String[] args) {

            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


            driver.get("https://ourattendance.com/web-portal_uat/public/");


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email']"))).sendKeys("goldys8ul@gmail.com");

            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("S8ulmortal");

            driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]")).click();


          //  wait.until(ExpectedConditions.urlContains("dashboard"));


            WebElement master = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class,'fa-database')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", master);


            WebElement user = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class,'fa-user')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", user);


            WebElement addUser = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'users/add')]")));
            addUser.click();


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("emp_id"))).sendKeys("S8ul_201");

            driver.findElement(By.name("name")).sendKeys("Geeta");
            driver.findElement(By.name("mobile_number")).sendKeys("9768564510");
            driver.findElement(By.name("email")).sendKeys("geeta@gmail.com");
            driver.findElement(By.id("password")).sendKeys("Geeta@123");


            Select role = new Select(driver.findElement(By.id("role")));
            role.selectByVisibleText("Level 1");


            Select designation = new Select(driver.findElement(By.id("designation")));
            designation.selectByVisibleText("Supervisor");


            WebElement branchDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select2-area_id-container")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", branchDropdown);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", branchDropdown);

            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-search__field")));

            searchBox.sendKeys("ghatkopar(S8ul bootcamp)");
            searchBox.sendKeys(Keys.ENTER);



            WebElement submitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-admin-member-submit")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", submitBtn);

            wait.until(ExpectedConditions.elementToBeClickable(submitBtn));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);




            // driver.quit();
        }
    }


