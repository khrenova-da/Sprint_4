package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By ACCEPT_COOKIE_BUTTON = By.id("rcc-confirm-button");
    private static final By DROPDOWN_ELEMENTS = By.xpath("//div[@class='accordion__item']");
    private static final By DROPDOWN_ELEMENT_TEXT = By.xpath("//div[@class='accordion__panel' and not (@hidden)]");
    private static final By ORDER_BUTTONS = By.xpath("//button[contains(@class,'Button_Button__ra12g') and text()='Заказать']");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }

    public void clickDropdownElement(int questionIndex) {
        List<WebElement> dropdownList = driver.findElements(DROPDOWN_ELEMENTS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", dropdownList.get(questionIndex));
        dropdownList.get(questionIndex).click();
    }

    public String getOpenElementText() {
        return driver.findElement(DROPDOWN_ELEMENT_TEXT).getText();
    }

    public void clickOrderButton(int button) {
        List<WebElement> buttons = driver.findElements(ORDER_BUTTONS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttons.get(button));
        buttons.get(button).click();
    }

    public void acceptCookieIfButtonExist() {
        if (driver.findElement(ACCEPT_COOKIE_BUTTON).isDisplayed()) {
            driver.findElement(ACCEPT_COOKIE_BUTTON).click();
        }
    }
}
