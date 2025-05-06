package PositiveScenario;

import Base.TestBaseLogin;
import Pages.HomePage;
import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Add_To_Cart extends TestBaseLogin {
    HomePage homePage;
    ProductPage productPage;
    String ProductName;
    int PriceOfProduct;
    String numofQty="5";

    @Test
    public void addToCart()
    {
        homePage=new HomePage(driver);
        productPage=homePage.ClickOnProduct();
        ProductName= productPage.GetProductName();
        PriceOfProduct=productPage.GetProductPrice();
        productPage.setSizeOfProduct();
        productPage.setColorOfProduct();
        productPage.setQtyInput(numofQty);
        productPage.AddItemToCart();

        Assert.assertTrue(productPage.messageAlert().contains("You added Radiant Tee "));







    }
}
