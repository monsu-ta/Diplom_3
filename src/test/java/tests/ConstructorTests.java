package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.ConstructorPage;
import page.MainPage;
import tests.WebDriverBase;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ConstructorTests extends WebDriverBase {
    @Parameterized.Parameter
    public WebDriverBase.Browser browser;

    @Parameterized.Parameters(name = "{0}")
    public static WebDriverBase.Browser[] browsers() {
        return new WebDriverBase.Browser[] { WebDriverBase.Browser.CHROME, WebDriverBase.Browser.YANDEX };
    }

    @Before
    public void setUp() {
        initDriver(browser);

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void testBunsSection() {

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();
        constructorPage.clickBunsTab();

        assertEquals("Булки", constructorPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void testSaucesSection() {

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();

        assertEquals("Соусы", constructorPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void testFillingsSection() {

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickFillingsTab();

        assertEquals("Начинки", constructorPage.getActiveTabText());
    }
}