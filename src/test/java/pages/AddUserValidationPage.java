package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddUserValidationPage extends CreateUserPage {

    public AddUserValidationPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToAddUser() {
        driver.get("https://ourattendance.com/web-portal_uat/public/users/add");
        wait.until(ExpectedConditions.elementToBeClickable(submit));
    }
}
