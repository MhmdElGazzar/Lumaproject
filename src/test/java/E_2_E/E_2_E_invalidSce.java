
package E_2_E;

import Base.TestBase;
import Pages.*;
import Utilities.DataUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
public class E_2_E_invalidSce extends TestBase {

    HomePage homePage;
    ProductPage productPage;
    String numofQty="5";
    String NUMofQty="3";
    CartPage cartPage;
    CheckOutInfo checkOutInfo;
    LoginPage loginPage;
    String itemForSearch="Watch";
    SearchPage searchPage;
    AccountPage accountPage;
    EditAccount editAccount;

    @Test
    public void signupWithA_RegisteredAccount()
    {
        homePage = new HomePage(driver);
        SignUp signUp=homePage.clickonSignUpBtn();
        Assert.assertEquals(signUp.Getheader(),"Create New Customer Account");
        signUp.SetFristName(DataUtil.getJsonData("TestData","LoginCred","name"));
        signUp.SetLastName(DataUtil.getJsonData("TestData","LoginCred","Lname"));
        signUp.Setemail(DataUtil.getJsonData("TestData","LoginCred","email"));
        signUp.SetPasswoed(DataUtil.getJsonData("TestData","LoginCred","password"));
        signUp.SetConfirmPasswoed(DataUtil.getJsonData("TestData","LoginCred","password"));
        signUp.ClickonSubmit();
        Assert.assertTrue(signUp.ErrorMessage().contains("There is already an account with this email addres"));
    }
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
    @Test
    public void checkOut_Without_Login() {
        homePage = new HomePage(driver);
        productPage = homePage.ClickOnProduct();
        productPage.setSizeOfProduct();
        productPage.setColorOfProduct();
        productPage.setQtyInput(NUMofQty);
        productPage.AddItemToCart();
        productPage.IconCartIsVisability();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        productPage.IconCart();
        int numberOfQty= basePage.convertStringToInt(NUMofQty);
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
    @Test
    public void VerifyLoginWithOldPassword() {

        homePage = new HomePage(driver);
        loginPage = homePage.clickonLoginBtn();
        homePage = loginPage.LoginBySpecificAccount
                (DataUtil.getJsonData("TestData", "Account2", "email"),
                        DataUtil.getJsonData("TestData", "Account2", "OldPassword"));

        Assert.assertTrue(loginPage.ErrorMessage().contains("The account sign-in was incorrect or your account is disabled temporarily"));


    }
    @Test
    public void VerifyWriteNewPasswordisCurrentPassword()
    {
        homePage = new HomePage(driver);
        loginPage = homePage.clickonLoginBtn();
        homePage = loginPage.LoginBySpecificAccount
                (DataUtil.getJsonData("TestData", "OldAccount", "email"),
                        DataUtil.getJsonData("TestData", "OldAccount", "password"));

        homePage.ClickOnDropDown();
        accountPage = homePage.ClickONAccountBtn();
        editAccount = accountPage.ClickONChangePassword();
        Assert.assertEquals(editAccount.GetHeader(), "Edit Account Information");

        editAccount.SetCurrentPassword(DataUtil.getJsonData("TestData", "OldAccount", "password"));
        editAccount.SetNewPassword(DataUtil.getJsonData("TestData", "OldAccount", "password"));
        editAccount.SetConfirmPassword(DataUtil.getJsonData("TestData", "OldAccount", "password"));
        loginPage = editAccount.ClickONSave();
        Assert.assertEquals(loginPage.Getheader(), "Customer Login");
        Assert.assertTrue(loginPage.Getheader().contains("The new password is the old password, you can change the new password"));


    }
    @Test
    public void TestLogin_InvalidEmail() {
        homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickonLoginBtn();
        Assert.assertEquals(loginPage.Getheader(), "Customer Login");

        loginPage.EnterEmail("wrongemail@example.com")
                .Enterpassword(DataUtil.getJsonData("TestData", "LoginCred", "password"))
                .click_on_SignIn_btn();

        Assert.assertTrue(loginPage.ErrorMessage().contains("The account sign-in was incorrect or your account is disabled temporarily"));
    }
    @Test
    public void TestLogin_InvalidPassword() {
        homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickonLoginBtn();
        Assert.assertEquals(loginPage.Getheader(), "Customer Login");

        loginPage.EnterEmail(DataUtil.getJsonData("TestData", "LoginCred", "email"))
                .Enterpassword("wrongpassword")
                .click_on_SignIn_btn();

        Assert.assertTrue(loginPage.ErrorMessage().contains("The account sign-in was incorrect or your account is disabled temporarily"));
    }
    @Test
    public void TestLogin_EmptyEmail() {
        homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickonLoginBtn();
        Assert.assertEquals(loginPage.Getheader(), "Customer Login");

        loginPage.EnterEmail("")
                .Enterpassword(DataUtil.getJsonData("TestData", "LoginCred", "password"))
                .click_on_SignIn_btn();

        Assert.assertTrue(loginPage.isEmailRequiredMessageDisplayed());
    }

    @Test
    public void TestLogin_EmptyPassword() {
        homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickonLoginBtn();
        Assert.assertEquals(loginPage.Getheader(), "Customer Login");

        loginPage.EnterEmail(DataUtil.getJsonData("TestData", "LoginCred", "email"))
                .Enterpassword("")
                .click_on_SignIn_btn();

        Assert.assertTrue(loginPage.isPasswordRequiredMessageDisplayed());
    }
    @Test
    public void TestLogin_EmptyEmailAndPassword() {
        homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickonLoginBtn();
        Assert.assertEquals(loginPage.Getheader(), "Customer Login");

        loginPage.EnterEmail("")
                .Enterpassword("")
                .click_on_SignIn_btn();

        Assert.assertTrue(loginPage.isEmailRequiredMessageDisplayed());
        Assert.assertTrue(loginPage.isPasswordRequiredMessageDisplayed());
    }








}
