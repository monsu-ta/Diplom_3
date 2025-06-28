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

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SuccessfulRegistrationTest extends WebDriverBase {

    @Parameterized.Parameter
    public Browser browser;

    @Parameterized.Parameters(name = "Успешная регистрация")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {Browser.CHROME},
                {Browser.YANDEX}
        });
    }

    @Before
    public void setUp() {
        initDriver(browser);
    }

    @Test
    @DisplayName("Тест успешной регистрации")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterButton();

        String email = "test" + System.currentTimeMillis() + "@example.com";
        String name = "TestUser";
        String validPassword = "123456";

        registerPage.fillName(name);
        registerPage.fillEmail(email);
        registerPage.fillPassword(validPassword);
        registerPage.clickRegisterButton();

        LoginPage newLoginPage = new LoginPage(driver);
        assertTrue("Должен отображаться заголовок 'Вход'",
                newLoginPage.isLoginHeaderDisplayed());
    }
}