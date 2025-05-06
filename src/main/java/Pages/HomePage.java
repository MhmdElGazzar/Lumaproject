package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
    }

    //login,signup
    private By loginBtn = By.partialLinkText("Sign In");
    private By signUpBtn=By.partialLinkText("Create an Account");
    private By headerTitle=By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span");
    private By headerAfterLogin=By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[1]/span");

    //search
    private By Search_input=By.id("search");
    private By Search_icon=By.xpath("//*[@id=\"search_mini_form\"]/div[2]/button");

    //addProductinCart
    private By Product1=By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[2]/div[5]/div/div/ol/li[1]/div/div/strong/a");

    //AccountBtn
    private By DropDownIcon=By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button");
    private By AccountPageBtn=By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[1]/a");

    //logout
    private By LogoutBtn=By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]/a");

    //bar
    private By womanBtn=By.xpath("//*[@id=\"ui-id-4\"]/span[2]");
    private By TopBtn=By.xpath("//*[@id=\"ui-id-9\"]/span[2]");
    private By jaketBtn=By.xpath("//*[@id=\"ui-id-11\"]/span");
    //methods
    public LoginPage clickonLoginBtn()
    {
        Waits.clickOnElementPresece(driver,loginBtn);

        return new LoginPage(driver);
    }

    public SignUp clickonSignUpBtn() {
        //Waits.clickOnElement(driver,signUpBtn);
        Waits.clickOnElementPresece(driver,signUpBtn);

        return new SignUp(driver);
    }

    public String GetHeaderAfterLogin() {
        Waits.waitForElementPresent(driver,headerAfterLogin);

        Waits.waitForElementPresent(driver,headerTitle);
        return driver.findElement(headerAfterLogin).getText();
    }

    public SearchPage SearchForItem(String Item)
    {
        Waits.sendData(driver,Search_input,Item);
        Waits.clickOnElement(driver,Search_icon);
        return new SearchPage(driver);
    }

    public ProductPage ClickOnProduct()
    {
        Waits.clickOnElement(driver,Product1);
        return new ProductPage(driver);
    }


    public void ClickOnDropDown()
    {
        Waits.clickOnElement(driver,DropDownIcon);
    }

    public AccountPage ClickONAccountBtn()
    {
        Waits.clickOnElement(driver,AccountPageBtn);
        return new AccountPage(driver);
    }
    public HomePage LogOut()
    {
        Waits.clickOnElement(driver,LogoutBtn);
        return this;
    }

    public String GetTextLogininbtn()
    {
        Waits.waitForElementPresent(driver,loginBtn);
        return driver.findElement(loginBtn).getText();
    }
    public JacketPage selectONProductFromCategory()
    {
        Waits.waitForElementPresent(driver,womanBtn);
        Waits.HoverOnProduct(driver,womanBtn);
        Waits.waitForElementPresent(driver,TopBtn);
        Waits.HoverOnProduct(driver,TopBtn);
        Waits.waitForElementPresent(driver,jaketBtn);
        Waits.clickOnElement(driver,jaketBtn);
        return new JacketPage(driver);
    }

}

