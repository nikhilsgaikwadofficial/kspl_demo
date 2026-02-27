package test;



import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateUserPage;

    public class AddUserTest extends BaseTest {


        @Test(priority = 1)
        public void verify_CreateUser() throws InterruptedException {
            CreateUserPage createUser = new CreateUserPage(driver);
            createUser.clickMaster();
            createUser.clickUser();
            createUser.clickAddUser();

            createUser.enterEmpId("");
            createUser.enterEmpName("Rega");
            createUser.enterMobile("9601896274");
            createUser.enterEmail("viper@gmail.com");
            createUser.enterPassword("");


            createUser.selectRole("Level 1");
            createUser.selectDesignation("F&B Executive");
            createUser.selectBranch("ghatkopar(S8ul bootcamp)");
            createUser.clickSubmit();

            Thread.sleep(2000);


            if (createUser.isSuccessMessageDisplayed()) {
                String successMsg = createUser.getSuccessMessageText();
                System.out.println("User created successfully - Success message displayed: " + successMsg);
                Assert.assertTrue(createUser.isSuccessMessageDisplayed(),
                        "User was created successfully. Success message: " + successMsg);
            }

            else if (createUser.isGenericErrorMessageDisplayed()) {
                String errorMsg = createUser.getGenericErrorMessageText();
                System.out.println("Generic error message displayed: " + errorMsg);
                Assert.fail("User creation failed with error: " + errorMsg);
            }

            else {
                System.out.println("Validating field-level error messages...");


                boolean hasValidationErrors = createUser.isEmpIdErrorDisplayed() ||
                        createUser.isEmpNameErrorDisplayed() ||
                        createUser.isMobileErrorDisplayed() ||
                        createUser.isEmailErrorDisplayed() ||
                        createUser.isPasswordErrorDisplayed() ||
                        createUser.isRoleErrorDisplayed() ||
                        createUser.isDesignationErrorDisplayed() ||
                        createUser.isBranchErrorDisplayed();

                if (hasValidationErrors) {
                    System.out.println("Unexpected validation errors found even though all fields were filled.");
                    Assert.fail("Form submission failed - validation errors appeared despite all required fields being filled");
                } else {

                    Assert.fail("Form submission completed but no success or error message was displayed");
                }
            }
        }
        }







