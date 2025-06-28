package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class WebDriverBase {
    protected WebDriver driver;

    protected enum Browser { CHROME, YANDEX }

    protected void initDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                setupChromeDriver();
                break;
            case YANDEX:
                setupYandexDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        Allure.addAttachment("Browser info", "text/plain",
                "Browser: Chrome\nDriver: ChromeDriver");
    }

    private void setupYandexDriver() {
        File yandexDriver = new File("C:\\drivers\\yandexdriver.exe");
        if (!yandexDriver.exists()) {
            throw new RuntimeException("YandexDriver not found at: " + yandexDriver.getAbsolutePath());
        }

        System.setProperty("webdriver.chrome.driver", yandexDriver.getAbsolutePath());

        ChromeOptions options = new ChromeOptions();
        options.setBinary(findYandexBrowserPath());
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        Allure.addAttachment("Browser info", "text/plain",
                "Browser: Yandex\nDriver: YandexDriver");
    }

    private String findYandexBrowserPath() {
        Path[] possiblePaths = {
                Paths.get(System.getenv("LOCALAPPDATA"), "Yandex", "YandexBrowser", "Application", "browser.exe"),
                Paths.get(System.getenv("ProgramFiles"), "Yandex", "YandexBrowser", "browser.exe"),
                Paths.get(System.getenv("ProgramFiles(x86)"), "Yandex", "YandexBrowser", "browser.exe")
        };

        for (Path path : possiblePaths) {
            if (path.toFile().exists()) {
                return path.toString();
            }
        }
        throw new RuntimeException("Yandex browser not found");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            takeScreenshot("Final state");
            driver.quit();
        }
    }

    protected void takeScreenshot(String name) {
        Allure.addAttachment(name, new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}