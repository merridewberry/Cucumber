package IMDb.steps.hooks;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import IMDb.Values;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

import static IMDb.Values.URL;

public class Hooks {

    public static WebDriver driver;
    public static Actions builder;

    @Before (order = 10)
    public void prepareTest() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications")
                .addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        builder = new Actions(driver);
    }

    @Before (order = 20)
    public void prepareMaps() {
        Values.populateMap();
    }

    @Before (order = 30)
    public void get(){
        driver.get(URL);
    }

    @After
    public void quit() {
        driver.quit();
    }
}
