package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUp {
    private WebDriver driver;
   public SignUp (WebDriver driver)
    {
        this.driver=driver;
    }

    private By headerTitle=By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span");
    private By submitButton = By.className("submit");
    private By First_name_input=By.xpath("//input[@name=\"firstname\"]");
    private By Last_name_input=By.xpath("//input[@name=\"lastname\"]");
    private By Email_input=By.xpath("//input[@name=\"email\"]");
    private By Password_input=By.xpath("//*[@id=\"password\"]");
    private By Confirm_Password_input=By.xpath("//*[@id=\"password-confirmation\"]");

    private By AlertError=By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div");
    public String Getheader() {
        Waits.waitForElementPresent(driver,submitButton);
        return driver.findElement(headerTitle).getText();
    }
    public void SetFristName(String FName)
    {
     Waits.sendData(driver,First_name_input,FName);
    }
    public void SetLastName(String LName)
    {
        Waits.sendData(driver,Last_name_input,LName);
    }
    public void Setemail(String email)
    {
        Waits.sendData(driver,Email_input,email);
    }
    public void SetPasswoed(String password)
    {
        Waits.sendData(driver,Password_input,password);
    }
    public void SetConfirmPasswoed(String Cpassword)
    {
        Waits.sendData(driver,Confirm_Password_input,Cpassword);
    }

    public AccountPage ClickonSubmit()
    {
        Waits.clickOnElement(driver,submitButton);
        return new AccountPage(driver);
    }
    public String ErrorMessage()
    {
        return driver.findElement(AlertError).getText();
    }




}
