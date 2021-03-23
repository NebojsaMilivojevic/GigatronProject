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

    public GigatronBasketPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void goToBasket ()
    {
        clickOnIconBasket();
        clickOnContinueLikeUnregisteredBuyerButton();
        createListOfBasketItems();
    }

    private void clickOnIconBasket()
    {
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("icon-link"),"2"));
        WebElement iconBasket = driver.findElement(By.className("icon-link"));
        System.out.println("Number of items in basket on basket icon is: " + iconBasket.getText());
        iconBasket.click();
    }

    private void clickOnContinueLikeUnregisteredBuyerButton()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-content")));
        WebElement unregisteredBuyerButton = driver.findElement(By.className("cart-reg"));
        unregisteredBuyerButton.click();
    }

    private String getBasketItemName(int i)
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-items")));   // mozda i za @FindBy
        List<WebElement> itemNames = driver.findElements(By.className("titleItemLink"));
        WebElement itemName = itemNames.get(i);
        String itemNameFromBasket = itemName.getText();
        System.out.println("Item name from basket is: " + itemNameFromBasket);
        return itemNameFromBasket.toLowerCase();
    }

    private double getBasketItemPrice(int i)
    {
        List<WebElement> itemPrices = driver.findElements(By.className("total-item-cart"));  // mozda i za @FindBy
        WebElement totalPrice = itemPrices.get(i);
        String itemPriceFromBasket = totalPrice.getText();
        System.out.println("Item price from basket is: " + itemPriceFromBasket);
        itemPriceFromBasket = itemPriceFromBasket.substring(13,19); // ovako ne moze ako cena nije u desetini hiljada dinara
        itemPriceFromBasket = itemPriceFromBasket.replace(".","").trim();

      //  System.out.println("Item price after substring, and replace: " + itemPriceFromBasket);
        double itemPrice = Double.parseDouble(itemPriceFromBasket);
     //   System.out.println("Item price after substring, replace and parseDouble is:" + itemPrice);
        return itemPrice;
    }


     //Create a list of items in basket

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


    //Returns the size of basket


    private int getSizeOfBasket ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-item-row")));
        List<WebElement> itemRows = driver.findElements(By.className("cart-item-row"));
        int basketSize = itemRows.size();
        System.out.println("Number of items in basket is: " + basketSize);
        return basketSize;
    }


    //Return a list of items in basket

    public List<Item> getBasketItems ()
    {
        return basketItems;
    }

}
