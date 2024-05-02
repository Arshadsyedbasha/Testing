package com.example;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Fcry {
       WebDriver driver;
       WebDriverWait wait;
    @Test(priority=1)
    public void test1() throws InvalidFormatException, IOException {
        FileInputStream fs = new FileInputStream("C:\\Users\\ARSHAD\\Desktop\\FirstCry\\fcry.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(row.getCell(0).getStringCellValue());
        driver.manage().window().maximize();
        String searchTerm = row.getCell(1).getStringCellValue();
         WebElement searchInput = driver.findElement(By.xpath("//*[@id='search_box']"));
        searchInput.click();
        searchInput.sendKeys(searchTerm, Keys.ENTER);
        Assert.assertTrue(driver.getCurrentUrl().contains("kids-toys"), "URL contains 'kids-toys'. Failed!");
    }
    @Test(priority=2)
    public void test2() throws Exception
     {
        FileInputStream fs = new FileInputStream("C:\\Users\\ARSHAD\\Desktop\\FirstCry\\fcry.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(row.getCell(0).getStringCellValue());
        driver.manage().window().maximize();
        String searchTerm = row.getCell(2).getStringCellValue();
         WebElement searchInput = driver.findElement(By.xpath("//*[@id='search_box']"));
        searchInput.click();
        searchInput.sendKeys(searchTerm, Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[4]")).click();
        Thread.sleep(2000);
        String text=driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[1]/div[1]/h1")).getText();
        Assert.assertTrue(text.contains("Ethnic"));
    }
    @Test(priority=3)
    public void test3() throws Exception
     {
        FileInputStream fs = new FileInputStream("C:\\Users\\ARSHAD\\Desktop\\FirstCry\\fcry.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(row.getCell(0).getStringCellValue());
        driver.manage().window().maximize();
        String searchTerm = row.getCell(3).getStringCellValue();
         WebElement searchInput = driver.findElement(By.xpath("//*[@id='search_box']"));
        searchInput.click();
        searchInput.sendKeys(searchTerm, Keys.ENTER);
        String productname=driver.findElement(By.xpath("//*[@id='maindiv']/div[1]/div/div[1]/div[2]/a")).getText();
      driver.findElement(By.xpath("//*[@id='maindiv']/div[1]/div/div[1]/div[2]/a")).click();
      Thread.sleep(4000);
      Set<String>s=driver.getWindowHandles();
      for(String it:s)
      {
         if(!it.equals(driver.getWindowHandle()))
         {
            driver.switchTo().window(it);
            break;
         }
      }
      String pname=driver.findElement(By.xpath("//*[@id=\"prod_name\"]")).getText();
      Assert.assertEquals(productname,pname);
    }
    
    
}
