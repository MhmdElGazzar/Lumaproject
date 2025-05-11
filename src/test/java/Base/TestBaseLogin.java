package Base;

import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DataUtil;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
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
