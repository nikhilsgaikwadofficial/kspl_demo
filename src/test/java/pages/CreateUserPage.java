
    package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

    public class CreateUserPage extends BasePage {



        public CreateUserPage(WebDriver driver) {
            super(driver);
            js = (JavascriptExecutor) driver;
            wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        }


        public By master = By.xpath("(//i[@class='fa fa-database '])");
        public By user = By.xpath("(//i[@class='fa fa-user ']) ");
        public By addUser = By.xpath("//a[contains(@href,'https://ourattendance.com/web-portal_uat/public/users/add')]");
        public By empId = By.xpath("//input[@name='emp_id']");
        public By empName = By.xpath("//input[@name='name']");
        public By mobile = By.xpath("//input[@name='mobile_number']");
        public By email = By.xpath("//input[@name='email']");
        public By password = By.xpath("//input[@id='password']");
        public By bloodGroup = By.xpath("//input[@name='blood_group']");
        public By emergencyContact = By.xpath("//input[@name='emergency_contact']");
        public By basicSalary = By.xpath("//input[@name='basic_salary']");
        public By role = By.xpath("//select[@id='role']");
        public By designation = By.xpath("//select[@id='designation']");
        public By branch =By.xpath("//select[@name='area_id'][1]");
        public By mapRole=By.xpath("//input[@role='searchbox']");
        public By submit = By.xpath("//button[@id='btn-admin-member-submit']");



        By empIdError = By.xpath("//div[contains(text(),'Employee ID field is required')]");
        By empNameError = By.xpath("//div[contains(text(),'Name field is required')]");
        By mobileError = By.xpath("//div[contains(text(),'Mobile Number field is required')]");
        By emailError = By.xpath("//div[contains(text(),'Email field is required')]");
        By passwordError = By.xpath("//div[contains(text(),'Password field is required')]");
        By roleError = By.xpath("//*[contains(@class,'error') or contains(@class,'invalid-feedback')][contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'role')]");
        By designationError = By.xpath("//div[contains(text(),'Designation field is required')]");
        By branchError = By.xpath("//*[contains(@class,'error') or contains(@class,'invalid-feedback')][contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'branch')]");
        By successMessage = By.xpath("//div[contains(@class,'alert-success')]");
        By genericErrorMessage = By.xpath("//div[contains(@class,'alert-danger') or contains(@class,'alert-warning')]");





        private boolean isElementDisplayed(By locator) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            } catch (TimeoutException e) {
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

        public String getValidationMessage(By locator) {
            WebElement element = driver.findElement(locator);
            return (String) js.executeScript("return arguments[0].validationMessage;", element);
        }

        public boolean isEmpIdErrorDisplayed() {
            return isElementDisplayed(empIdError);
        }

        public boolean isEmpNameErrorDisplayed() {
            return isElementDisplayed(empNameError);
        }

        public boolean isMobileErrorDisplayed() {
            return isElementDisplayed(mobileError);
        }

        public boolean isEmailErrorDisplayed() {
            return isElementDisplayed(emailError);
        }

        public boolean isPasswordErrorDisplayed() {
            return isElementDisplayed(passwordError);
        }

        public boolean isRoleErrorDisplayed() {

            if (hasFieldError(role)) return true;

            return isElementDisplayed(roleError);
        }

        public boolean isDesignationErrorDisplayed() {
            return isElementDisplayed(designationError);
        }

        public boolean isBranchErrorDisplayed() {

            if (hasFieldError(branch)) return true;

            return isElementDisplayed(branchError);
        }

        /** Searches parent elements of the given field for visible .error / .invalid-feedback children */
        private boolean hasFieldError(By fieldLocator) {
            try {
                WebElement field = driver.findElement(fieldLocator);

                WebElement parent = field;
                for (int i = 0; i < 3; i++) {
                    parent = parent.findElement(By.xpath(".."));
                    List<WebElement> errors = parent.findElements(
                            By.xpath(".//*[contains(@class,'error') or contains(@class,'invalid-feedback') or contains(@class,'invalid')]"));
                    for (WebElement error : errors) {
                        if (error.isDisplayed() && !error.getText().trim().isEmpty()) {
                            return true;
                        }
                    }
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }

        /** Searches parent elements of the given field for visible .error / .invalid-feedback children and returns the text */
        public String getFieldErrorText(By fieldLocator) {
            try {
                WebElement field = driver.findElement(fieldLocator);
                WebElement parent = field;
                for (int i = 0; i < 3; i++) {
                    parent = parent.findElement(By.xpath(".."));
                    List<WebElement> errors = parent.findElements(
                            By.xpath(".//*[contains(@class,'error') or contains(@class,'invalid-feedback') or contains(@class,'invalid') or contains(@class,'text-danger')]"));
                    for (WebElement error : errors) {
                        if (error.isDisplayed() && !error.getText().trim().isEmpty()) {
                            return error.getText().trim();
                        }
                    }
                }
                return "";
            } catch (Exception e) {
                return "";
            }
        }

        public boolean isSuccessMessageDisplayed() {
            return isElementDisplayed(successMessage);
        }

        public String getSuccessMessageText() {
            try {
                WebElement successElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
                return successElement.getText();
            } catch (TimeoutException e) {
                return "";
            }
        }

        public boolean isGenericErrorMessageDisplayed() {
            return isElementDisplayed(genericErrorMessage);
        }

        public String getGenericErrorMessageText() {
            try {
                WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(genericErrorMessage));
                return errorElement.getText();
            } catch (TimeoutException e) {
                return "";
            }
        }

        public void clickMaster() {
            WebElement mast = wait.until(ExpectedConditions.visibilityOfElementLocated(master));
            js.executeScript("arguments[0].click();", mast);
        }

        public void clickUser() {
            WebElement users=   wait.until(ExpectedConditions.visibilityOfElementLocated(user));
            js.executeScript("arguments[0].click();", users);
        }

        public void clickAddUser() {
            WebElement addupdate=    wait.until(ExpectedConditions.visibilityOfElementLocated(addUser));
            js.executeScript("arguments[0].click();", addupdate);
        }


        public void enterEmpId(String id) {
            WebElement empid = wait.until(ExpectedConditions.visibilityOfElementLocated(empId));
            empid.sendKeys(id);
        }


        public void enterEmpName(String name) {
            WebElement namee=wait.until(ExpectedConditions.visibilityOfElementLocated(empName));
            driver.findElement(empName).sendKeys(name);
        }

        public void enterMobile(String number) {



                number = number.replaceAll("\\D", "");


                if (number.length() > 10) {
                    number = number.substring(0, 10);
                }

                if (number.length() < 10) {
                    throw new IllegalArgumentException("Mobile number must be exactly 10 digits");
                }

                wait.until(ExpectedConditions.visibilityOfElementLocated(mobile)).clear();
                wait.until(ExpectedConditions.visibilityOfElementLocated(mobile)).sendKeys(number);
            }

            public void enterEmail(String mail) {
            WebElement maill=wait.until(ExpectedConditions.visibilityOfElementLocated(email));
            driver.findElement(email).sendKeys(mail);
        }

        public void enterPassword(String pass) {
            driver.findElement(password).sendKeys(pass);
        }

        public void enterBloodGroup(String bloodgroup) {
            driver.findElement(bloodGroup).sendKeys(bloodgroup);
        }

        public void enterEmergencyContact(String number) {
            driver.findElement(emergencyContact).sendKeys(number);
        }

        public void enterBasicSalary(String salary) {
            driver.findElement(basicSalary).sendKeys(salary);
        }

        public void selectRole(String roleText) {
            if (roleText == null || roleText.isEmpty()) return;
            new Select(driver.findElement(role)).selectByVisibleText(roleText);
        }

        public void selectDesignation(String designationText) {
            if (designationText == null || designationText.isEmpty()) return;
            new Select(driver.findElement(designation)).selectByVisibleText(designationText);
        }

        public void selectBranch(String branchText){
            if (branchText == null || branchText.isEmpty()) return;
            WebElement branchElement = wait.until(ExpectedConditions.presenceOfElementLocated(branch));
            

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", branchElement);
            

            wait.until(ExpectedConditions.elementToBeClickable(branchElement));
            

            Select branchSelect = new Select(branchElement);
            branchSelect.selectByVisibleText(branchText);
        }

        public void SelectMapRole(String maprole) {
            WebElement mapdrop= driver.findElement(mapRole);
            Actions drop=new Actions(driver);
            drop.moveToElement(mapdrop).click().perform();
            new Select(driver.findElement(mapRole)).selectByVisibleText(maprole);
        }

        public void clickSubmit() {

            WebElement submitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='btn-admin-member-submit']")));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", submitBtn);
            

            wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
            js.executeScript("arguments[0].click();", submitBtn);
        }



        public boolean assertEmpIdField() {
            return isElementDisplayed(empId) && (isFieldEmpty(empId) || hasFieldError(empId) || !getValidationMessage(empId).isEmpty());
        }

        public boolean assertEmpNameField() {
            return isElementDisplayed(empName) && (isFieldEmpty(empName) || hasFieldError(empName) || !getValidationMessage(empName).isEmpty());
        }

        public boolean assertMobileField() {
            return isElementDisplayed(mobile) && (isFieldEmpty(mobile) || hasFieldError(mobile) || !getValidationMessage(mobile).isEmpty());
        }

        public boolean assertEmailField() {
            return isElementDisplayed(email) && (isFieldEmpty(email) || hasFieldError(email) || !getValidationMessage(email).isEmpty());
        }

        public boolean assertPasswordField() {
            return isElementDisplayed(password) && (isFieldEmpty(password) || hasFieldError(password) || !getValidationMessage(password).isEmpty());
        }

        public boolean assertRoleField() {
            return isElementDisplayed(role) && (isFieldEmpty(role) || hasFieldError(role) || !getValidationMessage(role).isEmpty());
        }

        public boolean assertDesignationField() {
            return isElementDisplayed(designation) && (isFieldEmpty(designation) || hasFieldError(designation) || !getValidationMessage(designation).isEmpty());
        }

        public boolean assertBranchField() {
            return isElementDisplayed(branch) && (isFieldEmpty(branch) || hasFieldError(branch) || !getValidationMessage(branch).isEmpty());
        }

    }

//Select bran= new Select(branch);
//  bran.selectByVisibleText(branchText);

