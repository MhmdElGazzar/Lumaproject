package PositiveScenario;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DataUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[1]/span"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String text = (String) js.executeScript("return arguments[0].textContent;", element);

        Assert.assertEquals(text, "Welcome, "+
                (DataUtil.getJsonData("TestData","LoginCred","name")) +" " +
                (DataUtil.getJsonData("TestData","LoginCred","Lname")) +"!");


    }
}
