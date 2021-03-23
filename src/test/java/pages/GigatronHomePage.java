package pages;

import helpers.BaseHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class GigatronHomePage extends BaseHelper
{
    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/div[2]/div/header/div[1]/div/div/div/input")
    WebElement searchBox;

    @FindBy(className = "search-icon")
    WebElement magnifierIcon;

    WebDriver driver;
    public GigatronHomePage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    public void gigatronSearch (String term)
    {
        navigateToGigatronHomePage();
        cookieButton();
        enterTermInSearchBox(term);
        clickOnMagnifierIcon();
    }

    private void navigateToGigatronHomePage()
    {
        driver.get("https://gigatron.rs/");
    }

    private void cookieButton ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn"))).click();
    }

    private void enterTermInSearchBox (String term)
    {
        wdWait.until(ExpectedConditions.visibilityOf(searchBox));

        // WebElement searchBox = driver.findElement(By.id("searchbox"));
        //js.executeScript("arguments[0].click();", searchBox);
        searchBox.click();
        //searchBox.clear();
        // WebElement searchField = driver.findElement(By.className("search-container"));
        //js.executeScript("arguments[0].setAttribute();",term,searchBox);
        searchBox.sendKeys(term);
        // sada krece:  /html/body/header/div/nav/div/div/div/div/div/ul/li[4]/ul/li[4]/ul[2]/li[6]     preko hoverborda
    }

    private void clickOnMagnifierIcon ()
    {
        clickOnElement(magnifierIcon); // ovo mi je metoda iz BaseHelper-a umesto magnifierIcom.click()
    }

}

