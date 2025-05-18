package PositiveScenario;

import Base.TestBaseLogin;
import Pages.*;
import Utilities.LogsUtils;
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

// Get number of products in cart before adding
        int numOfProductBeforeAddToCart = productPage.getNumOfQtyOnIconCart();
        LogsUtils.info("Number of products in cart BEFORE adding: " + numOfProductBeforeAddToCart);

// لو فاضي وخارج -1 عدليه لـ 0
        if (numOfProductBeforeAddToCart == -1) {
            numOfProductBeforeAddToCart = 0;
        }

// اختيارات المنتج
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        productPage.setSizeOfProduct();
        productPage.setColorOfProduct();
        productPage.setQtyInput(numofQty);
        productPage.AddItemToCart();

// تأكد أن الأيقونة ظهرت
        productPage.IconCartIsVisability();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        productPage.IconCart();

// حساب القيم المتوقعة
        int numberOfQty = basePage.convertStringToInt(numofQty); // عدد الكمية اللي كتبتيه مثلا 3
        int numOfProductAfterAdd = productPage.getNumOfQtyOnIconCart(); // اللي حصل فعليًا في الأيقونة
        int numExpected = numberOfQty + numOfProductBeforeAddToCart; // المتوقع = اللي كان موجود + اللي ضفتيه

        LogsUtils.info("Number of products in cart AFTER adding: " + numOfProductAfterAdd);
        LogsUtils.info("Expected number of products in cart: " + numExpected);

// تأكيد النتيجة
        Assert.assertEquals(numOfProductAfterAdd, numExpected, "The number of products after adding to cart is not as expected.");


        searchPage=productPage.SearchForItem(itemForSearch);
        Assert.assertTrue(searchPage.SearchResultMessage().contains(itemForSearch),"not Found");

        searchPage.hoveronProduct();
        productPage =searchPage.Add_to_cart();


        cartPage=productPage.ClickOnShopingCart();


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
        float priceOfFirstElemnt=cartPage.GetPriceOfFirstElement();

        cartPage.ClickonCheckOutWithMultiple();
        cartPage.ClickonRemoveItemLink();
        cartPage.ClickonBackToCartPageBtn();

        float numofQuantityAfterRemove =cartPage.GetQuantity();

        String SubPriceAfterRemove=cartPage.TotalPriceInCart();
        float SubPriceAfterRem= basePage.convertPriceToFloat(SubPriceAfterRemove);

        float SubPriceAFterRemoveInSummary=cartPage.GetSubTotalPriceNumberInSummary();

        float xx=SubPriceBeforeRemoveInSummary-priceOfFirstElemnt;
        Assert.assertEquals(xx,SubPriceAFterRemoveInSummary ,"SubTotal in cart table not update");
        //Assert.assertEquals(numofQuantity,numofQuantityAfterRemove-1);






    }


}
