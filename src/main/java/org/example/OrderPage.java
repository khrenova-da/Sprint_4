package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private static final By FIRST_NAME = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and contains(@placeholder, 'Имя')]");
    private static final By SECOND_NAME = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and contains(@placeholder, 'Фамилия')]");
    private static final By ADDRESS = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and contains(@placeholder, 'Адрес')]");
    private static final By METRO_STATION_INPUT = By.xpath("//input[@class='select-search__input']");
    private static final By METRO_STATION_BUTTON = By.xpath("//div/ul/li/button[@tabindex='-1']");
    private static final By PHONE = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and contains(@placeholder, 'Телефон')]");
    private static final By NEXT_BUTTON = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Далее')]");
    private static final By DATE = By.xpath("//div[@class='react-datepicker__input-container']/input");
    private static final By NON_CLICKABLE_TEXT = By.xpath("//div[text()='Про аренду']");
    private static final By DURATION_FIELD = By.xpath("//div[@class='Dropdown-placeholder']");
    private static final By DURATION_OPTIONS = By.xpath("//div[@class='Dropdown-option']");
    private static final By COLOUR_OPTIONS = By.xpath("//input[@class='Checkbox_Input__14A2w']");
    private static final By ORDER_BUTTON = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Заказать')]");
    private static final By POPUP_YES_BUTTON = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    private static final By POPUP_RESULT_TEXT = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");
    private final WebDriver driver;


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstName(String firstname) {
        driver.findElement(FIRST_NAME).clear();
        driver.findElement(FIRST_NAME).sendKeys(firstname);
    }

    public void fillSecondName(String secondname) {
        driver.findElement(SECOND_NAME).clear();
        driver.findElement(SECOND_NAME).sendKeys(secondname);

    }

    public void fillAddress(String address) {
        driver.findElement(ADDRESS).clear();
        driver.findElement(ADDRESS).sendKeys(address);
    }

    public void chooseMetroStation(String metroStationName) {
        driver.findElement(METRO_STATION_INPUT).sendKeys(metroStationName);
        driver.findElement(METRO_STATION_BUTTON).click();
    }

    public void fillPhoneNumber(String phoneNumber) {
        driver.findElement(PHONE).clear();
        driver.findElement(PHONE).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(NEXT_BUTTON));
        driver.findElement(NEXT_BUTTON).click();
    }

    public void fillDate(String date) {
        driver.findElement(DATE).sendKeys(date);
        driver.findElement(NON_CLICKABLE_TEXT).click();
    }

    public void fillDuration(int amountOfDays) {
        driver.findElement(DURATION_FIELD).click();
        driver.findElements(DURATION_OPTIONS).get(amountOfDays - 1).click();
    }

    public void chooseColour(int colour) {
        driver.findElements(COLOUR_OPTIONS).get(colour).click();
    }

    public void clickOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(ORDER_BUTTON));
        driver.findElement(ORDER_BUTTON).click();
    }

    public void clickYesButton() {
        driver.findElement(POPUP_YES_BUTTON).click();
    }

    public String getResultText() {
        return driver.findElement(POPUP_RESULT_TEXT).getText();
    }

    public void fillFirstForm(String firstname, String secondname, String address,
                              String metroStationName, String phoneNumber) {
        fillFirstName(firstname);
        fillSecondName(secondname);
        fillAddress(address);
        chooseMetroStation(metroStationName);
        fillPhoneNumber(phoneNumber);
    }

    public void fillSecondForm(String date, int amountOfDays, int colour) {
        fillDate(date);
        fillDuration(amountOfDays);
        chooseColour(colour);
    }
}
