package ru.aplana.objpajes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Init {
    private static WebDriver driver;
    public static TestProperties props = TestProperties.getInstance();

    public static void initDriver(){
        System.setProperty("webdriver.chrome.driver", props.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty("timeout.global")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty("timeout.pageLoad")), TimeUnit.SECONDS);
        driver.get(props.getProperty("url"));
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
