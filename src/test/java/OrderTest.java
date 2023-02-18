import org.example.MainPage;
import org.example.OrderPage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderTest {
    private final int orderButton;
    private final String firstname;
    private final String secondname;
    private final String address;
    private final String metroStationName;
    private final String phoneNumber;
    private final String date;
    private final int amountOfDays;
    private final int colour;
    private WebDriver driver;

    public OrderTest(int orderButton, String firstname, String secondname, String address, String metroStationName,
                     String phoneNumber, String date, int amountOfDays, int colour) {
        this.orderButton = orderButton;
        this.firstname = firstname;
        this.secondname = secondname;
        this.address = address;
        this.metroStationName = metroStationName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.amountOfDays = amountOfDays;
        this.colour = colour;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsInfo() {
        return new Object[][]{
                {0, "Василий", "Пупкин", "улица Зеленая, дом 1, квартира 2", "Сокольники", "89634567890", "28.02.2023", 2, 0},
                {1, "Анжела", "Бипкина", "улица Длинная, дом 123, квартира 25", "Академическая", "+79911234567", "01.05.2024", 5, 1}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookieIfButtonExist();
        mainPage.clickOrderButton(orderButton);
    }

    @Test
    public void successfulOrderTest() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstForm(firstname, secondname, address, metroStationName, phoneNumber);
        orderPage.clickNextButton();
        orderPage.fillSecondForm(date, amountOfDays, colour);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        String actual = orderPage.getResultText();
        MatcherAssert.assertThat("Должно появиться окно с сообщением об успешном создании заказа",
                actual, containsString("Заказ оформлен"));
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
