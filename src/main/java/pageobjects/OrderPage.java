package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrderPage {
    private final WebDriver driver;

    // кнопка Далее
    private final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");
    // поле для заполнения имени
    private final By inputNameField = By.xpath(".//input[@placeholder='* Имя']");
    // поле для заполнения фамилии
    private final By inputSurnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // поле для заполнения адреса
    private final By inputAddressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // выпадающий список для выбора станции метро
    private final By inputMetroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    // поле для номера телефона
    private final By inputTelephoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillNameField(String name) {
        driver.findElement(inputNameField).sendKeys(name);
    }

    public void fillSurnameField(String surname) {
        driver.findElement(inputSurnameField).sendKeys(surname);
    }

    public void fillAddressField(String address) {
        driver.findElement(inputAddressField).sendKeys(address);
    }

    public void fillTelephoneField(String telephoneNumber) {
        driver.findElement(inputTelephoneField).sendKeys(telephoneNumber);
    }

    public void selectMetroStation(String stationName) {
        driver.findElement(inputMetroStation).sendKeys(stationName);
        selectMetroStationFromOptions(stationName);
    }

    private void selectMetroStationFromOptions(String stationName) {
        String metroOption = String.format(".//div[@class='select-search__select']//*[text()='%s']", stationName);
        driver.findElement(By.xpath(metroOption)).click();
    }

    public void pressNextButton() {
        driver.findElement(nextButton).click();
    }

    public void fillOrderFields(String name, String surname, String address, String metro, String telephoneNumber) {
        fillNameField(name);
        fillSurnameField(surname);
        fillAddressField(address);
        selectMetroStation(metro);
        fillTelephoneField(telephoneNumber);
        pressNextButton();
    }

}
