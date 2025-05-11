package E_2_E;

import Base.TestBaseForEndTOEnd;
import Pages.*;
import Utilities.DataUtil;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
//serve target/allure-results
public class EndToEndValidSce extends TestBaseForEndTOEnd {
    HomePage homePage;
    LoginPage loginPage;
    SearchPage searchPage;
    String itemForSearch = "Watch";
    WatchListPage watchListPage;
    String nameOfProduct;
    ProductPage productPage;
    String ProductName;
    int PriceOfProduct;
    String numofQty = "5";
    JacketPage jacketPage;
    int NumOfProductBeforeAddToCart;
    CartPage cartPage;
    float Price;
    CheckOutInfo checkOutInfo;


    @Test
    public void LoginTest()
    {
        homePage = new HomePage(driver);
        loginPage = homePage.clickonLoginBtn();
        homePage = loginPage.LoginBySpecificAccount
                (DataUtil.getJsonData("TestData", "LoginCred", "email"),
                        DataUtil.getJsonData("TestData", "LoginCred", "password"));

        Assert.assertEquals(homePage.GetHeaderAfterLogin(), "Welcome, " +
                (DataUtil.getJsonData("TestData", "LoginCred", "name")) + " " +
                (DataUtil.getJsonData("TestData", "LoginCred", "Lname")) + "!");

    }
    @Test(dependsOnMethods = "LoginTest")
    public void SearchForProduct()
    {
        homePage=new HomePage(driver);
        searchPage=homePage.SearchForItem(itemForSearch);
        searchPage.checkRelatedCharisDisplay();
        Assert.assertTrue(searchPage.SearchResultMessage().contains(itemForSearch),"not Found");

    }
    @Test(dependsOnMethods = "SearchForProduct")
    public void selectItemFromResultOfSearch_andAddToWatchList()
    {
        searchPage.hoveronProduct();
        nameOfProduct=searchPage.GetNameOfProduct();
        watchListPage =searchPage.add_to_watchList_Valid();

        Assert.assertTrue(watchListPage.Getheader().contains("My Wish"));

        Assert.assertTrue(watchListPage.SccessMessage().contains("Didi Sport Watch has been added to your Wish List"));
        System.out.println(nameOfProduct);

        Assert.assertTrue(watchListPage.isProductInWatchlist(nameOfProduct), "Product not found in wishlist");

    }
    @Test(dependsOnMethods = "selectItemFromResultOfSearch_andAddToWatchList")
    public void selectItemAndAddToCart()
    {
        jacketPage=homePage.selectONProductFromCategory();
        ProductName= jacketPage.GetNameOfProduct();
        System.out.println(ProductName);
        jacketPage.hoveronProduct();
        productPage=jacketPage.Add_to_cart();
        NumOfProductBeforeAddToCart=productPage.getNumOfQtyOnIconCart();
        PriceOfProduct=productPage.GetSecondProductPrice();
        productPage.setSizeOfSecondProduct();
        productPage.setColorOfSecondProduct();
        productPage.setQtyInput(numofQty);
        productPage.AddItemToCart();
        productPage.shechClickableMessageShopingCart();
        Assert.assertTrue(productPage.messageAlert().contains("You added Juno"));



    }

    @Test(dependsOnMethods = "selectItemAndAddToCart")
    public void verifyIncreaceNumberOfProductONCartIcon()
    {
        productPage.IconCartIsVisability();

        productPage.IconCart();

        int numberOfQty= basePage.convertStringToInt(numofQty);

        Assert.assertEquals(productPage.getNumOfQtyOnIconCart(),numberOfQty+NumOfProductBeforeAddToCart);

    }
    @Test(dependsOnMethods = "verifyIncreaceNumberOfProductONCartIcon")
    public void GoToCartAndVerifythatpriceOfProductEqualTotalPrice()
    {
        cartPage=productPage.ClickOnShopingCart();

        Price=cartPage.GetOrderTotalPriceNumber();
        String numOfText=cartPage.TotalPriceInCart();
        float PriceofProducts=  basePage.convertPriceToFloat(numOfText);

        float orderTotalPrice=cartPage.GetSubTotalPriceNumberInSummary();
        Assert.assertEquals(PriceofProducts,orderTotalPrice);

    }
    @Test(dependsOnMethods = "GoToCartAndVerifythatpriceOfProductEqualTotalPrice")
    public void DeleteFromCart()
    {

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

        //Assert.assertEquals(SubPriceAfterRem,SubPriceAFterRemoveInSummary ,"SubTotal in cart table not update");



    }
    @Test(dependsOnMethods ="DeleteFromCart" )
    public void verifyMakeCheckOut()
    {
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
            checkOutInfo.SetFixedRate();
            checkOutInfo.ClickOnDropDownSummaryPrice();
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
    @Test(dependsOnMethods = "verifyMakeCheckOut")
    public void MakeReviewAndRate()
    {
        jacketPage=homePage.selectONProductFromCategory();
        ProductName= jacketPage.GetNameOfProduct();
        System.out.println(ProductName);
        jacketPage.hoveronProduct();
        productPage=jacketPage.Add_to_cart();
        productPage.clickOnReviewsToggle();
        productPage.Reviews();
        productPage.give4StarRate();
        productPage.writeSummary();
        productPage.writeReview();
        productPage.clickSubmit();

        Assert.assertTrue(productPage.messageAlert().contains("You submitted your review for moderation"));

    }
    @Test(dependsOnMethods = "MakeReviewAndRate")
    public void VerifyLogOut()
    {
        homePage.ClickOnDropDown();
        homePage.LogOut();
        Assert.assertTrue(homePage.GetTextLogininbtn().contains("Sign In"));

    }
}

