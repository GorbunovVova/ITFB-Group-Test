package ru.yandex.market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FirstTest {
    private WebDriver driver;
    private String driverPath = "C:\\Users\\July\\Downloads\\chromedriver_win32\\chromedriver.exe";
    private String driverName = "webdriver.chrome.driver";

    @Before
    public void setUp() {
        System.setProperty(driverName, driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://market.yandex.ru");
        driver.manage().window().setSize(new Dimension(1936, 1056));
    }

    @Test
    public void yandexMarketTest() throws InterruptedException {
        Page homePage = new Page(driver);
        Page allCategories = homePage.goToAllCategories();
        Page productsForAnimals = allCategories.goToProductsForAnimals();
        Page treatsForCats = productsForAnimals.goToTreatsForCats();
        treatsForCats.setFiltersToWhiskas();
        Page firstItem = treatsForCats.goToFirstItem();
        firstItem.compare();
        Page treatsForCats2 = firstItem.backToTreatsForCats();
        treatsForCats2.setFiltersToMnyams();
        Page secondItem = treatsForCats2.goToSecondItem();
        secondItem.compare();
        Page comparing = secondItem.goToComparing();
        int firstPrice = comparing.getPrice(1);
        int secondPrice = comparing.getPrice(2);
        assertTrue("Сумма товаров < 300", firstPrice + secondPrice < 300);
        assertTrue("В сравнении есть Whiskas", comparing.contains("Whiskas"));
        comparing.deleteWhiskas();
        assertFalse("В сравнении нет Whiskas", comparing.contains("Whiskas"));
        comparing.deleteItems();
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
