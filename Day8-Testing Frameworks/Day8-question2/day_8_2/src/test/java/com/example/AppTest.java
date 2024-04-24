package com.example;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.experimental.theories.DataPoint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    WebDriver driver;
     @BeforeTest()
     public void BeforeTest() throws InterruptedException
     {
        WebDriverManager.chromedriver().setup();

        driver=new ChromeDriver();

        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
     }
     
    @Test
    public void test1() throws InterruptedException
    {

        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(3000);

        driver.findElement(By.linkText("MacBook air")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(3000);
        
        driver.findElement(By.linkText("Cart")).click();
        
    }

    @Test(dataProvider = "dat")
    public void test2(String use,String pass) throws InterruptedException
    { 
        driver.findElement(By.id("login2")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("loginusername")).sendKeys(use);
        driver.findElement(By.id("loginpassword")).sendKeys(pass);
        driver.findElement(By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]")).click();
        Thread.sleep(5000);
        
        WebElement element=driver.findElement(By.linkText("Welcome Testalpha"));
        Assert.assertEquals("Welcome Testalpha",element.getText());
        
    }

    @DataProvider(name = "dat")
public Object[][] fetchdata() throws IOException {
    FileInputStream fs = new FileInputStream("D:\\xl1.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fs);

    XSSFSheet sheet = workbook.getSheetAt(0);

    int rowcount = sheet.getLastRowNum()+1; 
    int col = 2;
    Object[][] data = new Object[1][col];

    for (int i = 1; i < rowcount; i++) {
        Row r = sheet.getRow(i);
        int g=i-1;
        if (r != null) { 
            for (int j = 0; j < col; j++) {
                Cell cell = r.getCell(j);
                if (cell != null) { 
                    data[g][j] = cell.getStringCellValue();
                    //System.out.println(data[i][j]);
                }
            }
        }
    }
    return data;
    }
    
}
