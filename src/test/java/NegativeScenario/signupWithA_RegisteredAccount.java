package NegativeScenario;

import Base.TestBase;
import Pages.HomePage;
import Pages.SignUp;
import Utilities.DataUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class signupWithA_RegisteredAccount extends TestBase {
    private HomePage homePage;
    @Test
    public void signup()
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
}
