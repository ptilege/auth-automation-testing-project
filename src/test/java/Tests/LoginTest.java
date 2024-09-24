package Tests;

import Pages.LoginPage;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

public class LoginTest extends BaseTest{

    @Test
    public void testValidLogin() throws IOException {
        test = extent.createTest("valid Login Test");

        SoftAssert softAssert = new SoftAssert();

        try{
        driver.get("http://localhost:8080/login");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUserName("ptilege@gmail.com");
        loginPage.enterPassword("pasindu");
        loginPage.clickLoginButton();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe("http://localhost:8080/dashboard"));


            if (driver.getCurrentUrl().equals("http://localhost:8080/dashboard")) {
                test.pass("Successfully logged in and redirected to the dashboard.");
            } else {
                test.fail("Not redirected to the dashboard after login.")
                        .addScreenCaptureFromPath(takeScreenshot(driver, "loginFailure"));
                softAssert.fail("Not redirected to the correct URL.");
            }
         }catch(Exception e){
        test.fail("Login test failed due to: " + e.getMessage())
                .addScreenCaptureFromPath(takeScreenshot(driver,"loginException"));
            throw e;
     }finally {
            softAssert.assertAll();
        }
    }

    @Test
    public void testInvalidUserName() throws IOException {
        test = extent.createTest("InvalidUserName Login Test");
        try{
            driver.get("http://localhost:8080/login");

            LoginPage loginPage = new LoginPage(driver);

            loginPage.enterUserName("A");
            loginPage.enterPassword("pasindu");
            loginPage.clickLoginButton();



            String invalidUserNameError = loginPage.getInvalidUserLoginError();
            if (invalidUserNameError.equals("Invalid username.")) {
                test.pass("The error message is displayed correctly: Invalid username.");
            } else {
                test.fail("The expected error message is not displayed. Found"+invalidUserNameError)
                        .addScreenCaptureFromPath(takeScreenshot(driver, "InvalidUserNameLoginTestFailure"));
            }
        }catch(Exception e){
            test.fail("InvalidUserNameLogin test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(takeScreenshot(driver,"InvalidUserNameLoginException"));
        }
    }

    @Test
    public void testInvalidPassword() throws IOException {
        test = extent.createTest("InvalidPassword Login Test");
        try {
            driver.get("http://localhost:8080/login");

            LoginPage loginPage = new LoginPage(driver);

            loginPage.enterUserName("ptilege@gmail.com");
            loginPage.enterPassword("Abe");
            loginPage.clickLoginButton(); // Ensure this submits the form

//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//small[@id='passwordError']")));
//
//            Thread.sleep(5000);

            String invalidPasswordError = loginPage.getInvalidPassLoginError();

            if (invalidPasswordError.equals("Invalid password.")) {
                test.pass("The error message is displayed correctly: Invalid password.");
            } else {
                test.fail("The expected error message is not displayed. Found: " + invalidPasswordError)
                        .addScreenCaptureFromPath(takeScreenshot(driver, "InvalidPasswordLoginTestFailure"));
            }
            Thread.sleep(5000);
        } catch (Exception e) {
            test.fail("InvalidPasswordLogin test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(takeScreenshot(driver, "InvalidPasswordLoginException"));
        }
    }


    @Test
    public void testAccountLockAfterFailedAttempts() throws IOException {
        test = extent.createTest("AccountLockAfterFailedAttempts Login Test");

        driver.get("http://localhost:8080/login");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUserName("Abcd");
        loginPage.enterPassword("InvalidPass");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < 3; i++) {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
            loginButton.click();

            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formError")));
                String messageText = errorMessage.getText();

                if (i < 2) {
                    if (messageText.contains("Invalid username or password")) {
                        test.pass("Error message displayed for failed login attempt " + (i + 1));
                    } else {
                        test.pass("Login failed as expected for attempt " + (i + 1) + ", no specific message required.");
                    }
                } else {
                    if (messageText.contains("Your account is locked. Please reset your password.")) {
                        test.pass("Account locked message displayed after 3 failed attempts.");
                    } else {
                        test.fail("Expected account lock message not found: " + messageText)
                                .addScreenCaptureFromPath(takeScreenshot(driver, "lockMessageFailure"));
                    }
                }
            } catch (TimeoutException e) {
                if (i < 2) {
                    test.pass("Login attempt " + (i + 1) + " failed without showing error message, as expected.");
                } else {
                    test.fail("Error message did not appear after 3rd login attempt (account lock expected)")
                            .addScreenCaptureFromPath(takeScreenshot(driver, "timeoutFailure"));
                }
            }
        }
    }






}
