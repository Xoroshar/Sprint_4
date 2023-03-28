package uitest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.Definitions;
import pageobjects.MainPage;
import pageobjects.OrderPage;
import pageobjects.RentPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String name;
    private final String surname;
    private final String addres;
    private final String metro;
    private final String telephone;
    private final String date;
    private final String comment;
    private final String duration, colour, orderButtonString;
    private WebDriver driver;

    public OrderTest(String orderButtonString, String name, String surname, String addres, String metro, String telephone, String date, String duration, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.addres = addres;
        this.metro = metro;
        this.telephone = telephone;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
        this.orderButtonString = orderButtonString;
    }

    @Parameterized.Parameters
    public static Object[][] getOrder() {
        return new Object[][]{
                {Definitions.UP, "Алексей", "Аква", "Казань", "Лубянка", "89046784355", "23.04.2023", "четверо суток", Definitions.BLACK, "no"},
                {Definitions.UP, "Анна", "Аква", "Казань", "Лубянка", "89046784355", "23.04.2024", "трое суток", Definitions.GREY, ""},
                {Definitions.DOWN, "Алексей", "Аква", "Казань", "Лубянка", "89046784355", "23.04.2023", "четверо суток", Definitions.BLACK, "no"},
                {Definitions.DOWN, "Анна", "Аква", "Казань", "Лубянка", "89046784355", "23.04.2024", "трое суток", Definitions.GREY, ""},
        };
    }

    @Test
    public void orderTest() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.pressOnCookieButton();
        objMainPage.pressOnOrderButton(orderButtonString);

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillOrderFields(name, surname, addres, metro, telephone);

        RentPage objRentPage = new RentPage(driver);
        objRentPage.inputAllFieldsAndPressOrderButton(date, duration, Definitions.BLACK, comment);
        assertTrue("Window about order doesn't appear", objRentPage.checkOrderIsSuccessful());
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
