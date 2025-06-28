package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ConstructorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By bunsTab = By.xpath("//span[text()='Булки']/..");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/..");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/..");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Кликнуть на вкладку 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Кликнуть на вкладку 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Кликнуть на вкладку 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Получить текст активной вкладки")
    public String getActiveTabText() {
        return driver.findElement(activeTab).getText();
    }
}