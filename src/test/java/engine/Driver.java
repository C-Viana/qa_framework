package engine;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import config.Config;

public class Driver {
    
    public static WebDriver driver = null;

    private static ChromeOptions configDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        if(Config.get("test.headless").toLowerCase().equals("true"))
            options.addArguments("--headless=new");
        return options;
    }

    public static WebDriver get() {
        if( driver == null ) {
            driver = new ChromeDriver(configDriver());
            
            driver.manage().timeouts().pageLoadTimeout( Duration.ofSeconds(50) );
            driver.get(Config.get("application.prod.url"));
        }
        return driver;
    }

    public static void close() {
        if( driver != null ) {
            driver.quit();
            driver = null;
        }
    }

}
