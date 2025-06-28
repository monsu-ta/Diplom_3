package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;
import tests.WebDriverBase;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTests extends WebDriverBase {
    @Parameterized.Parameter
    public WebDriverBase.Browser browser;

    @Parameterized.Parameters(name = "{0}")
    public static Browser[] browsers() {
        return new Browser[] { Browser.CHROME, Browser.YANDEX };
    }

    @Before
    public void setUp() {
        initDriver(browser);
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт»")
    public void testLoginFromMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginInAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("pushkin@mail.ru");
        loginPage.enterPassword("123123");
        loginPage.clickLoginButton();

        assertTrue(driver.findElement(mainPage.placeAnOrder).isDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void testLoginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("pushkin@mail.ru");
        loginPage.enterPassword("123123");
        loginPage.clickLoginButton();

        assertTrue(driver.findElement(mainPage.placeAnOrder).isDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме регистрации")
    public void testLoginFromRegistrationFormButton() {
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterButton();
        registerPage.clickEnterButtonRegisterForm();

        loginPage.enterEmail("pushkin@mail.ru");
        loginPage.enterPassword("123123");
        loginPage.clickLoginButton();

        assertTrue(driver.findElement(mainPage.placeAnOrder).isDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме восстановления пароля")
    public void testLoginFromPasswordRecoveryFormButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickForgotPasswordButton();
        registerPage.clickEnterButtonForgotPasswordForm();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("pushkin@mail.ru");
        loginPage.enterPassword("123123");
        loginPage.clickLoginButton();

        assertTrue(driver.findElement(mainPage.placeAnOrder).isDisplayed());
    }
}