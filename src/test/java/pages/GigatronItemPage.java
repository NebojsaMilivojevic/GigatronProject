package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class GigatronItemPage extends BaseHelper
{
    WebDriver driver;
    private List<Item> chosenItems = new ArrayList<Item>();

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

    private void addToBasketButton()  // 1. metoda je public zato sto se poziva i u drugim klasama // 2. mislim da treba biti ipak private!!!!!
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("btnTxt"))); // prebaciti u @FindBy
        WebElement addtoBasketButton = driver.findElement(By.className("btnTxt"));
        createListOfChosenItems();
        addtoBasketButton.click();
    }

    private String getItemName ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-title")));  // mozda prebaciti u @FindBy
        WebElement item = driver.findElement(By.className("product-title"));  // element u elementu
        WebElement itemName = item.findElement(By.xpath("h1[1]"));   // element u elementu
        String chosenItemName = itemName.getText();                  // element u elementu
        System.out.println("Chosen item name is: " + chosenItemName);
        /*WebElement imgAlt = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div/div/div/div/div/div[2]/div[2]/div[1]/div[1]/div[3]/div/div/div/img"));
        String alt = imgAlt.getAttribute("alt");
        System.out.println("Img alt attribute is: " + alt);*/
        String titleName = driver.getTitle();
        String[] arr = titleName.split(chosenItemName);
        String componentText = arr[0].toLowerCase();
        return componentText + chosenItemName.toLowerCase();
    }

    private double getItemPrice ()
    {
        WebElement priceHolder = driver.findElement(By.className("price"));   // mozda prebaciti u @FindBy
        String chosenItemPrice = priceHolder.getText();
        chosenItemPrice = chosenItemPrice.substring(0,chosenItemPrice.length()-3);
        chosenItemPrice = chosenItemPrice.replace(".","");
        double priceOfChosenItem = Double.parseDouble(chosenItemPrice);
        System.out.println("Chosen item price is: " + priceOfChosenItem);
        return priceOfChosenItem;
    }


    //Creates a list of chosen items

    private void createListOfChosenItems()
    {
        Item item = new Item();
        item.setName(getItemName());
        item.setPrice(getItemPrice());
        System.out.println(item.getName() + " " + item.getPrice());
        chosenItems.add(item);
    }


   //  Return a list of chosen items

    public List<Item> getChosenItems()
    {
        return chosenItems;
    }

    private void nazadNaPretraguButton ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("shop-buttons")));
        WebElement nazadNaPretragu = driver.findElement(By.className("white"));
        js.executeScript("arguments[0].click();", nazadNaPretragu);
    }

    public void navigateBack ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-title")));  // mozda prebaciti u @FindBy
        driver.navigate().back();
    }
}
