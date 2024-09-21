package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeMethod
    public void setUp(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        ExtentSparkReporter spark = new ExtentSparkReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public String takeScreenshot(WebDriver driver, String filename) throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        Date date = new Date();
        String timestamp = formatter.format(date);

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);


        File screenshotDir = new File(System.getProperty("user.dir") + "/screenshot/");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        File destinationFile = new File(screenshotDir, filename + "_" + timestamp + ".png");
        FileHandler.copy(sourceFile, destinationFile);
        return destinationFile.getAbsolutePath();
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
            extent.flush();
        }
    }
}
