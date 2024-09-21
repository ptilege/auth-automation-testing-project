package Tests;

import Pages.RegistrationPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTest extends BaseTest {

    @Test
    public void testValidRegistration() throws IOException, InterruptedException {
        test = extent.createTest("Valid Registration Test");

        try{
        driver.get("http://localhost:8080/register");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterFirstName("Pasindu");
        registrationPage.enterLastName("Tharaka");
        registrationPage.enterEmail("pasindu@gmail.com");
        registrationPage.enterPhoneNumber("0769180174");
        registrationPage.enterDOB("12-12-1998");
        registrationPage.enterPassword("pasinduth");
        registrationPage.submitForm();

        String successAlert = registrationPage.getSuccessMessage();

        if (successAlert.equals("You're In!")) {
            test.pass("The success message is displayed correctly: You're In!");
        } else {
            test.fail("The expected success message is not displayed.")
                    .addScreenCaptureFromPath(takeScreenshot(driver, "successMessageErrorTestFailure"));
        }

//        Thread.sleep(5000);
        }catch(Exception e){
            test.fail("Login test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(takeScreenshot(driver,"successMessageErrorTestException"));
        }
    }

    @Test
    public void testEmptyFields() throws IOException {
        test = extent.createTest("Invalid First Name Test");

        try{
            driver.get("http://localhost:8080/register");

            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.enterFirstName("Pasindu");
            registrationPage.enterLastName("tharaka");
            registrationPage.enterEmail("ptilege@gmail.com");
            registrationPage.enterPhoneNumber("0769180173");
            registrationPage.enterDOB("12-12-1998");
            registrationPage.submitForm();


            String EmptyFieldError = registrationPage.getEmptyFieldError();

            if (EmptyFieldError.equals("All fields are mandatory.")) {
                test.pass("The error message is displayed correctly: All fields are mandatory.");
            } else {
                test.fail("The expected error message is not displayed.")
                        .addScreenCaptureFromPath(takeScreenshot(driver, "EmptyFieldErrorTestFailure"));
            }
        }catch(Exception e){
            test.fail("Login test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(takeScreenshot(driver,"EmptyFieldErrorTestException"));
        }
    }




    @Test
    public void testInvalidFirstName() throws IOException {
        test = extent.createTest("Invalid First Name Test");

        try{
        driver.get("http://localhost:8080/register");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterFirstName("P");
        registrationPage.enterLastName("Tharaka");
        registrationPage.enterEmail("ptilege@gmail.com");
        registrationPage.enterPhoneNumber("0769180173");
        registrationPage.enterDOB("1998-12-12");
        registrationPage.enterPassword("pasindu");
        registrationPage.submitForm();


        String firstNameError = registrationPage.getFirstNameError();

            if (firstNameError.equals("First name must contain at least 2 alphabetic characters.")) {
                test.pass("The error message is displayed correctly: First name must contain at least 2 alphabetic characters.");
            } else {
                test.fail("The expected error message is not displayed.")
                        .addScreenCaptureFromPath(takeScreenshot(driver, "FirstNameErrorTestFailure"));
            }
        }catch(Exception e){
            test.fail("Login test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(takeScreenshot(driver,"FirstNameErrorTestException"));
        }
    }

    @Test
    public void testInvalidLastName() throws IOException {
        test = extent.createTest("Invalid First Name Test");

        try{
            driver.get("http://localhost:8080/register");

            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.enterFirstName("Pasindu");
            registrationPage.enterLastName("T");
            registrationPage.enterEmail("ptilege@gmail.com");
            registrationPage.enterPhoneNumber("0769180173");
            registrationPage.enterDOB("1998-12-12");
            registrationPage.enterPassword("pasindu");
            registrationPage.submitForm();


            String lastNameError = registrationPage.getLastNameError();

            if (lastNameError.equals("Last name must contain at least 2 alphabetic characters.")) {
                test.pass("The error message is displayed correctly: Last name must contain at least 2 alphabetic characters.");
            } else {
                test.fail("The expected error message is not displayed.")
                        .addScreenCaptureFromPath(takeScreenshot(driver, "LastNameErrorTestFailure"));
            }
        }catch(Exception e){
            test.fail("Login test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(takeScreenshot(driver,"LastNameErrorTestException"));
        }
    }

    @Test
    public void testInvalidEmail() throws IOException {
        test = extent.createTest("Invalid Email Test");

        try{
        driver.get("http://localhost:8080/register");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterFirstName("Pasindu");
        registrationPage.enterLastName("Tharaka");
        registrationPage.enterEmail("invalid_email");
        registrationPage.enterPhoneNumber("0769180173");
        registrationPage.enterDOB("1998-12-12");
        registrationPage.enterPassword("pasindu");
        registrationPage.submitForm();


        String emailError = registrationPage.getEmailError();

            if (emailError.equals("Invalid email format.")) {
                test.pass("The error message is displayed correctly: Invalid email format.");
            } else {
                test.fail("The expected error message is not displayed.")
                        .addScreenCaptureFromPath(takeScreenshot(driver, "EmailErrorTestFailure"));
            }
        }catch(Exception e){
            test.fail("Login test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(takeScreenshot(driver,"EmailErrorTestException"));
        }
    }

    @Test
    public void testInvalidPhoneNumber() throws IOException {
        test = extent.createTest("Invalid Phone Number Test");

        try{
        driver.get("http://localhost:8080/register");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterFirstName("Pasindu");
        registrationPage.enterLastName("Tharaka");
        registrationPage.enterEmail("ptilege@gmail.com");
        registrationPage.enterPhoneNumber("12345");
        registrationPage.enterDOB("1998-12-12");
        registrationPage.enterPassword("pasindu");
        registrationPage.submitForm();


        String phoneNumberError = registrationPage.getPhoneNumberError();

            if (phoneNumberError.equals("Phone number must contain exactly 10 digits.")) {
                test.pass("The error message is displayed correctly: Phone number must contain exactly 10 digits.");
            } else {
                test.fail("The expected error message is not displayed.")
                        .addScreenCaptureFromPath(takeScreenshot(driver, "PhoneNumberTestFailure"));
            }
        }catch(Exception e){
            test.fail("Login test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(takeScreenshot(driver,"PhoneNumberTestException"));
        }
    }

    @Test
    public void testFutureDOB() throws IOException {
        test = extent.createTest("Future DOB Test");

        try{
        driver.get("http://localhost:8080/register");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterFirstName("Pasindu");
        registrationPage.enterLastName("Tharaka");
        registrationPage.enterEmail("ptilege@gmail.com");
        registrationPage.enterPhoneNumber("0769180173");
        registrationPage.enterDOB("2025-01-01");
        registrationPage.enterPassword("pasindu");
        registrationPage.submitForm();


        String dobError = registrationPage.getDOBError();

            if (dobError.equals("Date of birth cannot be in the future.")) {
                test.pass("The error message is displayed correctly: Date of birth cannot be in the future.");
            } else {
                test.fail("The expected error message is not displayed.")
                        .addScreenCaptureFromPath(takeScreenshot(driver, "FutureDOBTestFailure"));
            }
        }catch(Exception e){
            test.fail("Login test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(takeScreenshot(driver,"FutureDOBTestException"));
        }
    }
}
