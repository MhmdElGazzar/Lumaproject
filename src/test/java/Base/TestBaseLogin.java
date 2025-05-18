package Base;

import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DataUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class TestBaseLogin {
    public WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); //Read -> Document
        driver = new ChromeDriver(options);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

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

        Assert.assertEquals(homePage.GetHeaderAfterLogin(), "Welcome, "+
                (DataUtil.getJsonData("TestData","LoginCred","name")) +" " +
                (DataUtil.getJsonData("TestData","LoginCred","Lname")) +"!");



    }
}
