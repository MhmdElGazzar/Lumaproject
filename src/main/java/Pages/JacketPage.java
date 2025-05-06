package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JacketPage {
    private WebDriver driver;

    public JacketPage(WebDriver driver)
    {
        this.driver=driver;
    }

    private By FirstProductInList=By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[2]/div");
    private By nameOfproduct=By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[2]/div/div/strong/a");
    private By AddtocartBtn=By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[2]/div/div/div[4]/div/div[1]/form/button");


    public void hoveronProduct()
    {
        Waits.waitForElementPresent(driver,FirstProductInList);
        Waits.HoverOnProduct(driver,FirstProductInList);
    }


    public ProductPage Add_to_cart(){
        Waits.clickOnElement(driver,AddtocartBtn);
        return new ProductPage(driver);
    }
    public String GetNameOfProduct()
    {
        Waits.waitForElementPresent(driver,nameOfproduct);
        String name= driver.findElement(nameOfproduct).getText();
        System.out.println(name);
        return name;
    }
}
