package NegativeScenario;

import Base.TestBase;
import Pages.HomePage;
import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartWithoutLogin extends TestBase {
    HomePage homePage;
    ProductPage productPage;
    String numofQty="5";
    @Test
    public void addToCart_Without_Login()
    {
        homePage=new HomePage(driver);
        productPage=homePage.ClickOnProduct();
        productPage.setSizeOfProduct();
        productPage.setColorOfProduct();
        productPage.setQtyInput(numofQty);
        productPage.AddItemToCart();

        Assert.assertTrue(productPage.messageAlert().contains("You must login or register"));


    }
}
