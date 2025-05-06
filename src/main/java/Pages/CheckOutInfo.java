package Pages;

import Utilities.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.NoSuchElementException;

public class CheckOutInfo {
    private WebDriver driver;

    public CheckOutInfo(WebDriver driver)
    {
        this.driver=driver;
    }

    private By email=By.id("customer-email");
    private By Fname= By.xpath("//input[@name='firstname']");
    private By Lname= By.xpath("//input[@name='lastname']");
    private By Company= By.xpath("//input[@name='company']");
    private By Street= By.xpath("//input[@name='street[0]']");
    private By city= By.xpath("//input[@name='city']");
    private By state= By.xpath("//select[@name='region_id']");
    private By StateIndex0=By.xpath("//select[@name='region_id']/option[3]");
    private By Zip= By.xpath("//input[@name='postcode']");
    private By Country= By.xpath("//select[@name='country_id']");
    private By CountryEgypt=By.xpath("//select[@name='country_id']/option[68]");
    private By PhoneNum= By.xpath("//input[@name='telephone']");
    private By TableRate= By.xpath("//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[1]/td[1]/input");
    private By Tax= By.xpath("//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[2]/td[1]/input");
    private By NextBtn= By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button/span");
    private By CheckOutRatio=By.xpath("//*[@id=\"billing-address-same-as-shipping-checkmo\"]");
    private By CheckOutBtn=By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button/span");
    private By messageTitle=By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span");
    private By addressRatio=By.xpath("//*[@id=\"billing-address-same-as-shipping-checkmo\"]");

//methods
    public void setEmail(String Email)
    {
        Waits.sendData(driver,email,Email);
    }

    public boolean isFormDisplayed() {
        try {
            return driver.findElement(Fname).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isAddressRatioDisplay() {
        try {
            return driver.findElement(addressRatio).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void ClickOnRatioOFAddress()
    {
        Waits.clickOnElement(driver,addressRatio);
    }


    public void setFname(String FristName)
    {
        Waits.sendData(driver,Fname,FristName);
    }
    public void setLname(String LastName)
    {
        Waits.sendData(driver,Lname,LastName);
    }
    public void setCompany(String CompanyData)
    {
        Waits.sendData(driver,Company,CompanyData);
    }
    public void setStreet(String StreetData){Waits.sendData(driver,Street,StreetData);}
    public void SetCity(String CityData){Waits.sendData(driver,city,CityData);}
    public void SetState(String stateData)
    {
        Waits.sendData(driver,state,stateData);
    }
    public void SetZip(String ZipCode){Waits.sendData(driver,Zip,ZipCode);}
    public void SetCountry()
    {
        Waits.clickOnElement(driver,Country);
        Waits.clickOnElement(driver,CountryEgypt);
    }
    public void SetPhone(String PhoneNumber){Waits.sendData(driver,PhoneNum,PhoneNumber);}
    public void SetTableRate(){Waits.clickOnElement(driver,TableRate);}
    public void SetTax(){Waits.clickOnElement(driver,Tax);}
    public void ClickOnBtnNext(){Waits.clickOnElement(driver,NextBtn);}

    public void ClickonCheckOutRatio(){Waits.clickOnElement(driver, CheckOutRatio);}
    public void ClickonCheckOutBtn(){
        Waits.clickOnElement(driver,CheckOutBtn);
    }
    public String GetTitle()
    {
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Waits.waitForElementPresent(driver,messageTitle);
        return driver.findElement(messageTitle).getText();
    }


}
