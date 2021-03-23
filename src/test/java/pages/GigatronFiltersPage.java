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
        filterAsus();
        filterMagistralaMemorije();

    }

    private void filterAsus ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("brand")));
        List<WebElement> brands = driver.findElements(By.className("force-display"));
        WebElement brandAsus = brands.get(0);
        brandAsus.click();
    }

    private void filterMagistralaMemorije ()
    {
        WebElement memories = driver.findElement(By.id("filters-Magistrala memorije"));
        List<WebElement> filters = memories.findElements(By.className("filter"));
        WebElement filter64bit = filters.get(1);
        js.executeScript("arguments[0].scrollIntoView;",filter64bit);
        wdWait.until(ExpectedConditions.visibilityOf(filter64bit));
        js.executeScript("arguments[0].click();", filter64bit);

    }

}
