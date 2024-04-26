package com.example;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Book {
   final Logger logj = Logger.getLogger(getClass());
    
    WebDriver driver;
    ExtentReports extent;
    ExtentTest logger;

    @BeforeTest
    public void setup()
    {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("D:\\Software Testing\\cc\\cclogandreport\\report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

   
    @Test
    public void Test1() throws IOException, InterruptedException
    {
        PropertyConfigurator.configure("D:\\Software Testing\\cc\\cc2\\src\\main\\resources\\log4j2.properties");
        logger = extent.createTest("Test1","This is First Test");
        
         driver.get("https://www.barnesandnoble.com/");
         logj.info("opening url");
         driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/a")).click();
         driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[1]/a")).click();
         logj.info("selecting books");
         driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys("Chetan Bhagat"+Keys.ENTER);
         logj.info("searching chetan bhagat");
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         logj.warn("time wait");
         String key = driver.findElement(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1/span")).getText();
         Assert.assertTrue(key.contains("Chetan Bhagat"));
         logj.warn("validation");
         
        }
        
        @Test
        public void test2() throws InterruptedException
        {
        PropertyConfigurator.configure("D:\\Software Testing\\cc\\cc2\\src\\main\\resources\\log4j2.properties");
        driver.get("https://www.barnesandnoble.com");
        logj.info("opening url");
        driver.findElement(By.xpath("//*[@id='onetrust-close-btn-container']/button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        logj.warn("time wait");
        Actions action=new Actions(driver);
        WebElement source=driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]"));
        WebElement dest=driver.findElement(By.xpath("//*[@id='rhfCategoryFlyout_Audiobooks']"));
        action.dragAndDrop(source,dest).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        logj.warn("time wait");
        driver.findElement(By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        logj.warn("time wait");
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        logj.info("scrolling done");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        logj.warn("time wait");
        driver.findElement(By.xpath("//*[@id='addToBagForm_2940159543998']/input[11]")).click();
    }



    @Test
    public void Test3() throws IOException,InterruptedException
    {
      PropertyConfigurator.configure("D:\\Software Testing\\cc\\cc2\\src\\main\\resources\\log4j2.properties");
         logger = extent.createTest("Test3","This is Third Test");
         driver.manage().window().maximize();
       
         driver.get("https://www.barnesandnoble.com/");
         logj.info("opening url");
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         logj.warn("time wait");
         driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]/div")).click();
         logj.info("B&N Membership clicked");
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         logj.warn("time wait");
         driver.findElement(By.xpath("//*[@id=\"rewards-modal-link\"]")).click();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
         logj.warn("time wait");
         String tit = driver.findElement(By.xpath("//*[@id=\"dialog-title\"]")).getText();
         Assert.assertTrue(tit.contains("Sign in or Create an Account"));
      }

    @DataProvider(name ="one")
    public String fetchData() throws IOException
    {  
        FileInputStream fs=new FileInputStream("D:\\Software Testing\\cc\\excel.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(fs); 
        XSSFSheet sheet=workbook.getSheetAt(0); 
      
        XSSFRow row=sheet.getRow(0); 
        String val =row.getCell(0).getStringCellValue();
        fs.close();
        return val;
    }


    @AfterMethod
    public void tearDown(ITestResult result)throws Exception
    {
        if(result.getStatus()==ITestResult.FAILURE)
        {
            logger.log(Status.FAIL,"Testcase Failed : "+result.getName());
            logger.log(Status.FAIL,"Testcase Failed Reason: "+result.getThrowable());

            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String screenShotPath = "D:\\Software Testing\\cc\\log\\"+result.getName()+".png";
            FileUtils.copyFile(screenshot,new File(screenShotPath));
            logger.addScreenCaptureFromPath(screenShotPath);
        }
        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            logger.log(Status.PASS,"Test Case Passed: "+result.getName());
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String screenShotPath = "D:\\Software Testing\\cc\\log\\"+result.getName()+".png";
            FileUtils.copyFile(screenshot,new File(screenShotPath));
            logger.addScreenCaptureFromPath(screenShotPath);
           
        }
        else if(result.getStatus()==ITestResult.SKIP)
        {
            logger.log(Status.SKIP,"Test Case Skipped: "+result.getName());
        }
        
    }

    @AfterTest
    public void afterTest()
    {
        extent.flush();
    }
}
