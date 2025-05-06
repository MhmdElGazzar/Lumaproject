package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver)
    {
        this.driver=driver;
    }

    private By Product_name= By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[1]/h1/span");
    private By Product_price=By.xpath("//*[@id=\"product-price-1556\"]/span");
    private By Product2_price=By.xpath("//*[@id=\"product-price-1380\"]/span");
    private By sizeOfProduct=By.xpath("//*[@id=\"option-label-size-143-item-168\"]");
    private By sizeOfProduct2=By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[1]/div[1]/div[3]");
    private By colorOfProduct=By.xpath("//*[@id=\"option-label-color-93-item-56\"]");
    private By colorOfProduct2=By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[2]/div[1]/div[1]");
    private By QtyInput=By.xpath("//*[@id=\"qty\"]");
    private By AddToCartBtn=By.xpath("//*[@id=\"product-addtocart-button\"]/span");
    private By SuccessMessage=By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div");
    private By numOfQtyinCartBtn=By.xpath("/html/body/div[2]/header/div[2]/div[1]/a/span[2]/span[1]");
    private By ShoppingCartLink=By.linkText("shopping cart");

    //search
    private By Search_input=By.id("search");
    private By Search_icon=By.xpath("//*[@id=\"search_mini_form\"]/div[2]/button");

    //Review
    private By ReviewBtn=By.xpath("//*[@id=\"tab-label-reviews-title\"]");
    private By Rating=By.xpath("//*[@id=\"Rating_4_label\"]");
    private By summary=By.xpath("//*[@id=\"summary_field\"]");
    private By reviewinput=By.xpath("//*[@id=\"review_field\"]");
    private By submitReview=By.xpath("//*[@id=\"review-form\"]/div/div/button/span");

    private By summaryTxtField=By.id("summary_field");
    private By reviewTxtField=By.id("review_field");
    private By submitButton=By.className("submit");
    private By nickname=By.xpath("//*[@id=\"nickname_field\"]");
    //methods
/*
    public void Make_review(String summaryReview,String ReviewInput)
    {
        Waits.clickOnElement(driver,ReviewBtn);
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Waits.waitForElementPresent(driver,Rating);
        Waits.clickOnElement(driver,Rating);
        Waits.sendData(driver,summary,summaryReview);
        Waits.sendData(driver,reviewinput,ReviewInput);
        Waits.clickOnElement(driver,submitReview);
    }
*/
    public void clickOnReviewsToggle(){driver.findElement(ReviewBtn).click();}

    public void give4StarRate(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement fourStar= driver.findElement(By.id("Rating_4"));
        js.executeScript("arguments[0].click();", fourStar);
    }
    public void writename(){driver.findElement(nickname).sendKeys("nour");}

    public void writeSummary(){driver.findElement(summaryTxtField).sendKeys("nice Jacket");}

    public void writeReview(){driver.findElement(reviewTxtField)
            .sendKeys("nice Jacket to have good value for the money, nice variety of colors");}

    public void clickSubmit(){driver.findElement(submitButton).click();}

    public String GetProductName()
    {
        Waits.waitForElementPresent(driver,Product_name);
        return driver.findElement(Product_name).getText();
    }
    public int GetProductPrice()
    {
        Waits.waitForElementPresent(driver,Product_price);
        String price=driver.findElement(Product_price).getText();
       int priceofProduct=basePage.convertStringToInt(price);
       return priceofProduct;
    }
    public void setSizeOfProduct()
    {
        Waits.clickOnElement(driver,sizeOfProduct);
    }
    public void setColorOfProduct()
    {
        Waits.clickOnElement(driver,colorOfProduct);
    }
    public void setQtyInput(String number)
    {
        driver.findElement(QtyInput).clear();
        driver.findElement(QtyInput).sendKeys(number);

    }
    public void AddItemToCart()
    {
        Waits.clickOnElement(driver,AddToCartBtn);
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}

    }

    public String messageAlert()
    {
        Waits.waitForElementPresent(driver,SuccessMessage);
        return driver.findElement(SuccessMessage).getText();
    }

    public void IconCartIsVisability()
    {
        Waits.waitForElementPresent(driver,numOfQtyinCartBtn);
    }
    public int getNumOfQtyOnIconCart()
    {
        Waits.waitForElementPresent(driver,numOfQtyinCartBtn);
        String num=driver.findElement(numOfQtyinCartBtn).getText();
        int number=basePage.convertStringToInt(num);
        return number;
    }

    public CartPage ClickOnShopingCart()
    {
        Waits.clickOnElement(driver,ShoppingCartLink);

        return new CartPage(driver);
    }

    public SearchPage SearchForItem(String Item)
    {
        Waits.sendData(driver,Search_input,Item);
        Waits.clickOnElement(driver,Search_icon);
        return new SearchPage(driver);
    }

    public float GetSecondProductPrice()
    {
        Waits.waitForElementPresent(driver,Product2_price);
        String price=driver.findElement(Product2_price).getText();
        float priceofProduct=basePage.convertPriceToFloat(price);
        return priceofProduct;
    }
    public void setColorOfSecondProduct()
    {
        Waits.clickOnElement(driver,colorOfProduct2);
    }
    public void setSizeOfSecondProduct()
    {
        Waits.clickOnElement(driver,sizeOfProduct2);
    }
}
