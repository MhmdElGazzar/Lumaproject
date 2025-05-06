package PositiveScenario;

import Base.TestBaseLogin;
import Pages.*;
import Utilities.DataUtil;
import Utilities.LogsUtils;
import org.openqa.selenium.devtools.v127.log.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DeleteFromCart extends TestBaseLogin {

    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    CheckOutInfo checkOutInfo;
    SearchPage searchPage;
    String itemForSearch="Watch";

    String numofQty="3";
    @Test
    public void DeleteItemFromCart() {
        homePage = new HomePage(driver);
        productPage = homePage.ClickOnProduct();
        int NumOfProductBeforeAddToCart=productPage.getNumOfQtyOnIconCart();
        productPage.setSizeOfProduct();
        productPage.setColorOfProduct();
        productPage.setQtyInput(numofQty);
        productPage.AddItemToCart();
        productPage.IconCartIsVisability();

        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}

        int numberOfQty= basePage.convertStringToInt(numofQty);

        Assert.assertEquals(productPage.getNumOfQtyOnIconCart(),numberOfQty+NumOfProductBeforeAddToCart);


        searchPage=productPage.SearchForItem(itemForSearch);
        Assert.assertTrue(searchPage.SearchResultMessage().contains(itemForSearch),"not Found");

        searchPage.hoveronProduct();
        productPage =searchPage.Add_to_cart();

        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        cartPage=productPage.ClickOnShopingCart();

        basePage.SleepThreed();
        float Price=cartPage.GetOrderTotalPriceNumber();
        String numOfText=cartPage.TotalPriceInCart();
        float PriceofProducts=  basePage.convertPriceToFloat(numOfText);


      //  float orderTotalPrice=Price-cartPage.GetDiscount()+cartPage.GetRateFixed();

        float orderTotalPrice=cartPage.GetSubTotalPriceNumberInSummary();
        Assert.assertEquals(PriceofProducts,orderTotalPrice);

        float numofQuantity =cartPage.GetQuantity();

        String SubPriceBrforeRemove=cartPage.TotalPriceInCart();
       float SubPrice= basePage.convertPriceToFloat(SubPriceBrforeRemove);

        float SubPriceBeforeRemoveInSummary=cartPage.GetSubTotalPriceNumberInSummary();

        LogsUtils.info(cartPage.GetSubTotalPriceNumberInSummary()+" ");

        Assert.assertEquals(SubPrice,SubPriceBeforeRemoveInSummary);


        cartPage.ClickonCheckOutWithMultiple();
        cartPage.ClickonRemoveItemLink();
        cartPage.ClickonBackToCartPageBtn();

        float numofQuantityAfterRemove =cartPage.GetQuantity();

        String SubPriceAfterRemove=cartPage.TotalPriceInCart();
        float SubPriceAfterRem= basePage.convertPriceToFloat(SubPriceAfterRemove);

        float SubPriceAFterRemoveInSummary=cartPage.GetSubTotalPriceNumberInSummary();


        Assert.assertEquals(SubPriceAfterRem,SubPriceAFterRemoveInSummary ,"SubTotal in cart table not update");
        Assert.assertEquals(numofQuantity,numofQuantityAfterRemove-1);






    }


}
