package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelper
{
   public   int brojBaseHelper = 10;
    protected  String imeBaseHelper = "Nebo";
    int broj1BaseHelper = 101;
   private String ime1BaseHelper = "Nebo1";

    protected static WebDriver driver = new ChromeDriver();
    protected  WebDriverWait wdWait = new WebDriverWait(driver, 40);

    protected static JavascriptExecutor js = (JavascriptExecutor) driver;

    protected  void clickOnElement (WebElement element)
    {
        element.click();
    }

    public void navigateBack ()
    {
        driver.navigate().back();
    }
}

