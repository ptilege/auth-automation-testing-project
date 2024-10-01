package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private WebDriver driver;

    private By headingElement = By.xpath("//h1[normalize-space()='Dashboard']");

    public DashboardPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean getHeadingElement(){
        return driver.findElement(headingElement).isDisplayed();
    }
}
