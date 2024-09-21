package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;

    private By firstName = By.xpath("//input[@name='firstName']");
    private By lastName = By.xpath("//input[@id='lastName']");
    private By email = By.xpath("//input[@id='email']");
    private By phoneNumber = By.xpath("//input[@id='phone']");
    private By dateOfBirth = By.xpath("//input[@id='dob']");
    private By password = By.xpath("//input[@id='password']");
    private By submitButton = By.xpath("//button[normalize-space()='Register']");
    private By successMessage = By.xpath("//h2[@id='swal2-title']");
    private By errorMessage = By.xpath("");





    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String fName){
        driver.findElement(firstName).sendKeys(fName);
    }

    public void enterLastName(String lName){
        driver.findElement(lastName).sendKeys(lName);
    }

    public void enterEmail(String Email){
        driver.findElement(email).sendKeys(Email);
    }

    public void enterPhoneNumber(String pNumber){
        driver.findElement(phoneNumber).sendKeys(pNumber);
    }

    public void enterDOB(String dob){
        driver.findElement(dateOfBirth).sendKeys(dob);
    }

    public void enterPassword(String Password){
        driver.findElement(password).sendKeys(Password);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public String getEmptyFieldError(){
        return driver.findElement(By.id("EmptyFieldError")).getText();
    }

    public String getFirstNameError() {
        return driver.findElement(By.xpath("//div[@id='firstNameError']")).getText();
    }

    public String getLastNameError() {
        return driver.findElement(By.xpath("//div[@id='lastnameError']")).getText();
    }

    public String getEmailError() {
        return driver.findElement(By.xpath("//div[@id='emailError']")).getText();
    }

    public String getPhoneNumberError() {
        return driver.findElement(By.xpath("//div[@id='phoneError']")).getText();
    }

    public String getDOBError() {
        return driver.findElement(By.xpath("//div[@id='dobError']")).getText();
    }

    public String getSuccessMessage(){
        return driver.findElement(successMessage).getText();
    }

}
