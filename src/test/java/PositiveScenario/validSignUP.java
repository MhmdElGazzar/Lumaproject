package PositiveScenario;

import Base.TestBase;
import Pages.AccountPage;
import Pages.HomePage;
import Pages.SignUp;
import Utilities.DataUtil;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class validSignUP extends TestBase {
    private HomePage homePage;
    private AccountPage accountPage;
    @Test
    public void signup()
    {
        homePage = new HomePage(driver);
        SignUp signUp=homePage.clickonSignUpBtn();
        Assert.assertEquals(signUp.Getheader(),"Create New Customer Account");
        signUp.SetFristName(DataUtil.getJsonData("TestData","SignUpCred","name"));
        signUp.SetLastName(DataUtil.getJsonData("TestData","SignUpCred","Lname"));
        signUp.Setemail(DataUtil.getJsonData("TestData","SignUpCred","email"));
        signUp.SetPasswoed(DataUtil.getJsonData("TestData","SignUpCred","password"));
        signUp.SetConfirmPasswoed(DataUtil.getJsonData("TestData","SignUpCred","password"));
        accountPage = signUp.ClickonSubmit();


        Assert.assertTrue(accountPage.messageSuccessSignup().contains("Thank you for registering"));


        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(accountPage.GetHeaderAfterSignUp(), "Welcome, "+
                (DataUtil.getJsonData("TestData","SignUpCred","name")) +" " +
                (DataUtil.getJsonData("TestData","SignUpCred","Lname")) +"!");



    }

}
