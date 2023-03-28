package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentPage {
    private final WebDriver driver;
    private final By startDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By duration = By.xpath(".//div[@class='Dropdown-placeholder']");
    private final By blackColor = By.xpath("//*[@id=\"black\"]");
    private final By greyColor = By.xpath("//*[@id=\"grey\"]");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/*[text()='Заказать']");
    private final By yesButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/*[text()='Да']");
    private final By successfulMessage = By.xpath("//*[text()='Заказ оформлен']");
    String dropDownMenu = ".//div[@class='Dropdown-menu']";

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputStartDate(String date) {
        driver.findElement(startDate).sendKeys(date);
        driver.findElement(startDate).sendKeys(Keys.ENTER);
    }

    public void inputComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void inputDuration(String durationString) {
        driver.findElement(duration).click();
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(By.xpath(dropDownMenu)).getAttribute("aria-expanded").equals("true")));
        String durationOption = String.format(".//div[@class='Dropdown-root is-open']//*[text()='%s']", durationString);
        driver.findElement(By.xpath(durationOption)).click();
    }

    public void inputAllFieldsAndPressOrderButton(String date, String duration, String color, String comment) {
        inputStartDate(date);
        inputDuration(duration);
        inputComment(comment);
        if (color.equals(Definitions.BLACK)) {
            driver.findElement(blackColor).click();
        } else {
            driver.findElement(greyColor).click();
        }
        driver.findElement(orderButton).click();
        driver.findElement(yesButton).click();
    }

    public boolean checkOrderIsSuccessful() {
        return driver.findElement(successfulMessage).isDisplayed();
    }

}
