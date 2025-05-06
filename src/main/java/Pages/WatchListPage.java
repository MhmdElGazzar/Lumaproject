package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WatchListPage {
    WebDriver driver;
    WatchListPage(WebDriver driver)
    {
        this.driver=driver;
    }

    private By headerTitle=By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span");
    private By AlertError=By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div");
    private By WatchListNum=By.xpath("//*[@id=\"wishlist-view-form\"]/div[1]/ol/li");
    private By nameOfProduct = By.xpath("//*[@id=\"wishlist-view-form\"]/div[1]/ol/li/div/strong/a");

    public String Getheader() {
        Waits.waitForElementPresent(driver,headerTitle);
        return driver.findElement(headerTitle).getText();
    }
    public String SccessMessage()
    {
        Waits.waitForElementPresent(driver,AlertError);
        return driver.findElement(AlertError).getText();
    }

    public boolean isProductInWatchlist(String productName) {
        List<String> productNames = Waits.getProductNames(driver, nameOfProduct);
        return productNames.contains(productName);
    }
}
