package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
WebDriver driver;
    public AccountPage(WebDriver driver) {
        this.driver=driver;
    }
    private By TestSuccMessage=By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div");
    private By headerAfterSignUP=By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[1]/span");
    private By headerTitle =By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span");
    private By ChangePasswordLink=By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[3]/div[2]/div/div[2]/a[2]");
    private By messageTitle=By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span");


    public EditAccount ClickONChangePassword()
    {
        Waits.clickOnElement(driver,ChangePasswordLink);
        return new EditAccount(driver);
    }
    public String messageSuccessSignup()
    {
        Waits.waitForElementPresent(driver,TestSuccMessage);
        return driver.findElement(TestSuccMessage).getText();
    }


    public String GetHeaderAfterSignUp()
    {
        Waits.waitForElementPresent(driver,headerAfterSignUP);


        Waits.waitForElementPresent(driver,headerTitle);
        return driver.findElement(headerAfterSignUP).getText();
    }

    public String GetTitle()
    {
        Waits.waitForElementPresent(driver,messageTitle);
        return driver.findElement(messageTitle).getText();
    }
}
