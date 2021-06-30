package tests;


import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import pages.*;
import java.util.Collections;
import java.util.List;

public class GigatronTest extends BaseTest
{
    final String itemName1 = "ASUS LED VG278QR";

    @Test
    public void searchFunctionalityTest () throws InterruptedException
    {

        GigatronHomePage homePage = new GigatronHomePage(driver);
        homePage.gigatronSearch(itemName1);

        GigatronResultPage resultPage = new GigatronResultPage(driver);

        Assert.assertEquals(itemName1, resultPage.getItemName());

        Thread.sleep(4000); // added for presentation
    }
    @Test
    public void addToBasketFunctionalityTest () throws InterruptedException
    {

        GigatronHomePage homePage = new GigatronHomePage(driver);
        homePage.gigatronSearch(itemName1);

        GigatronResultPage resultPage = new GigatronResultPage(driver);
        Assert.assertEquals(itemName1, resultPage.getItemName());

        resultPage.chooseItemFromSearchBox();

        GigatronItemPage itemPage = new GigatronItemPage(driver);
        itemPage.addItemToBasket();

        Assert.assertTrue("Error !!!",itemPage.getAddToBasketButtonText().contains(itemPage.getIconBasketText()));

        Thread.sleep(4000); // added for presentation
    }

    @Test
    public void selectItemsFunctionalityTest () throws InterruptedException
    {
        GigatronSelectItemsPage selectItemsPage = new GigatronSelectItemsPage(driver);
        selectItemsPage.selectGroupOfItems();
        String grafickeKarteText  = selectItemsPage.getGrafickeKarteText();
        selectItemsPage.selectGrafickeKarte();

        GigatronFiltersPage filtersPage = new GigatronFiltersPage(driver);
        Assert.assertTrue(filtersPage.getItemGroupName().contains(grafickeKarteText));

        Thread.sleep(4000); // added for presentation
    }

    @Test
    public void filterFunctionalityTest () throws InterruptedException
    {
        GigatronSelectItemsPage selectItemsPage = new GigatronSelectItemsPage(driver);
        selectItemsPage.selectGroupOfItems();
        String grafickeKarteText  = selectItemsPage.getGrafickeKarteText();
        selectItemsPage.selectGrafickeKarte();

        GigatronFiltersPage filtersPage = new GigatronFiltersPage(driver);
        Assert.assertTrue(filtersPage.getItemGroupName().contains(grafickeKarteText));

        filtersPage.filterSelectedItems();

        GigatronFiltersResultsPage filtersResultsPage = new GigatronFiltersResultsPage(driver);

        Assert.assertEquals(filtersPage.getFilterAsusName(),filtersResultsPage.getFilterNamePlusTextNumber());

        Thread.sleep(4000); // added for presentation
    }

    @Test
    public void basketFunctionalityTest () throws InterruptedException
    {
        GigatronSelectItemsPage selectItemsPage = new GigatronSelectItemsPage(driver);
        selectItemsPage.selectGroupOfItems();
        String grafickeKarteText  = selectItemsPage.getGrafickeKarteText();
        selectItemsPage.selectGrafickeKarte();

        GigatronFiltersPage filtersPage = new GigatronFiltersPage(driver);
        Assert.assertTrue(filtersPage.getItemGroupName().contains(grafickeKarteText));

        filtersPage.filterSelectedItems();

        GigatronFiltersResultsPage filtersResultsPage = new GigatronFiltersResultsPage(driver);

        Assert.assertEquals(filtersPage.getFilterAsusName(),filtersResultsPage.getFilterNamePlusTextNumber());
                                                                                                //assert
        filtersResultsPage.chooseRandomItem(); // choose first item

        GigatronItemPage itemPage = new GigatronItemPage(driver);
        itemPage.addItemToBasket();

        itemPage.navigateBack();

        filtersResultsPage.chooseRandomItem(); // choose second item

        itemPage.addItemToBasket();

        filtersResultsPage.clickOnIconBasket();

        GigatronBasketPage basketPage = new GigatronBasketPage(driver);
        basketPage.goToBasket();

        List<Item> chosenItems = itemPage.getChosenItems();
        List<Item> basketItems = basketPage.getBasketItems();

        Collections.sort(chosenItems);
        Collections.sort(basketItems);

        for(int i =0; i<basketPage.getSizeOfBasket(); i++)
        {
            Item chosenItem = chosenItems.get(i);
            Item basketItem = basketItems.get(i);
            System.out.println("Chosen item is: "+ chosenItem.getName());
            System.out.println("Basket item is: " + basketItem.getName());
            Assert.assertTrue("Names are not equal! ",basketItem.getName().contains(chosenItem.getName()));
        }

        double chosenItemsSum = itemPage.getSumChooseItems();
        double basketItemsSum = basketPage.getSumBasketItems();
        double totalPrice = basketPage.getTotalPrice();
        boolean allEquals = false;
        if (Double.compare(chosenItemsSum, basketItemsSum) == 0 && Double.compare(basketItemsSum, totalPrice) == 0)
            allEquals = true;

        Assert.assertTrue(allEquals);

        Thread.sleep(4000); // added for presentation
    }
}
