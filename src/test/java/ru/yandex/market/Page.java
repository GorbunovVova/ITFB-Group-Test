package ru.yandex.market;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Page {
    private final WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public Page goToAllCategories() {
        driver.findElement(By.xpath("//span[text()=\"Все категории\"]")).click();
        return new Page(driver);
    }

    public Page goToProductsForAnimals() {
        driver.findElement(By.linkText("Товары для животных")).click();
        return new Page(driver);
    }

    public Page goToTreatsForCats() {
        driver.findElement(By.linkText("Лакомства")).click();
        return new Page(driver);
    }

    public void setFiltersToWhiskas() throws InterruptedException {
        driver.findElement(By.id("glpricefrom")).sendKeys("50");
        driver.findElement(By.id("glpriceto")).sendKeys("150");
        driver.findElement(By.xpath("//span[text()=\"Whiskas\"]")).click();
        try {
            driver.findElement((By.xpath("//span[text()=\"С доставкой\"]")));
        } catch (NoSuchElementException e) {
        }
        Thread.sleep(3000);
    }

    public Page goToFirstItem() {
        driver.findElement(By.cssSelector("[class~=\"n-snippet-list\"] > div:first-of-type > div:nth-of-type(4) > div:first-of-type > div:first-of-type > a")).click();
        return new Page(driver);
    }

    public void compare() {
        driver.findElement(By.xpath("//div[@class=\"n-product-title-features__toolbar\"]//span[text()=\"Сравнить\"]")).click();
    }

    public Page backToTreatsForCats() {
        driver.navigate().back();
        return new Page(driver);
    }

    public void setFiltersToMnyams() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()=\"Whiskas\"]")).click();
        driver.findElement(By.xpath("//span[text()=\"Мнямс\"]")).click();
        Thread.sleep(3000);
    }

    public Page goToSecondItem() {
        driver.findElement(By.cssSelector("[class~=\"n-snippet-list\"] > div:nth-of-type(2) > div:nth-of-type(4) > div:first-of-type > div:first-of-type > a")).click();
        return new Page(driver);
    }

    public Page goToComparing() {
        driver.findElement(By.xpath("//a[@href=\"/compare?track=head\"]")).click();
        return new Page(driver);
    }

    public int getPrice(int i) {
        String strPrice = driver.findElement(By.cssSelector("[class=\"n-compare-row n-compare-row_type_price\"] > div > div:nth-of-type(" + i + ") div.price")).getText();
        String[] str = strPrice.split(" ");
        return Integer.parseInt(str[1]);
    }

    public boolean contains(String productName) {
        try {
            String strProduct = driver.findElement(By.xpath("//div[@class=\"n-compare-content__line i-bem n-compare-content__line_js_inited\"]/div[2]/a")).getText();
            return strProduct.indexOf(productName) != -1 ? true : false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void deleteWhiskas() {
        driver.findElement(By.xpath("//div[@class=\"n-compare-content__line i-bem n-compare-content__line_js_inited\"]/div[2]/a")).click();
        driver.findElement(By.xpath("//div[@class=\"n-product-title-features__toolbar\"]//span[text()=\"В сравнении\"]")).click();
        driver.findElement(By.xpath("//a[@href=\"/compare?track=head\"]")).click();
    }

    public void deleteItems() throws InterruptedException {
        driver.findElement((By.xpath("//span[text()=\"Удалить список\"]"))).click();
        Thread.sleep(2000);
    }
}
