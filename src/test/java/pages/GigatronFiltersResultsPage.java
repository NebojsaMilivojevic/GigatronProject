package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GigatronFiltersResultsPage extends BaseHelper
{
    WebDriver driver;

    public GigatronFiltersResultsPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    public void chooseItem (String itemName)
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(itemName)));
        WebElement item = driver.findElement(By.linkText(itemName));
        item.click();
    }

}
