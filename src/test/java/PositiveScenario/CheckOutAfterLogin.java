package PositiveScenario;

import Base.TestBaseLogin;
import Pages.*;
import Utilities.DataUtil;
import org.openqa.selenium.By;
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
        int NumOfProductBeforeAddToCart=productPage.getNumOfQtyOnIconCart();
        productPage.setSizeOfProduct();
        productPage.setColorOfProduct();
        productPage.setQtyInput(numofQty);
        productPage.AddItemToCart();
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}
    //verify num of quntity of product you need to add in cart added in number of quantity in icon cart
        int numberOfQty= basePage.convertStringToInt(numofQty);
        Assert.assertEquals(productPage.getNumOfQtyOnIconCart(),numberOfQty+NumOfProductBeforeAddToCart);

        cartPage=productPage.ClickOnShopingCart();

        //verify compare total price in cart table by total price in summary

        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}
        String numOfText=cartPage.TotalPriceInCart();   //sub total Price in cart table
        float PriceofProducts=  basePage.convertPriceToFloat(numOfText); //float sub total

        float orderTotalPrice=PriceofProducts+cartPage.GetDiscount()+cartPage.GetRateFixed();

        float Price=cartPage.GetOrderTotalPriceNumber();        //totalinsummary

        Assert.assertEquals(orderTotalPrice,Price);

        checkOutInfo= cartPage.clickONCheckOutBtn();

        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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


        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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


        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(checkOutInfo.GetTitle(),"Thank you for your purchase!");












    }



}
