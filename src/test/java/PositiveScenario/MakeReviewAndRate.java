package PositiveScenario;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductPage;
import Utilities.DataUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class MakeReviewAndRate extends TestBase {
    HomePage homePage;
    LoginPage loginPage;
    ProductPage productPage;
    @Test
    public void verifyMakeReviewWithLogin()
    {
        homePage = new HomePage(driver);
        loginPage = homePage.clickonLoginBtn();
        homePage = loginPage.LoginBySpecificAccount
                (DataUtil.getJsonData("TestData", "LoginCred", "email"),
                        DataUtil.getJsonData("TestData", "LoginCred", "password"));

        Assert.assertEquals(homePage.GetHeaderAfterLogin(), "Welcome, " +
                (DataUtil.getJsonData("TestData", "LoginCred", "name")) + " " +
                (DataUtil.getJsonData("TestData", "LoginCred", "Lname")) + "!");

        productPage = homePage.ClickOnProduct();
        productPage.clickOnReviewsToggle();

        productPage.give4StarRate();
        productPage.writeSummary();
        productPage.writeReview();
        productPage.clickSubmit();

        Assert.assertTrue(productPage.messageAlert().contains("You submitted your review for moderation"));

    }
    @Test
    public void verifyMakeReviewWithoutLogin()
    {
        homePage = new HomePage(driver);
        productPage = homePage.ClickOnProduct();
        productPage.clickOnReviewsToggle();

        productPage.give4StarRate();
        productPage.writename();
        productPage.writeSummary();
        productPage.writeReview();
        productPage.clickSubmit();

        Assert.assertFalse(productPage.messageAlert().contains("You submitted your review for moderation"),"you dhould Login to make review");

    }
}
