package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class GigatronBasketPage extends BaseHelper
{
    WebDriver driver;
    private List<Item> basketItems = new ArrayList<Item>();

    double sumBasketItems;

    public GigatronBasketPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void goToBasket ()
    {
        clickOnContinueLikeUnregisteredBuyerButton();
        createListOfBasketItems();
    }


    private void clickOnContinueLikeUnregisteredBuyerButton()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-content")));
        WebElement unregisteredBuyerButton = driver.findElement(By.className("cart-reg"));
        unregisteredBuyerButton.click();
    }

    private String getBasketItemName(int i)
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-items")));
        List<WebElement> itemNames = driver.findElements(By.className("titleItemLink"));
        WebElement itemName = itemNames.get(i);
        String itemNameFromBasket = itemName.getText();
        System.out.println("Item name from basket is: " + itemNameFromBasket);
        return itemNameFromBasket.toLowerCase();
    }

    private double getBasketItemPrice(int i)
    {
        List<WebElement> itemPrices = driver.findElements(By.className("total-item-cart"));
        WebElement totalPrice = itemPrices.get(i);
        String itemPriceFromBasket = totalPrice.getText();
        System.out.println("Item price from basket is: " + itemPriceFromBasket);
        itemPriceFromBasket = itemPriceFromBasket.substring(13,itemPriceFromBasket.length()-7);
        itemPriceFromBasket = itemPriceFromBasket.replace(".","").trim();

        double itemPrice = Double.parseDouble(itemPriceFromBasket);
     // System.out.println("Item price after substring, replace and parseDouble is:" + itemPrice);
        sumBasketItems += itemPrice;
        return itemPrice;
    }

     /*
     * Create a list of items in basket
      */
    private void createListOfBasketItems ()
    {
        int turn = getSizeOfBasket();

        for (int i = 0; i<turn; i++)
        {
            Item item = new Item();
            item.setName(getBasketItemName(i));
            item.setPrice(getBasketItemPrice(i));
            basketItems.add(item);
        }
    }


    /*
     * Returns the size of basket for creating list of basket items (turns!!!)
     */
    public int getSizeOfBasket ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-item-row")));
        List<WebElement> itemRows = driver.findElements(By.className("cart-item-row"));
        int basketSize = itemRows.size();
        System.out.println("Number of items in basket is: " + basketSize);
        return basketSize;
    }


    /*
     * Return a list of items in basket
     */
    public List<Item> getBasketItems ()
    {
        return basketItems;
    }

    public double getSumBasketItems ()
    {
        System.out.println("Sum of basket items is: " + sumBasketItems);
        return sumBasketItems;
    }

    public double getTotalPrice ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("final-price")));
        WebElement finalPrice = driver.findElement(By.className("final-price"));
        String totalPriceText = finalPrice.getText();
        totalPriceText = totalPriceText.substring(0,totalPriceText.length()-7);
        totalPriceText = totalPriceText.replace(".","").trim();
        double totalPrice = Double.parseDouble(totalPriceText);
        System.out.println("Total price in basket is: " + totalPrice);
        return totalPrice;
    }

}
