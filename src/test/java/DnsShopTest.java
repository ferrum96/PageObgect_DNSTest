import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.aplana.objpajes.Init;
import ru.aplana.objpajes.Product;
import ru.aplana.objpajes.pages.*;

import java.util.List;

public class DnsShopTest {

    @Before
    public void setUp(){
        Init.initDriver();
    }

    @Test
    public void dnsShopTest() throws InterruptedException {

        SearchPage searchPlaystation = new SearchPage();
        searchPlaystation.waitElement(searchPlaystation.searchTextInput);
        searchPlaystation.searhItems("playstation");

        ResultPage resultPage = new ResultPage();
        resultPage.chooseItem("playstation 4 slim black");

        ItemPage itemPlaystation = new ItemPage();
        itemPlaystation.getPriceAndSave("playstation without garantee price");
        itemPlaystation.chooseGuarantee(2);
        itemPlaystation.getPriceAndSave("playstation with garantee price");
        itemPlaystation.addToCart();

        SearchPage searchDetroit = new SearchPage();
        searchDetroit.waitElement(searchDetroit.searchTextInput);
        searchDetroit.searhItems("Detroit");

        ItemPage itemDetroit = new ItemPage();
        itemDetroit.getPriceAndSave("detroit price");
        itemDetroit.addToCart();

        itemDetroit.checkTotalPrice("playstation with garantee price","detroit price");


        BasketPage basketPage = new BasketPage();
        basketPage.gotoBasket();
        basketPage.checkGarantee(2)
                .checkPriceAndSum()
                .deleteItem(2)
                .addItem(1)
                .addItem(1)
                .restoreRemove();

        Thread.sleep(10000);


    }

    @After
    public void TearDown(){
       Init.getDriver().quit();
    }
}
