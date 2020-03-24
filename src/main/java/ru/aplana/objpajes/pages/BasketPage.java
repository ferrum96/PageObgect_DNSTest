package ru.aplana.objpajes.pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.aplana.objpajes.Init;
import ru.aplana.objpajes.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BasketPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'Удалить')]")
    List<WebElement> deleteBtn;

    @FindBy(xpath = "//*[contains(@class,'button_plus')]")
    List<WebElement> addBtn;

    @FindBy(xpath = "//*[@class='price__current']")
    List<WebElement> prices;

    @FindBy(xpath = "//*[@class='cart-items__product-name']/a")
    List<WebElement> names;

    public BasketPage checkGarantee(int years) {
        waitElement(Init.getDriver().findElement(By.xpath("//*[@class='list-applied-product-services__item']/span")));
        WebElement garantee = Init.getDriver().findElement(By.xpath("//*[@class='list-applied-product-services__item']/span"));
        switch (years){
            case 1:
                Assert.assertEquals("Продленная гарантия на 12 мес.",true, garantee.getText().contains("12 мес"));
                break;
            case 2:
                Assert.assertEquals("Продленная гарантия на 24 мес.",true, garantee.getText().contains("24 мес"));
                break;
        }
        return this;
    }

    public BasketPage deleteItem(int position) throws InterruptedException {
        //Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn.get(deleteBtn.size()-1)));
        WebElement delete = deleteBtn.stream()
                .skip(position - 1)
                .findFirst().orElseThrow(() -> new RuntimeException("нет такой позиции"));
        delete.click();
        return this;
    }

    public BasketPage addItem(int position) throws InterruptedException {
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(addBtn.get(0)));
        WebElement add = addBtn.stream()
                .skip(position - 1)
                .findFirst().orElseThrow(() -> new RuntimeException("нет такой позиции"));
        add.click();
        return this;
    }

    public BasketPage restoreRemove(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='restore-last-removed']")));
        Init.getDriver().findElement(By.xpath("//*[@class='restore-last-removed']")).click();
        return this;
    }


    float sum = 0;

    public BasketPage checkPriceAndSum() throws InterruptedException {
        sum = 0;
        WebElement garantee = Init.getDriver().findElement(By.xpath("//*[@class='list-applied-product-services__item']/span"));
        Thread.sleep(1000);
        for (int i = 0; i < prices.size() - 1; i++) {
            if (ItemPage.products.get(i).getHasGarranty() == true && garantee.getText().contains("Продленная гарантия на 12 мес. (+3 640)"))
                Assert.assertEquals(Float.parseFloat(ItemPage.products.get(i).getPrice().replaceAll(" ", "")),(Float.parseFloat(prices.get(i).getText().replaceAll(" ", ""))) + 3640f ,0.01);
            else if (ItemPage.products.get(i).getHasGarranty() == true && garantee.getText().contains("Продленная гарантия на 24 мес. (+5 040)"))
                Assert.assertEquals(Float.parseFloat(ItemPage.products.get(i).getPrice().replaceAll(" ", "")), (Float.parseFloat(prices.get(i).getText().replaceAll(" ", ""))) + 5040f, 0.01);
            else if (ItemPage.products.get(i).getHasGarranty() == true && garantee.getText().contains("Продленная гарантия на 24 мес. (+14 040)"))
                Assert.assertEquals(Float.parseFloat(ItemPage.products.get(i).getPrice().replaceAll(" ", "")), (Float.parseFloat(prices.get(i).getText().replaceAll(" ", ""))) + 14040f, 0.01);
            else
                Assert.assertEquals(Float.parseFloat(ItemPage.products.get(i).getPrice().replaceAll(" ", "")), (Float.parseFloat(prices.get(i).getText().replaceAll(" ", ""))), 0.01);
            sum += Float.parseFloat(ItemPage.products.get(i).getPrice().replaceAll(" ", ""));
        }
        Assert.assertEquals(sum,Float.parseFloat(prices.get(prices.size() - 1).getText().replaceAll(" ", "")),0.01);

        return this;
    }

    public BasketPage checkItem(String title){
        for (WebElement name : names) {
            if (!name.getText().contains(title.toLowerCase()))
                Assert.assertTrue(!name.getText().contains(title));
        }
        Assert.assertEquals(sum - Float.parseFloat(ItemPage.products.get(ItemPage.products.size() - 1 ).getPrice()), Float.parseFloat(prices.get(prices.size() - 1).getText().replaceAll(" ", "")),0.01);
        return this;
    }
}
