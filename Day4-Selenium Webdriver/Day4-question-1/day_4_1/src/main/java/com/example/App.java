package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver1=new ChromeDriver();
        driver1.manage().window().maximize();
        WebDriverManager.edgedriver().setup();
        WebDriver driver2=new EdgeDriver();
        driver2.manage().window().maximize();
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver3=new FirefoxDriver();
        driver3.manage().window().maximize();
        WebDriverManager.chromedriver().setup();
        WebDriver driver4=new ChromeDriver();
        driver4.manage().window().maximize();
        driver4.get("https://www.shoppersstop.com/");
        driver4.findElement(By.className("user-icon")).click();
    }
}
