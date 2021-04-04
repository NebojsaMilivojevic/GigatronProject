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
        WebElement brandAsus = brands.get(0);
        brandAsus.click();
    }

    /*
    * Method for assert which return filter name and item number in brackets
    */
    public String getFilterAsusName ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("brand")));
        List<WebElement> brands = driver.findElements(By.className("force-display"));
        WebElement brandAsus = brands.get(0);
        String brandAsusFilter = brandAsus.getText();
        brandAsusFilter = brandAsusFilter.replace("\n","").replace("(","").replace(")","").trim();
        System.out.println("BrandAsusFilter after replace: " + brandAsusFilter);
        return brandAsusFilter.toLowerCase();
    }
}
