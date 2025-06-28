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
public class RegistrationPasswordValidationTests extends WebDriverBase {

    @Parameterized.Parameter
    public String testName;

    @Parameterized.Parameter(1)
    public String password;

    @Parameterized.Parameter(2)
    public Browser browser;

    @Parameterized.Parameters(name = "{0} ({2})")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"1 символ", "1", Browser.CHROME},
                {"4 символа", "1234", Browser.CHROME},
                {"5 символов", "12345", Browser.CHROME},
                {"1 символ", "1", Browser.YANDEX},
                {"4 символа", "1234", Browser.YANDEX},
                {"5 символов", "12345", Browser.YANDEX}
        });
    }

    @Before
    public void setUp() {
        initDriver(browser);
    }

    @Test
    @DisplayName("Проверка валидности пароля")
    public void testPasswordValidation() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterButton();

        String email = "test" + System.currentTimeMillis() + "@example.com";
        String name = "TestUser";

        registerPage.fillName(name);
        registerPage.fillEmail(email);
        registerPage.fillPassword(password);
        registerPage.clickRegisterButton();

        assertTrue("Этот пароль не должен быть принят: " + password + " в браузере " + browser,
                registerPage.isPasswordErrorDisplayed());

        assertEquals("Текст ошибки: " + browser,
                "Некорректный пароль",
                registerPage.getPasswordErrorText());
    }
}