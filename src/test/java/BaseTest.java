import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {

    private WebDriver driver;

    protected WebDriverWait wait;

    protected Actions actions;

    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();




    @BeforeMethod
    @Parameters("baseUrl")

    public void SetUpDriver(String url) throws MalformedURLException {

        driver = pickDriver(System.getProperty("browser"));
        threadLocal.set(driver);
        threadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //String url = "https://qa.koel.app/";
        threadLocal.get().get(url);
        wait = new WebDriverWait(threadLocal.get(), java.time.Duration.ofSeconds(10));
        actions = new Actions(threadLocal.get());
        int width = 1920;
        int height = 1080;
        Dimension dimension = new Dimension(width, height);
        threadLocal.get().manage().window().setSize(dimension);
    }

    public WebDriver getDriver(){
        return threadLocal.get();
    }


    @AfterMethod
    public void closeBrowser() {
        getDriver().quit();
    }


    public WebDriver pickDriver(String browser) throws MalformedURLException {
        String gridUrl = "http://192.168.0.110:4444";
        ChromeOptions options = new ChromeOptions();
        EdgeOptions edgeOptions = new EdgeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browser) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.addArguments("--disable-notifications");
                return driver = new EdgeDriver(edgeOptions);

            case "chrome":
                WebDriverManager.chromedriver().setup();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                return driver = new ChromeDriver(options);

            case "grid":
                capabilities.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), capabilities);

            case "lambda":

                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), capabilities);
            default:
                WebDriverManager.chromedriver().setup();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                return driver = new ChromeDriver(options);
        }
    }

}

