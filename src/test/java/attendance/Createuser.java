package attendance;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Createuser {
    WebDriver driver;



    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://ourattendance.com/web-portal_uat/public/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("goldys8ul@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("S8ulmortal");
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-block btn-flat']")).click();


        Thread.sleep(3000);

        WebElement master = driver.findElement(By.xpath("//i[@class='fa fa-database ']"));


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", master);

        Thread.sleep(2000);
        JavascriptExecutor jvs = (JavascriptExecutor) driver;
        jvs.executeScript("window.scrollBy(0,500);");

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement user= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//i[@class='fa fa-user '])")));

        JavascriptExecutor js1=(JavascriptExecutor) driver;
        js1.executeScript("arguments[0].click();",user);

        Thread.sleep(2000);



        WebElement adduser=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'https://ourattendance.com/web-portal_uat/public/users/add')]")));
        JavascriptExecutor jv=(JavascriptExecutor) driver;
        jv.executeScript("arguments[0].click();",adduser);

        driver.findElement(By.xpath("//input[@name='emp_id']")).sendKeys("S8ul_201");
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Geeta");
        driver.findElement( By.xpath("//input[@name='mobile_number']")).sendKeys("9768564510");
        driver.findElement( By.xpath("//input[@name='email']")).sendKeys("geeta@gmail.com");
        driver.findElement( By.xpath("//input[@id='password']")).sendKeys("Geeta@123");
      //  driver.findElement( By.xpath("//input[@name='blood_group']")).sendKeys("B+");
        //driver.findElement(  By.xpath("//input[@name='emergency_contact']")).sendKeys("9465996320");
    //      driver.findElement(  By.xpath("//input[@name='basic_salary']")).sendKeys("10000");
            //    driver.findElement(By.xpath("//input[@name='per_hour_cost']")).sendKeys("500");
      //  driver.findElement(By.xpath("//input[@name='allowance']")).sendKeys("1000");
        WebElement   role= driver.findElement( By.xpath("//select[@id='role']"));

        Select s=new Select(role);
        s.selectByVisibleText("Level 1");

        WebElement desg= driver.findElement( By.xpath("//select[@id='designation']"));
        Select ss=new Select(desg);
        ss.selectByVisibleText("Supervisor");


        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement branchDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='select2-area_id-container']")));

        JavascriptExecutor js5 = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", branchDropdown);


        wait.until(ExpectedConditions.elementToBeClickable(branchDropdown));
        js.executeScript("arguments[0].click();", branchDropdown);
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@role='searchbox']")));
        searchBox.sendKeys("ghatkopar(S8ul bootcamp)");


        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'ghatkopar')]")));
        option.click();


           /*  Select dropdown=new Select(branchDropdown);
             dropdown.selectByVisibleText("Monika Home(Monika Home)");*/



        /*WebElement branchSearch = wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//input[@role='searchbox']")));
        Select ss2=new Select(branchSearch);
        ss2.selectByVisibleText("Jitesh home");*/



      /*  WebElement rsm= driver.findElement(By.xpath("//select[@id='rsm']"));
        rsm.click();
        Select s2=new Select(rsm);
        s2.selectByVisibleText("Aniket Patil");*/

        WebDriverWait wait2= new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement Submit=wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btn-admin-member-submit']")));
        Submit.click();



    }
}

    /*public void selectBranch(String branchText) {
    WebElement branchDropdown = driver.findElement(branch);

    Actions actions = new Actions(driver);
    actions.moveToElement(branchDropdown).click().perform();

    Select select = new Select(branchDropdown);
    select.selectByVisibleText(branchText);
}
*/

/* driver.findElement(By.xpath("//span[@class='d-none d-md-inline']")).click();
          WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Log Out']")));
        logout.click();*/

//driver.findElement(By.xpath("//a[normalize-space()='Log Out']")).click();

// driver.findElement( By.xpath("//button[@id='btn-admin-member-submit']")).click();

// driver.quit();
