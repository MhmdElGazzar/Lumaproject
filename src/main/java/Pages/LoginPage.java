package Pages;

import Utilities.DataUtil;
import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.NoSuchElementException;

public class LoginPage {
    WebDriver driver;

    By headerTitle=By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span");
    By SignInBTN=By.id("send2");
    By email_input=By.id("email");
    By password_input=By.id("pass");
    private By AlertError=By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div");

    private By EmailMessageError=By.xpath("//*[@id=\"email-error\"]");
    private By PasswordMessageError=By.xpath("//*[@id=\"pass-error\"]");

    public boolean isEmailRequiredMessageDisplayed() {
        try {
            WebElement emailErrorMessage = driver.findElement(EmailMessageError);
            return emailErrorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isPasswordRequiredMessageDisplayed() {
        try {
            WebElement passErrorMessage = driver.findElement(PasswordMessageError);
            return passErrorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public String Getheader() {
       Waits.waitForElementPresent(driver,SignInBTN);
        return driver.findElement(headerTitle).getText();
    }
    public LoginPage EnterEmail(String email) {
        Waits.sendData(driver,email_input,email);
        return this;
    }
    public LoginPage Enterpassword(String password) {
        Waits.sendData(driver,password_input,password);
        return this;
    }
    public HomePage click_on_SignIn_btn(){Waits.clickOnElement(driver,SignInBTN);
    return new HomePage(driver);}

    public String ErrorMessage()
    {
        return driver.findElement(AlertError).getText();
    }


    public HomePage LoginBySpecificAccount(String email,String password)
    {
        EnterEmail(email)
                .Enterpassword(password);

        Waits.clickOnElement(driver,SignInBTN);



        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
        Assert.assertEquals(homePage.GetHeaderAfterLogin(), "Welcome, "+
                (DataUtil.getJsonData("TestData","LoginCred","name")) +" " +
                (DataUtil.getJsonData("TestData","LoginCred","Lname")) +"!");
*/
return new HomePage(driver);
    }
}
