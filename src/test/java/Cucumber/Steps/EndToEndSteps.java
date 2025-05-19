package Cucumber.Steps;

import Pages.*;
import Utilities.DataUtil;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.time.Duration;

import static Cucumber.Steps.Hooks.driver;


public class EndToEndSteps {

    HomePage homePage= new HomePage(driver);;
    LoginPage loginPage;
    SearchPage searchPage;
    WatchListPage watchListPage;
    ProductPage productPage;
    JacketPage jacketPage;
    CartPage cartPage;
    CheckOutInfo checkOutInfo;

    String itemForSearch = "Watch";
    String nameOfProduct;
    String ProductName;
    int PriceOfProduct;
    String numofQty = "5";
    int NumOfProductBeforeAddToCart;
    float Price;

    @Given("I am on the Magento homepage")
    public void i_am_on_the_homepage() {
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        homePage = new HomePage(driver);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage = homePage.clickonLoginBtn();
    }

    @When("I enter a valid email and password")
    public void i_enter_valid_credentials() {
        loginPage.LoginBySpecificAccount(
                DataUtil.getJsonData("TestData", "LoginCred", "email"),
                DataUtil.getJsonData("TestData", "LoginCred", "password")
        );
    }

    @When("I click on the sign-in button")
    public void i_click_on_the_sign_in_button() {
        homePage = new HomePage(driver);
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        Assert.assertEquals(homePage.GetHeaderAfterLogin(), "Welcome, " +
                (DataUtil.getJsonData("TestData", "LoginCred", "name")) + " " +
                (DataUtil.getJsonData("TestData", "LoginCred", "Lname")) + "!");
    }

    @When("I search for {string}")
    public void i_search_for_item(String item) {
        searchPage = homePage.SearchForItem(item);
        searchPage.checkRelatedCharisDisplay();
    }

    @Then("I should see relevant search results")
    public void i_should_see_relevant_search_results() {
        Assert.assertTrue(searchPage.SearchResultMessage().contains(itemForSearch), "not Found");
    }

    @When("I hover on a product and add it to the watchlist")
    public void i_hover_on_a_product_and_add_to_watchlist() {
        searchPage.hoveronProduct();
        nameOfProduct = searchPage.GetNameOfProduct();
        watchListPage = searchPage.add_to_watchList_Valid();
    }

    @Then("the product should be added to my watchlist successfully")
    public void the_product_should_be_added_to_watchlist_successfully() {
        Assert.assertTrue(watchListPage.Getheader().contains("My Wish"));
        Assert.assertTrue(watchListPage.SccessMessage().contains("Didi Sport Watch has been added to your Wish List"));
        Assert.assertTrue(watchListPage.isProductInWatchlist(nameOfProduct), "Product not found in wishlist");
    }

    @When("I select an item from the category and add it to the cart")
    public void i_select_item_from_category_and_add_to_cart() {
        jacketPage = homePage.selectONProductFromCategory();
        ProductName = jacketPage.GetNameOfProduct();
        jacketPage.hoveronProduct();
        productPage = jacketPage.Add_to_cart();
        NumOfProductBeforeAddToCart = productPage.getNumOfQtyOnIconCart();
        PriceOfProduct = productPage.GetSecondProductPrice();
        productPage.setSizeOfSecondProduct();
        productPage.setColorOfSecondProduct();
        productPage.setQtyInput(numofQty);
        productPage.AddItemToCart();
        productPage.shechClickableMessageShopingCart();
    }

    @Then("I should see a success message for adding to cart")
    public void i_should_see_success_message_for_adding_to_cart() {
        Assert.assertTrue(productPage.messageAlert().contains("You added Juno"));
    }

    @When("I open the cart")
    public void i_open_the_cart() {
        productPage.IconCartIsVisability();
        productPage.IconCart();
    }

    @Then("the cart icon quantity should reflect the added products correctly")
    public void the_cart_icon_quantity_should_reflect_added_products() {
        int numberOfQty = basePage.convertStringToInt(numofQty);
        Assert.assertEquals(productPage.getNumOfQtyOnIconCart(), numberOfQty + NumOfProductBeforeAddToCart);
    }

    @When("I go to the cart page")
    public void i_go_to_cart_page() {
        cartPage = productPage.ClickOnShopingCart();
    }

    @Then("the total price should match the sum of the product prices")
    public void the_total_price_should_match_sum_of_products() {
        Price = cartPage.GetOrderTotalPriceNumber();
        String numOfText = cartPage.TotalPriceInCart();
        float PriceofProducts = basePage.convertPriceToFloat(numOfText);
        float orderTotalPrice = cartPage.GetSubTotalPriceNumberInSummary();
        Assert.assertEquals(PriceofProducts, orderTotalPrice);
    }

    @When("I remove an item from the cart")
    public void i_remove_item_from_cart() {
        float SubPriceBeforeRemoveInSummary = cartPage.GetSubTotalPriceNumberInSummary();
        float priceOfFirstElemnt = cartPage.GetPriceOfFirstElement();
        cartPage.ClickonCheckOutWithMultiple();
        cartPage.ClickonRemoveItemLink();
        cartPage.ClickonBackToCartPageBtn();
    }

    @Then("the subtotal should update correctly after removal")
    public void subtotal_should_update_correctly_after_removal() {
        float SubPriceAfterRem = basePage.convertPriceToFloat(cartPage.TotalPriceInCart());
        float SubPriceAfterRemoveInSummary = cartPage.GetSubTotalPriceNumberInSummary();
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        checkOutInfo = cartPage.clickONCheckOutBtn();
        if (checkOutInfo.isFormDisplayed()) {
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
        } else {
            checkOutInfo.SetFixedRate();
            checkOutInfo.ClickOnDropDownSummaryPrice();
            checkOutInfo.ClickOnBtnNext();
        }
        checkOutInfo.ClickonCheckOutRatio();
        if (checkOutInfo.isAddressRatioDisplay()) {
            checkOutInfo.ClickonCheckOutRatio();
            checkOutInfo.ClickonCheckOutBtn();
        } else {
            checkOutInfo.ClickonCheckOutBtn();
        }
        checkOutInfo.continueShop();
    }

    @Then("I should be able to complete the checkout process successfully")
    public void checkout_process_completed_successfully() {
        Assert.assertEquals(checkOutInfo.GetTitle(), "Thank you for your purchase!");
    }

    @When("I add a review and a rating to a product")
    public void i_add_review_and_rating() {
        jacketPage = homePage.selectONProductFromCategory();
        jacketPage.hoveronProduct();
        productPage = jacketPage.Add_to_cart();
        productPage.clickOnReviewsToggle();
        productPage.Reviews();
        productPage.give4StarRate();
        productPage.writeSummary();
        productPage.writeReview();
        productPage.clickSubmit();
    }

    @Then("I should see a success message for the review submission")
    public void success_message_for_review_submission() {
        Assert.assertTrue(productPage.messageAlert().contains("You submitted your review for moderation"));
    }

    @When("I log out from the account")
    public void i_log_out() {
        homePage.ClickOnDropDown();
        homePage.LogOut();
    }

    @Then("I should see the {string} button again")
    public void i_should_see_sign_in_button(String buttonText) {
        Assert.assertTrue(homePage.GetTextLogininbtn().contains(buttonText));
    }
}
