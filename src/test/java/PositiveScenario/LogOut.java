package PositiveScenario;

import Base.TestBase;
import Pages.*;
import Utilities.DataUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogOut extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    String numofQty="3";

    @Test
    public void verifyMakeReviewAndRateAndLogOut() {
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
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        productPage.give4StarRate();
        productPage.writeSummary();
        productPage.writeReview();
        productPage.clickSubmit();

        Assert.assertTrue(productPage.messageAlert().contains("You submitted your review for moderation"));

        homePage.ClickOnDropDown();
        homePage.LogOut();
        Assert.assertTrue(homePage.GetTextLogininbtn().contains("Sign In"));





    }
}
