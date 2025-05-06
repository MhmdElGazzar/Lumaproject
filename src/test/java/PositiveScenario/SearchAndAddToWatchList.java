package PositiveScenario;

import Base.TestBaseLogin;
import Pages.HomePage;
import Pages.SearchPage;
import Pages.WatchListPage;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchAndAddToWatchList extends TestBaseLogin {
    HomePage homePage;
    SearchPage searchPage;
    String itemForSearch="Watch";
    WatchListPage watchListPage;
    String nameOfProduct;
    @Test
    public void TestSearch()
    {
        homePage=new HomePage(driver);
        searchPage=homePage.SearchForItem(itemForSearch);
        Assert.assertTrue(searchPage.SearchResultMessage().contains(itemForSearch),"not Found");

        searchPage.hoveronProduct();
        nameOfProduct=searchPage.GetNameOfProduct();
        watchListPage =searchPage.add_to_watchList_Valid();

        Assert.assertTrue(watchListPage.Getheader().contains("My Wish"));

        Assert.assertTrue(watchListPage.SccessMessage().contains("Didi Sport Watch has been added to your Wish List"));
        System.out.println(nameOfProduct);

      Assert.assertTrue(watchListPage.isProductInWatchlist(nameOfProduct), "Product not found in wishlist");

    }
}
