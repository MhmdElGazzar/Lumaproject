package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditAccount {
    WebDriver driver;
    public EditAccount(WebDriver driver) {
        this.driver=driver;
    }

    private By TitleOfPage= By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span");
    private By Current_Password=By.xpath("//*[@id=\"current-password\"]");
    private By New_Password=By.xpath("//*[@id=\"password\"]");
    private By ConfirmPassword=By.xpath("//*[@id=\"password-confirmation\"]");
    private By SaveBtn=By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button");
    public String GetHeader() {
        Waits.waitForElementPresent(driver,TitleOfPage);
        return driver.findElement(TitleOfPage).getText();
    }

    public void SetCurrentPassword(String CurrPassword)
    {
        Waits.sendData(driver,Current_Password,CurrPassword);
    }

    public void SetNewPassword(String NPassword)
    {
        Waits.sendData(driver,New_Password,NPassword);
    }

    public void SetConfirmPassword(String CPassword)
    {
        Waits.sendData(driver,ConfirmPassword,CPassword);
    }

    public LoginPage ClickONSave()
    {
        Waits.clickOnElement(driver,SaveBtn);
        return new LoginPage(driver);
    }


}
