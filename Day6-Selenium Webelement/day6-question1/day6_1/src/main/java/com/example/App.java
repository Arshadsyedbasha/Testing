package com.example;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://economictimes.indiatimes.com/");
        Thread.sleep(5000);
        driver.findElement(By.linkText("Mutual Funds")).click();
        Thread.sleep(3000);
        WebElement e1=driver.findElement(By.name("amc"));
        Select select1=new Select(e1);
        select1.selectByVisibleText("Canara Robeco");
        Thread.sleep(2000);
        WebElement e2=driver.findElement(By.name("schemenm"));
        Select select2=new Select(e2);
        select2.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Get Details")).click();
        Thread.sleep(3000);
        List<String> handle=new ArrayList<>(driver.getWindowHandles());
        String tab2=handle.get(1);
        driver.switchTo().window(tab2);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='installment_type']/li/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='installment_type']/li/ul/li[1]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='installment_amt']/li/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='installment_amt']/li/ul/li[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='installment_period']/li/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='installment_period']/li/ul/li[4]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='mfNav']/div/ul/li[2]")).click();
        Thread.sleep(3000);
        String sp=driver.findElement(By.xpath("//*[@id='mfReturns']/div[2]/div[2]/ul/li[1]/table/tbody/tr[1]/td[6]")).getText();
        System.out.print("Annualized Returns: "+sp);
    }
}
