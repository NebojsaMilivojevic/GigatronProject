package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class GigatronItemPage extends BaseHelper
{
    @FindBy(className = "btnTxt")
    WebElement addtoBasketButton;

    WebDriver driver;

    private List<Item> chosenItems = new ArrayList<Item>();

    double sumChoosenItems;

    public GigatronItemPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    public void addItemToBasket ()
    {
        addToBasketButton();
        nazadNaPretraguButton();
    }

    private void addToBasketButton()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("buy-holder")));
        wdWait.until(ExpectedConditions.visibilityOf(addtoBasketButton));
        createListOfChosenItems();
        addtoBasketButton.click();
    }

    private String getItemName ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-title")));
        WebElement item = driver.findElement(By.className("product-title"));
        WebElement itemName = item.findElement(By.xpath("h1[1]"));   // element u elementu
        String chosenItemName = itemName.getText();
        System.out.println("Chosen item name is: " + chosenItemName);
        String titleName = driver.getTitle();
        System.out.println("Title name is: " + titleName);
        String[] arr = titleName.split(chosenItemName);
        String componentText = arr[0].toLowerCase();
        System.out.println("Final chosen item name is: " + componentText + chosenItemName.toLowerCase() );
        return componentText + chosenItemName.toLowerCase();
    }

    private double getItemPrice ()
    {
        WebElement priceHolder = driver.findElement(By.className("price"));
        String chosenItemPrice = priceHolder.getText();
        chosenItemPrice = chosenItemPrice.substring(0,chosenItemPrice.length()-3);
        chosenItemPrice = chosenItemPrice.replace(".","");
        double priceOfChosenItem = Double.parseDouble(chosenItemPrice);
        System.out.println("Chosen item price is: " + priceOfChosenItem);
        sumChoosenItems += priceOfChosenItem;
        return priceOfChosenItem;
    }

    /*
    * Creates a list of chosen items
    */
    private void createListOfChosenItems()
    {
        String itemName = getItemName();
        Item item = new Item();
        item.setName(itemName);
        item.setPrice(getItemPrice());
        System.out.println(item.getName() + " " + item.getPrice());
        chosenItems.add(item);
    }

    /*
    *  Return a list of chosen items
    */
    public List<Item> getChosenItems()
    {
        return chosenItems;
    }

    private void nazadNaPretraguButton ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("sw-content")));
        WebElement messageElement = driver.findElement(By.className("sw-content"));
        WebElement messageH4 = messageElement.findElement(By.xpath("h4[1]"));
        wdWait.until(ExpectedConditions.textToBePresentInElement(messageH4,"Proizvod je dodat u korpu"));
        String message = messageH4.getText();
        System.out.println("Message after adding item in basket is: "+ message);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("shop-buttons")));
       // wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("white")));
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("white")));// zamenio 10.04.
        WebElement nazadNaPretragu = driver.findElement(By.className("white"));

        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("qty_cart_icon_number"),"1"));
        wdWait.until(ExpectedConditions.textToBePresentInElement(addtoBasketButton,"U KORPI"));

        js.executeScript("arguments[0].click();", nazadNaPretragu);
    }

    public String getAddToBasketButtonText ()
    {
        wdWait.until(ExpectedConditions.textToBePresentInElement(addtoBasketButton,"U KORPI"));
        String buttonText = addtoBasketButton.getText();
        System.out.println("Text on add to basket button is: " + buttonText);
        return buttonText;
    }

    public void navigateBack ()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("icon-link")));
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-title")));
        driver.navigate().back();
    }

    public String getIconBasketText()
    {
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("icon-link"),""));
        WebElement iconBasket = driver.findElement(By.className("icon-link"));
        System.out.println("Number of items in basket on basket icon is: " + iconBasket.getText());
        return iconBasket.getText();
    }

    public double getSumChooseItems ()
    {
        System.out.println("Sum of chosen items is: " + sumChoosenItems);
        return sumChoosenItems;
    }
}
