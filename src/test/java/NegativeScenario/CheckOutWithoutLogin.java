package NegativeScenario;

import Base.TestBase;
import Pages.*;

import Utilities.DataUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckOutWithoutLogin extends TestBase {
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    CheckOutInfo checkOutInfo;

    String numofQty="3";
    @Test
    public void checkOut_Without_Login() {
        homePage = new HomePage(driver);
        productPage = homePage.ClickOnProduct();
        productPage.setSizeOfProduct();
        productPage.setColorOfProduct();
        productPage.setQtyInput(numofQty);
        productPage.AddItemToCart();
        productPage.IconCartIsVisability();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        productPage.IconCart();
        int numberOfQty=basePage.convertStringToInt(numofQty);
        Assert.assertEquals(productPage.getNumOfQtyOnIconCart(),numberOfQty);

        cartPage=productPage.ClickOnShopingCart();
        float Price=cartPage.GetOrderTotalPriceNumberWithoutLogin();
        String numOfText=cartPage.TotalPriceInCart();
        float PriceofProducts=  basePage.convertPriceToFloat(numOfText);
        Assert.assertEquals(PriceofProducts,Price);

        checkOutInfo= cartPage.clickONCheckOutBtn();

        checkOutInfo.setEmail(DataUtil.getJsonData("TestData","CartInfo","email"));

        checkOutInfo.setFname(DataUtil.getJsonData("TestData","CartInfo","firstname"));
        checkOutInfo.setLname(DataUtil.getJsonData("TestData","CartInfo","lastname"));
        checkOutInfo.setCompany(DataUtil.getJsonData("TestData","CartInfo","Company"));
        checkOutInfo.setStreet(DataUtil.getJsonData("TestData","CartInfo","street"));
        checkOutInfo.SetCountry();
        checkOutInfo.SetPhone(DataUtil.getJsonData("TestData","CartInfo","phone"));
        checkOutInfo.SetCity(DataUtil.getJsonData("TestData","CartInfo","city"));
        checkOutInfo.SetZip(DataUtil.getJsonData("TestData","CartInfo","zipcode"));
        checkOutInfo.SetTableRate();
        checkOutInfo.SetTax();
        checkOutInfo.ClickOnBtnNext();
        checkOutInfo.Editbutton();
        checkOutInfo.ClickonCheckOutRatio();
        checkOutInfo.ClickonCheckOutBtn();

        checkOutInfo.continueShop();

        Assert.assertEquals(checkOutInfo.GetTitle(),"You can login at First or enter valid email");











    }

}
