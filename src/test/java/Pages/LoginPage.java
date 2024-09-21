package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By username = By.xpath("//input[@id='username']");
    private By password = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//button[normalize-space()='Login']");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUserName(String uname){
        driver.findElement(username).sendKeys(uname);
    }

    public void enterPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public String getInvalidPassLoginError(){
        return driver.findElement(By.xpath("//small[@id='passwordError']")).getText();
    }

    public String getInvalidUserLoginError(){
        return driver.findElement(By.xpath("//small[@id='usernameError']")).getText();
    }



}
