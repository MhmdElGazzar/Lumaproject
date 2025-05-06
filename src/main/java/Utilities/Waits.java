package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Waits {

    //TODO:: wait for to be element present


    public static void waitForElementPresent(WebDriver driver, By locator){
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        //TODO:: clicking ON Element
        public  static void clickOnElement(WebDriver driver, By locator){
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
        }
    public  static void clickOnElementPresece(WebDriver driver, By locator){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

        //TODO:: Sending Data to Element
        public  static void sendData(WebDriver driver, By locator,String text){
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            driver.findElement(locator).sendKeys(text);
        }

        //TODO:: Wait for alert is present
        public static void acceptAlert(WebDriver driver ){
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.alertIsPresent());

            driver.switchTo().alert().accept();
        }

    public  static void HoverOnProduct(WebDriver driver, By locator){
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static List<String> getProductNames(WebDriver driver,By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        List<WebElement> nameElements = driver.findElements(locator);
        List<String> productNames = new ArrayList<>();

        for (WebElement element : nameElements) {
            productNames.add(element.getText().trim());
        }

        return productNames;
    }
    public static String getTotalPriceOfSelectedProducts(WebDriver driver,By priceLocator) {
        float totalPrice = 0;

        try {
            List<WebElement> priceElements = driver.findElements(priceLocator);

            for (WebElement priceElement : priceElements) {
                String fullPriceText = priceElement.getText().trim();
                System.out.println("Found price text: " + fullPriceText);
                totalPrice += Float.parseFloat(fullPriceText.replace("$", "").replace(",", ""));
            }


            LogsUtils.info("Total Price of Selected Products: $" + totalPrice);
            return String.valueOf(totalPrice);

        } catch (Exception e) {
            LogsUtils.error("Error calculating total price: " + e.getMessage());
            return "0";
        }
    }

}


