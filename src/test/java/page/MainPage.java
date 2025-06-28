package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginInAccountButton = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");
    private final By personalAccountButton = By.xpath("//*[@id=\"root\"]/div/header/nav/a/p");
    public final By placeAnOrder = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        wait.until(ExpectedConditions.urlContains("stellarburgers"));
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginInAccountButton() {
        clickElement(loginInAccountButton, "Кнопка 'Войти в аккаунт'");
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        clickElement(personalAccountButton, "Кнопка 'Личный кабинет'");
    }

    private void clickElement(By locator, String elementName) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при клике " + elementName + ": " + e.getMessage(), e);
        }
    }
}