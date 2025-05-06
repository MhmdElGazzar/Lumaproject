package NegativeScenario;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchAndAddToCardWithoutLogin extends TestBase {
    HomePage homePage;
    SearchPage searchPage;
    LoginPage loginPage;
    String itemForSearch="Watch";

    @Test
    public void TestSearch()
    {
        homePage=new HomePage(driver);
        searchPage=homePage.SearchForItem(itemForSearch);
        Assert.assertTrue(searchPage.SearchResultMessage().contains(itemForSearch),"not Found");

        searchPage.hoveronProduct();
        loginPage =searchPage.add_to_watchList();
        Assert.assertTrue(loginPage.Getheader().contains("Login"));

        Assert.assertTrue(loginPage.ErrorMessage().contains("You must login or register "));

    }
}
