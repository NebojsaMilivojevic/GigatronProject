package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GigatronFiltersResultsPage extends BaseHelper
{
    WebDriver driver;

    List<String> itemNameList = new ArrayList<String>();

    public GigatronFiltersResultsPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);

    }

    public void chooseRandomItem ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("selected-filter-drop")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product-item-name")));
        List<WebElement> items = driver.findElements(By.className("product-item-name"));

        Random rnd = new Random();
        int randomNumber = rnd.nextInt(items.size());
        WebElement item = items.get(randomNumber);

        if (itemNameList.contains(item.getText()))
        {
            chooseRandomItem ();
        } else {
            itemNameList.add(item.getText());
            js.executeScript("arguments[0].scrollIntoView();", item);
            wdWait.until(ExpectedConditions.visibilityOf(item));
            js.executeScript("arguments[0].click();", item); // item.click();


        }
    }

    /*
    * get number of items after filter tick and convert int to String, added in method getFilterNamePlusTextNumber
    */
    public String getTextNumberOfItems ()
    {
        wdWait.until(ExpectedConditions.numberOfElementsToBe(By.className("selected-filter-drop"),1));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product-item-name"))); // product-box-gallery
        List<WebElement> itemsResult = driver.findElements(By.className("product-item-name")); // product-box-gallery
        int itemsNumber = itemsResult.size();
        System.out.println("Number of items on filter results page is: " + itemsNumber);
        return String.valueOf(itemsNumber);
    }

    public String getFilterNamePlusTextNumber ()
    {
        Actions act = new Actions(driver);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("selected-filter-drop")));
        WebElement filterText = driver.findElement(By.className("selected-filter-drop"));
        act.moveToElement(filterText).perform();
        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("selected-filter-text"),"Asus"));
        WebElement filter = driver.findElement(By.className("selected-filter-text"));
        String filterName = filter.getText() + getTextNumberOfItems();
        System.out.println("Filter name is: " + filterName);
        return filterName.toLowerCase();
    }

    public void clickOnIconBasket()
    {

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("fb-logo")));
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("icon-number"),"1"));
        WebElement iconBasket = driver.findElement(By.className("icon"));
        js.executeScript("arguments[0].scrollIntoView();", iconBasket);
        js.executeScript("arguments[0].click();", iconBasket);//iconBasket.click();
    }
}
