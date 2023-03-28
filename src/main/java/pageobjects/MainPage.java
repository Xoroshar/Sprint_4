package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
    private final WebDriver driver;

    // заголовок "Самоат на пару дней"
    private final By samokatHeader = By.className("Home_Header__iJKdX");

    // кнопка для подтверждения куки
    private final By cookieButton = By.id("rcc-confirm-button");

    // заголовк вопросов о важном
    private final By mainQuestions = By.className("accordion__heading");

    // кнопка для заказа нижняя
    private final By orderDownButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    // кнопка для заказа верхняя
    private final By orderUpButton = By.className("Button_Button__ra12g");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLogoToAppear() {
        new WebDriverWait(driver, 5).until(driver -> (driver.findElement(samokatHeader).getText() != null
                && !driver.findElement(samokatHeader).getText().isEmpty()));
    }

    public void pressOnCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void scrollToQuestionsSection() {
        WebElement element = driver.findElement(mainQuestions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String[] getAnswerList() {
        List<WebElement> questions = driver.findElements(By.className("accordion__button"));
        String[] answersRez = new String[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).click();
            if (questions.get(i).getAttribute("aria-expanded").equals("true")) {
                answersRez[i] = driver.findElements(By.className("accordion__panel")).get(i).getText();
            }
        }
        return answersRez;
    }

    public void pressOnOrderUpButton() {
        driver.findElement(orderUpButton).click();
    }

    public void pressOnOrderDownButton() {
        WebElement element = driver.findElement(orderDownButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderDownButton).click();
    }

    public void pressOnOrderButton(String orderButton) {
        if (orderButton.equals(Definitions.UP)) {
            pressOnOrderUpButton();
        } else pressOnOrderDownButton();
    }

}
