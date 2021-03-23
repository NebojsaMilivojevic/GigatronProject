package tests;

import org.junit.Test;
import pages.*;

import java.util.Collections;
import java.util.List;

public class GigatronTest extends BaseTest
{
    final String itemName1 = "ASUS Gaming monitor TUF Gaming 27 VA - VG27VQ";
    final String itemName2 = "ASUS nVidia GeForce GT 1030 Phoenix OC 2GB GDDR5 64bit - PH-GT1030-O2G";
    final String itemName3 = "ASUS nVidia GeForce GT 1030 2GB GDDR5 64bit - GT1030-2G-BRK";

    @Test
    public void searchTest () throws InterruptedException {

        GigatronHomePage homePage = new GigatronHomePage(driver);
        homePage.gigatronSearch(itemName1);

        GigatronResultPage resultPage = new GigatronResultPage(driver);
        resultPage.chooseItemFromSearchBox();

        GigatronItemPage itemPage = new GigatronItemPage(driver);
        itemPage.addItemToBasket();

        GigatronSelectItemsPage selectItemsPage = new GigatronSelectItemsPage(driver);
        selectItemsPage.selectGroupOfItems();

        GigatronFiltersPage filtersPage = new GigatronFiltersPage(driver);
        filtersPage.filterSelectedItems();

        GigatronFiltersResultsPage filtersResultsPage = new GigatronFiltersResultsPage(driver);
        filtersResultsPage.chooseItem(itemName2);

        itemPage.addItemToBasket();

        navigateBack();

        filtersResultsPage.chooseItem(itemName3);

        itemPage.addItemToBasket();

        GigatronBasketPage basketPage = new GigatronBasketPage(driver);
        basketPage.goToBasket();

        List<Item> chosenItems = itemPage.getChosenItems();
        List<Item> basketItems = basketPage.getBasketItems();

        Collections.sort(chosenItems);
        Collections.sort(basketItems);
        Thread.sleep(4000);
    }
}
