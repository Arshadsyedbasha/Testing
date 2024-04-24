package com.example;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    WebDriver driver;

    @BeforeTest()
    public void BeforeMethod()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("http://dbankdemo.com/bank/login");
        driver.manage().window().maximize();

    }
    
    @Test(dataProvider = "dat")
    public void test1(String username,String pass)
    {
        SoftAssert assert1=new SoftAssert();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.id("submit")).submit();
        
        assert1.assertTrue(driver.getTitle().contains("Home"));

    }

    @Test(dataProvider = "dat")
    public void test2(String username,String pass) throws InterruptedException
    {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.id("submit")).submit();
        Thread.sleep(3000);
        
        driver.findElement(By.linkText("Deposit")).click();
        Thread.sleep(3000);
        WebElement w=driver.findElement(By.id("selectedAccount"));
        Select ss=new Select(w);
        ss.selectByVisibleText("Individual Checking (Standard Checking)");

        driver.findElement(By.id("amount")).sendKeys("5000");
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).submit();
        
        
        
    }
    
    @Test(dataProvider = "dat")
    public void test3(String username,String pass) throws InterruptedException
    {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.id("submit")).submit();
        Thread.sleep(3000);
        
        driver.findElement(By.linkText("Withdraw")).click();
        Thread.sleep(3000);

        WebElement w=driver.findElement(By.id("selectedAccount"));
        Select ss=new Select(w);
        ss.selectByVisibleText("Individual Checking (Standard Checking)");

        driver.findElement(By.id("amount")).sendKeys("3000");
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).submit();
  
    }
    @DataProvider(name = "dat")
public Object[][] fetchdata() throws IOException {
    FileInputStream fs = new FileInputStream("D:\\xl1.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fs);

    XSSFSheet sheet = workbook.getSheetAt(0);

    int rowcount = sheet.getLastRowNum() + 1; 
    int col = 2;
    Object[][] data = new Object[rowcount][col];

    for (int i = 0; i < rowcount; i++) {
        Row r = sheet.getRow(i);
        
        if (r != null) { 
            for (int j = 0; j < col; j++) {
                Cell cell = r.getCell(j);
                if (cell != null) { 
                    data[i][j] = cell.getStringCellValue();
                    System.out.println(data[i][j]);
                }
            }
        }
    }
    return data;
  }
}