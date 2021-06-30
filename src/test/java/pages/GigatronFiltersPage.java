package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GigatronFiltersPage extends BaseHelper
{
    WebDriver driver;

    public GigatronFiltersPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void filterSelectedItems ()
    {
        clickOnFilterAsus();
    }

    private void clickOnFilterAsus ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("brand")));
        List<WebElement> brands = driver.findElements(By.className("force-display"));
        WebElement brandAsus = brands.get(1); // also replace number on 42. line in this class, most of the time Asus is on 0 (zero) position
        brandAsus.click();
    }

    /*
    * Method for assert which return filter name and item number in brackets
    */
    public String getFilterAsusName ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("brand")));
        List<WebElement> brands = driver.findElements(By.className("force-display"));
        WebElement brandAsus = brands.get(1); // line 31. in this class
        String brandAsusFilter = brandAsus.getText();
        brandAsusFilter = brandAsusFilter.replace("\n","").replace("(","").replace(")","").trim();
        System.out.println("BrandAsusFilter after replace: " + brandAsusFilter);
        return brandAsusFilter.toLowerCase();
    }

    public String getItemGroupName ()
    {
        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("nav-title-grid"), "Grafičke karte"));
        WebElement itemGroupName = driver.findElement(By.className("nav-title-grid"));
        System.out.println("Item group name is: "+ itemGroupName.getText());
        return itemGroupName.getText();
    }
}
