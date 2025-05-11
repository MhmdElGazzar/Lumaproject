package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    WebDriver driver;
    SearchPage (WebDriver driver)
    {
        this.driver=driver;
    }

    private By SearchResultLocator= By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span");
    private By FirstProductInList=By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[2]/ol/li[1]");
    private By AddToWatchList=By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[2]/ol/li[1]/div/div/div[3]/div/div[2]/a[1]");
    private By nameOfproduct=By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[2]/ol/li[1]/div/div/strong/a");
    private By AddtocartBtn=By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[2]/ol/li[1]/div/div/div[3]/div/div[1]/form/button/span");
    private By RelatedSearchTerms=By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/dl/dt");

    //methods
    public String SearchResultMessage() {
        Waits.waitForElementPresent(driver,SearchResultLocator);
        return driver.findElement(SearchResultLocator).getText();
    }

    public void hoveronProduct()
    {
        Waits.waitForElementPresent(driver,FirstProductInList);
        Waits.HoverOnProduct(driver,FirstProductInList);
    }


    public ProductPage Add_to_cart(){
        Waits.clickOnElement(driver,AddtocartBtn);
        return new ProductPage(driver);
    }
    public LoginPage add_to_watchList()
    {
        Waits.clickOnElement(driver,AddToWatchList);
        return new LoginPage(driver);
    }
    public WatchListPage add_to_watchList_Valid()
    {
        Waits.clickOnElement(driver,AddToWatchList);
        return new WatchListPage(driver);
    }

    public String GetNameOfProduct()
    {
        Waits.waitForElementPresent(driver,nameOfproduct);
        return driver.findElement(nameOfproduct).getText();
    }
    public void checkRelatedCharisDisplay()
    {
        Waits.waitForElementPresent(driver,RelatedSearchTerms);
    }




}
