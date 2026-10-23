package engine;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver implements TestBase {
    
    public static WebDriver driver = null;

    public static WebDriver get() {
        if( driver == null ) {
            System.setProperty(properties.getProperty("selenium.chromedriver.property"), properties.getProperty("selenium.chromedriver.path"));
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout( Duration.ofSeconds(50) );
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.get(properties.getProperty("application.prod.url"));
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
