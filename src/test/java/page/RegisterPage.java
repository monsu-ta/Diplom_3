package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameField = By.cssSelector("input[name='name']");
    private final By emailField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    private final By forgotPasswordButton = By.cssSelector("#root > div > main > div > div > p:nth-child(2) > a");
    private final By enterButtonRegisterForm = By.cssSelector("#root > div > main > div > div > p > a");
    private final By enterButtonForgotPasswordForm = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    private final By passwordError = By.xpath("//p[contains(text(),'Некорректный пароль')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Ввести имя '{name}'")
    public void fillName(String name) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(nameField));
        element.clear();
        element.sendKeys(name);
    }

    @Step("Ввести email '{email}'")
    public void fillEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        element.clear();
        element.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void fillPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        element.clear();
        element.sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Нажать кнопку 'Войти' в форме регистрации")
    public void clickEnterButtonRegisterForm() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButtonRegisterForm)).click();
    }

    @Step("Нажать кнопку 'Восстановить пароль'")
    public void clickForgotPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton)).click();
    }

    @Step("Нажать кнопку 'Войти' в форме восстановления пароля")
    public void clickEnterButtonForgotPasswordForm() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButtonForgotPasswordForm)).click();
    }

    @Step("Проверить отображение ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }
}