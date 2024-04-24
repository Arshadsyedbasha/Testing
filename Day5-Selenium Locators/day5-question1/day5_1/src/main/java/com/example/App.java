package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
       driver.get("https://www.demoblaze.com/");
       Thread.sleep(5000);
       driver.findElement(By.linkText("Laptops")).click();
       Thread.sleep(5000);
       driver.findElement(By.linkText("MacBook air")).click();
       Thread.sleep(5000);
       driver.findElement(By.linkText("Add to cart")).click();
       Thread.sleep(5000);
       driver.switchTo().alert().accept();
       Thread.sleep(5000);
       driver.findElement(By.linkText("Cart")).click();
       Thread.sleep(5000);
       WebElement table=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]"));
       String s=table.getText();
       WebElement table1=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[3]"));
       String s1=table1.getText();
       System.out.println("Title:"+s+"\nPrice:"+s1);
       
    }
}
