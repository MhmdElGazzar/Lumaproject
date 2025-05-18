package PositiveScenario;

import Base.TestBaseLogin;
import Pages.*;
import Utilities.DataUtil;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckOutAfterLogin extends TestBaseLogin {
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    CheckOutInfo checkOutInfo;

    String numofQty="3";
    @Test
    public void checkOut_After_Login() {
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
        int numberOfQty = basePage.convertStringToInt(numofQty);
        int numOfProductAfterAdd = productPage.getNumOfQtyOnIconCart();
        int numExpected = numberOfQty + numOfProductBeforeAddToCart;

        LogsUtils.info("Number of products in cart AFTER adding: " + numOfProductAfterAdd);
        LogsUtils.info("Expected number of products in cart: " + numExpected);
        Assert.assertEquals(numOfProductAfterAdd, numExpected, "The number of products after adding to cart is not as expected.");



        cartPage=productPage.ClickOnShopingCart();

        //verify compare total price in cart table by total price in summary

        String numOfText=cartPage.TotalPriceInCart();   //sub total Price in cart table
        float PriceofProducts=  basePage.convertPriceToFloat(numOfText); //float sub total

        float orderTotalPrice=PriceofProducts+cartPage.GetDiscount()+cartPage.GetRateFixed();

        float Price=cartPage.GetOrderTotalPriceNumber();        //totalinsummary

        Assert.assertEquals(orderTotalPrice,Price);

        checkOutInfo= cartPage.clickONCheckOutBtn();


//verify is found address or not
        //if found ,it is check out
        //else write info and completed information

        boolean isDisplayed = checkOutInfo.isFormDisplayed();

        if(isDisplayed){
            checkOutInfo.setFname(DataUtil.getJsonData("TestData", "CartInfo", "firstname"));
            checkOutInfo.setLname(DataUtil.getJsonData("TestData", "CartInfo", "lastname"));
            checkOutInfo.setCompany(DataUtil.getJsonData("TestData", "CartInfo", "Company"));
            checkOutInfo.setStreet(DataUtil.getJsonData("TestData", "CartInfo", "street"));
            checkOutInfo.SetCountry();
            checkOutInfo.SetPhone(DataUtil.getJsonData("TestData", "CartInfo", "phone"));
            checkOutInfo.SetCity(DataUtil.getJsonData("TestData", "CartInfo", "city"));
            checkOutInfo.SetZip(DataUtil.getJsonData("TestData", "CartInfo", "zipcode"));
            checkOutInfo.SetTableRate();
            checkOutInfo.SetTax();
            checkOutInfo.ClickOnBtnNext();
        }
        else {
            checkOutInfo.ClickOnBtnNext();

        }



        checkOutInfo.ClickonCheckOutRatio();
        boolean isDisplayedAddRatio = checkOutInfo.isAddressRatioDisplay();
        if (isDisplayedAddRatio)
        {
            checkOutInfo.ClickonCheckOutRatio();
            checkOutInfo.ClickonCheckOutBtn();
        }
        else {
            checkOutInfo.ClickonCheckOutBtn();
        }



        checkOutInfo.continueShop();
        Assert.assertEquals(checkOutInfo.GetTitle(),"Thank you for your purchase!");












    }



}
