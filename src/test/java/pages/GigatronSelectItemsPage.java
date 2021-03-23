package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GigatronSelectItemsPage extends BaseHelper
{
    WebDriver driver;

    public GigatronSelectItemsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    public void selectGroupOfItems ()
    {
        proizvodiButton();
        goToRacunariIKomponenteFirst();
        selectGrafickeKarte();
    }

    private void proizvodiButton ()
    {
        WebElement megaButton = driver.findElement(By.className("megabtn")); // prebaciti u @FindBy
        megaButton.click();
    }

    private void goToRacunariIKomponenteFirst ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("content")));
        WebElement racunariIKomponente = driver.findElement(By.id("main-nav-2"));
        Actions act = new Actions(driver);
        act.moveToElement(racunariIKomponente).perform();
    }

    private void selectGrafickeKarte ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("sub-menu")));
        WebElement grafickeKarte = driver.findElement(By.linkText("Grafičke karte"));
        grafickeKarte.click();
    }

}
