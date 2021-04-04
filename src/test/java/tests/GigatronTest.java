package tests;


import org.junit.Assert;
import org.junit.Test;
import pages.*;
import java.util.Collections;
import java.util.List;

public class GigatronTest extends BaseTest
{
    final String itemName1 = "ASUS Gaming monitor TUF Gaming 27 VA - VG27VQ";

    @Test
    public void firstTest () throws InterruptedException
    {

        GigatronHomePage homePage = new GigatronHomePage(driver);
        homePage.gigatronSearch(itemName1);

        GigatronResultPage resultPage = new GigatronResultPage(driver);
        resultPage.chooseItemFromSearchBox();

        GigatronItemPage itemPage = new GigatronItemPage(driver);
        itemPage.addItemToBasket();

        Assert.assertTrue("Error !!!", itemPage.getIconBasketText().contains("1"));

        Thread.sleep(4000); // added for presentation
    }

    @Test
    public void secondTest () throws InterruptedException
    {
        GigatronSelectItemsPage selectItemsPage = new GigatronSelectItemsPage(driver);
        selectItemsPage.selectGroupOfItems();

        GigatronFiltersPage filtersPage = new GigatronFiltersPage(driver);
        filtersPage.filterSelectedItems();

        GigatronFiltersResultsPage filtersResultsPage = new GigatronFiltersResultsPage(driver);

        Assert.assertEquals(filtersPage.getFilterAsusName(),filtersResultsPage.getFilterNamePlusTextNumber());

        Thread.sleep(4000); // added for presentation
    }

    @Test
    public void thirdTest () throws InterruptedException
    {
        GigatronSelectItemsPage selectItemsPage = new GigatronSelectItemsPage(driver);
        selectItemsPage.selectGroupOfItems();

        GigatronFiltersPage filtersPage = new GigatronFiltersPage(driver);
        filtersPage.filterSelectedItems();

        GigatronFiltersResultsPage filtersResultsPage = new GigatronFiltersResultsPage(driver);
        filtersResultsPage.chooseRandomItem(); // choose first item

        GigatronItemPage itemPage = new GigatronItemPage(driver);
        itemPage.addItemToBasket();

        itemPage.navigateBack();

        filtersResultsPage.chooseRandomItem(); // choose second item

        itemPage.addItemToBasket();

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
