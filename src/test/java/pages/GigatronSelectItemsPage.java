package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GigatronSelectItemsPage extends BaseHelper
{
    @FindBy(className = "megabtn")
    WebElement megaButton;

    WebDriver driver;

    public GigatronSelectItemsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    public void selectGroupOfItems ()
    {
        navigateToGigatronHomePage();
        cookieButton();
        proizvodiButton();
        goToRacunariIKomponenteFirst();
      //  selectGrafickeKarte();
    }

    private void navigateToGigatronHomePage()
    {
        driver.get("https://gigatron.rs/");
    }

    private void cookieButton ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn"))).click();
    }

    private void proizvodiButton ()
    {
        wdWait.until(ExpectedConditions.visibilityOf(megaButton));
        megaButton.click();
    }

    private void goToRacunariIKomponenteFirst ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("content")));
        WebElement racunariIKomponente = driver.findElement(By.id("main-nav-2"));
        Actions act = new Actions(driver);
        act.moveToElement(racunariIKomponente).perform();
    }

    public void selectGrafickeKarte ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("sub-menu")));
        WebElement grafickeKarte = driver.findElement(By.linkText("Grafičke karte"));
        grafickeKarte.click();
    }

    public String getGrafickeKarteText ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("sub-menu")));
        WebElement grafickeKarte = driver.findElement(By.linkText("Grafičke karte"));
        System.out.println("Graficke karte text is: " + grafickeKarte.getText());
        return grafickeKarte.getText();
    }
}
