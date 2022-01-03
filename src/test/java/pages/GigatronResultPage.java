package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GigatronResultPage extends BaseHelper
{

    WebDriver driver;

    public GigatronResultPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    public void chooseItemFromSearchBox ()
    {
        chooseItem();
    }

    private void chooseItem ()
    {
      WebElement item =  wdWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("ASUS LED VG278QR")));
        //WebElement item = driver.findElement(By.linkText("ASUS LED VG278QR"));
        item.click();

    }

    public String getItemName ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("ASUS LED VG278QR")));
        WebElement itemName = driver.findElement(By.linkText("ASUS LED VG278QR"));
        System.out.println("Item name on result page is: " + itemName.getText());
        return itemName.getText();
    }

}

