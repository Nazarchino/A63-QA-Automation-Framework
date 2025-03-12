import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    private WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    @BeforeMethod

    @Parameters("baseUrl")
    public void setUpDriver(String url) throws MalformedURLException {
        driver = pickDriver(System.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        threadLocal.set(driver);

        threadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        threadLocal.get().get(url);
        wait = new WebDriverWait(threadLocal.get(), Duration.ofSeconds(10));
        actions = new Actions(threadLocal.get());
        int width = 1920;
        int height = 1080;
        Dimension dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);
    }

    public WebDriver getDriver() {
        return threadLocal.get();
    }

    @AfterMethod
    public void closeBrowser() {
        getDriver().quit();
    }

    public WebDriver pickDriver(String browser) throws MalformedURLException {
        String gridUrl = "http://192.168.1.220:4444/";
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                return driver = new ChromeDriver(options);
            case "edge":
                WebDriverManager.edgedriver().setup();
                return driver = new EdgeDriver();
            case "grid":
                capabilities.setCapability("browserName", "edge");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), capabilities);
            case "lambda":
                String hub = "@hub.lambdatest.com/wd/hub";
                String userName = "nazarchino";
                String apiKey = "LT_dMHpzRbcLO7GJJiv2sQ5EnurzDcDeYOm8TJSnHNgLTK3A2Z";
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("browserVersion", "133.0");
                HashMap<String, Object> ltOptions = new HashMap<>();
                ltOptions.put("username", userName);
                ltOptions.put("accessKey", apiKey);
                ltOptions.put("platformName", "Windows 10");
                ltOptions.put("project", "Koel");
                ltOptions.put("w3c", true);
                ltOptions.put("plugin", "java-testNG");
                capabilities.setCapability("LT:Options", ltOptions);
                return driver = new RemoteWebDriver(URI.create("https://" + userName + ";" + apiKey + hub).toURL(), capabilities);
            default:
                WebDriverManager.chromedriver().setup();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                return driver = new ChromeDriver(options);
        }
    }
}