package com.example;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
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
    @BeforeTest
    void beforeit()
    {
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
    driver.manage().window().maximize();
    }
    @Test
    void Test1() throws InterruptedException
    {
    driver.get("https://www.moneycontrol.com/");
    }
    @Test
    void Test2() throws InterruptedException
    {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15000));
    driver.findElement(By.id("search_str")).sendKeys("Reliance Industries");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15000));
    driver.findElement(By.partialLinkText("Reliance Industries")).click();
    Thread.sleep(3000);
    }
    @Test
    void Test3()
    {
    String s=driver.findElement(By.xpath("//*[@id='stockName']/h1")).getText();
    if(s.equals("Reliance Industries Ltd."))
    {
        System.out.println("Got Stock Details of Reliance Industries");
    }
    else
    {
        System.out.println("Fails to get");
    }
    }
    @Test
    void Test4() throws InterruptedException
    {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15000));
    driver.findElement(By.linkText("SIP Return")).click();
}
@Test
void Test5() throws InterruptedException
{
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15000));
        WebElement e1=driver.findElement(By.xpath("//*[@id='ff_id']"));
        Select s1=new Select(e1);
        s1.selectByValue("Axis Mutual Fund");
        Thread.sleep(2000);
        WebElement e2=driver.findElement(By.xpath("//*[@id='im_id']"));
        Select s2=new Select(e2);
        s2.selectByValue("Axis Arbitrage Fund - Regular Plan (D)");
        Thread.sleep(3000);
        driver.findElement(By.name("invamt")).sendKeys("100000");
        WebElement e3=driver.findElement(By.xpath("//*[@id='frq']"));
        Select s3=new Select(e3);
        s3.selectByValue("Monthly");
        Thread.sleep(3000);
        driver.findElement(By.id("stdt")).sendKeys("2021-08-02");
        driver.findElement(By.id("endt")).sendKeys("2023-08-17");
        driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input")).click();
        Thread.sleep(4000);
    }
    @Test
    void finaltest()
    {
        String s1=driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[1]/td[2]")).getText();
        String s2=driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[3]/td[2]")).getText();
        System.out.println("Investment Period: "+s1+"\nTotal Amount Invested (Rs): "+s2);
    }
}
