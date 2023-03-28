package uitest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckQuestionsTest {
    private final int answerNumber;
    private WebDriver driver;

    public CheckQuestionsTest(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getAnswersNumber() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void checkQuestionsTest() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.pressOnCookieButton();
        objMainPage.waitLogoToAppear();
        objMainPage.scrollToQuestionsSection();
        String[] answers = objMainPage.getAnswerList();

        String[] answersTexts = new String[8];
        answersTexts[0] = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        answersTexts[1] = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
        answersTexts[2] = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
        answersTexts[3] = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
        answersTexts[4] = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
        answersTexts[5] = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
        answersTexts[6] = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
        answersTexts[7] = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

        assertEquals("Текст не соответствует ожидаемому", answersTexts[answerNumber], answers[answerNumber]);

    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
