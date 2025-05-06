package PositiveScenario;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DataUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class validLogin extends TestBase {
    private HomePage homePage;
    @Test
    public void Testlogin()
    {
        homePage = new HomePage(driver);
        LoginPage loginPage=homePage.clickonLoginBtn();
        Assert.assertEquals(loginPage.Getheader(),"Customer Login");
        loginPage.EnterEmail
                        (DataUtil.getJsonData("TestData","LoginCred","email"))
                .Enterpassword
                        (DataUtil.getJsonData("TestData","LoginCred","password"))
                .click_on_SignIn_btn();

        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(homePage.GetHeaderAfterLogin(), "Welcome, "+
                (DataUtil.getJsonData("TestData","LoginCred","name")) +" " +
                (DataUtil.getJsonData("TestData","LoginCred","Lname")) +"!");


    }
}
