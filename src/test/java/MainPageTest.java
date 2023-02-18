import org.example.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MainPageTest {

    private final int questionIndex;
    private final String expectedAnswerText;
    private WebDriver driver;

    public MainPageTest(int questionsIndex, String expectedAnswerText) {
        this.questionIndex = questionsIndex;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsInfo() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. " +
                        "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                        "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. " +
                        "Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. " +
                        "Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}

        };
    }

    @Before
    public void setDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void questionsTextTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickDropdownElement(questionIndex);
        String actualText = mainPage.getOpenElementText();
        assertEquals("Ответ на вопрос №" + questionIndex + 1 + " должен сопадать с эталоном",
                expectedAnswerText, actualText);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}