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
        choseItem();
    }

    private void choseItem ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("ASUS Gaming monitor TUF Gaming 27 VA - VG27VQ")));
        WebElement item = driver.findElement(By.linkText("ASUS Gaming monitor TUF Gaming 27 VA - VG27VQ"));
        item.click();
    }

}

