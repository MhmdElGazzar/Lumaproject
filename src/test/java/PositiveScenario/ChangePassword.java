package PositiveScenario;

import Base.TestBase;
import Base.TestBaseLogin;
import Pages.AccountPage;
import Pages.EditAccount;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.DataUtil;


public class ChangePassword extends TestBase {
   HomePage homePage;
   AccountPage accountPage;
   EditAccount editAccount;
   LoginPage loginPage;

    @Test(description = "VerfiyChangePassword")
    public void changePassword() {

        homePage = new HomePage(driver);
        loginPage = homePage.clickonLoginBtn();
        homePage = loginPage.LoginBySpecificAccount
                (DataUtil.getJsonData("TestData", "Account2", "email"),
                        DataUtil.getJsonData("TestData", "Account2", "password"));

        Assert.assertEquals(homePage.GetHeaderAfterLogin(), "Welcome, " +
                (DataUtil.getJsonData("TestData", "Account2", "name")) + " " +
                (DataUtil.getJsonData("TestData", "Account2", "Lname")) + "!");


        homePage.ClickOnDropDown();
        accountPage = homePage.ClickONAccountBtn();
        editAccount = accountPage.ClickONChangePassword();
        Assert.assertEquals(editAccount.GetHeader(), "Edit Account Information");

        editAccount.SetCurrentPassword(DataUtil.getJsonData("TestData", "Account2", "password"));
        editAccount.SetNewPassword(DataUtil.getJsonData("TestData", "Account2", "newPassword"));
        editAccount.SetConfirmPassword(DataUtil.getJsonData("TestData", "Account2", "newPassword"));
        loginPage = editAccount.ClickONSave();
        Assert.assertEquals(loginPage.Getheader(), "Customer Login");


    }
    @Test(dependsOnMethods = "changePassword",priority = 1)
    public void VerifyLoginWithOldPassword() {

        homePage = new HomePage(driver);
        loginPage = homePage.clickonLoginBtn();
        homePage = loginPage.LoginBySpecificAccount
                (DataUtil.getJsonData("TestData", "Account2", "email"),
                        DataUtil.getJsonData("TestData", "Account2", "password"));

        Assert.assertTrue(loginPage.ErrorMessage().contains("The account sign-in was incorrect or your account is disabled temporarily"));


    }
    @Test(dependsOnMethods = "changePassword",priority = 2)
    public void VerifyLoginWithNewPassword()
    {
        homePage = new HomePage(driver);
        loginPage = homePage.clickonLoginBtn();
        homePage = loginPage.LoginBySpecificAccount
                (DataUtil.getJsonData("TestData", "Account2", "email"),
                        DataUtil.getJsonData("TestData", "Account2", "newPassword"));
        Assert.assertEquals(homePage.GetHeaderAfterLogin(), "Welcome, " +
                (DataUtil.getJsonData("TestData", "Account2", "name")) + " " +
                (DataUtil.getJsonData("TestData", "Account2", "Lname")) + "!");

    }
    @Test(dependsOnMethods = "changePassword",priority = 2)
    public void VerifyWriteNewPasswordisCurrentPassword()
    {
        homePage = new HomePage(driver);
        loginPage = homePage.clickonLoginBtn();
        homePage = loginPage.LoginBySpecificAccount
                (DataUtil.getJsonData("TestData", "Account2", "email"),
                        DataUtil.getJsonData("TestData", "Account2", "newPassword"));

        Assert.assertEquals(homePage.GetHeaderAfterLogin(), "Welcome, " +
                (DataUtil.getJsonData("TestData", "Account2", "name")) + " " +
                (DataUtil.getJsonData("TestData", "Account2", "Lname")) + "!");


        homePage.ClickOnDropDown();
        accountPage = homePage.ClickONAccountBtn();
        editAccount = accountPage.ClickONChangePassword();
        Assert.assertEquals(editAccount.GetHeader(), "Edit Account Information");

        editAccount.SetCurrentPassword(DataUtil.getJsonData("TestData", "Account2", "newPassword"));
        editAccount.SetNewPassword(DataUtil.getJsonData("TestData", "Account2", "newPassword"));
        editAccount.SetConfirmPassword(DataUtil.getJsonData("TestData", "Account2", "newPassword"));
        loginPage = editAccount.ClickONSave();
        Assert.assertEquals(loginPage.Getheader(), "Customer Login");
        Assert.assertTrue(loginPage.Getheader().contains("The new password is the old password, you can change the new password"));


    }

    }

