package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver)
    {
        this.driver=driver;
    }

    private By SubTotalPriceofProduct=By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/span/span/span");
    private By TotalPrice=By.xpath("//*[@id=\"cart-totals\"]/div/table/tbody/tr[4]/td/strong/span");
    private By TotalPriceOfOrderWithoutLogin=By.xpath("//*[@id=\"cart-totals\"]/div/table/tbody/tr[3]/td/strong/span");
    private By CheckOutBtn=By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[2]/div[1]/ul/li[1]/button");
    private By Discount=By.xpath("//*[@id=\"cart-totals\"]/div/table/tbody/tr[2]/td/span/span");
    private By Rate_fixed=By.xpath("//*[@id=\"cart-totals\"]/div/table/tbody/tr[3]/td/span");
    private By CheckOutWithMultipleAdd=By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[2]/div[1]/ul/li[2]/a/span");
    private By RemoveItemLink=By.xpath("//*[@id=\"multiship-addresses-table\"]/tbody/tr[1]/td[4]/a/span");
    private By QuntityOfItemOfIndex1=By.xpath("//*[@id=\"shopping-cart-table\"]/tbody[1]/tr[1]/td[3]/div/div/label/input");
    private By SubTotalInSummary=By.xpath("//*[@id=\"cart-totals\"]/div/table/tbody/tr[1]/td/span");
    private By BackToCartPageBtn=By.xpath("//*[@id=\"checkout_multishipping_form\"]/div[3]/div[2]/a/span");
    //private By SubTotalincart=By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[4]/span/span/span");


    public void ClickonBackToCartPageBtn()
    {
        Waits.clickOnElement(driver,BackToCartPageBtn);
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}

    }

    public float GetQuantity()
    {
        Waits.waitForElementPresent(driver,QuntityOfItemOfIndex1);
        String Qan=driver.findElement(QuntityOfItemOfIndex1).getText();
        float QuantityOfNum=basePage.convertPriceToFloat(Qan);
        return QuantityOfNum;
    }

    public float GetSubTotalPriceNumberInSummary()
    {
        Waits.waitForElementPresent(driver,SubTotalInSummary);
        String Price=driver.findElement(SubTotalInSummary).getText();
        float number= basePage.convertPriceToFloat(Price);
        return number;


    }

    public void ClickonRemoveItemLink()
    {
        Waits.clickOnElement(driver,RemoveItemLink);
    }

   public void ClickonCheckOutWithMultiple()
   {
       Waits.clickOnElement(driver,CheckOutWithMultipleAdd);
   }

    public float GetOrderTotalPriceNumber()
    {
        Waits.waitForElementPresent(driver,TotalPrice);
        String Price=driver.findElement(TotalPrice).getText();
        System.out.println("String of total num ="+Price);
       float num= basePage.convertPriceToFloat(Price);
        System.out.println("float number "+num);
        return num;
    }

    public float GetOrderTotalPriceNumberWithoutLogin()
    {
        Waits.waitForElementPresent(driver,TotalPriceOfOrderWithoutLogin);
        String Price=driver.findElement(TotalPriceOfOrderWithoutLogin).getText();
        Float num= basePage.convertPriceToFloat(Price);
        return num;
    }
    public float GetDiscount()
    {
        Waits.waitForElementPresent(driver,Discount);
        String discount=driver.findElement(Discount).getText();
        float discountOfNum=basePage.convertPriceToFloat(discount);
        return discountOfNum;
    }

    public float GetRateFixed()
    {
        Waits.waitForElementPresent(driver,Rate_fixed);
        String rate=driver.findElement(Rate_fixed).getText();
        System.out.println("string of Fixed"+rate);

        float RateOfNum=basePage.convertPriceToFloat(rate);
        System.out.println("floatof Fixed"+RateOfNum);
        return RateOfNum;
    }


    public String TotalPriceInCart()
    {
        String TotalPrice;
        TotalPrice= Waits.getTotalPriceOfSelectedProducts(driver,SubTotalPriceofProduct);
        return TotalPrice;
    }

    public CheckOutInfo clickONCheckOutBtn()
    {
        Waits.clickOnElement(driver,CheckOutBtn);
        return new CheckOutInfo(driver);
    }

}
