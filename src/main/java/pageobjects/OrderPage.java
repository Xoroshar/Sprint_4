package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrderPage {
    private final WebDriver driver;

    // кнопка Далее
    private final By nextButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public void fillOrderFields(String name, String surname, String address, String metro, String telephone) {
        String[] inputs = {name, surname, address, metro, telephone};
        for (int i = 1; i < 6; i++) {
            String locator = String.format("//*[@id=\"root\"]/div/div[2]/div[2]/div[%s]/input", i);
            if (i == 4) {
                locator = "//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div/input";
                driver.findElement(By.xpath(locator)).click();
                driver.findElement(By.xpath(locator)).sendKeys(inputs[i - 1]);
                String metroOption = String.format(".//div[@class='select-search__select']//*[text()='%s']", metro);
                driver.findElement(By.xpath(metroOption)).click();
                continue;
            }
            driver.findElement(By.xpath(locator)).sendKeys(inputs[i - 1]);

        }

        driver.findElement(nextButton).click();
    }

}
