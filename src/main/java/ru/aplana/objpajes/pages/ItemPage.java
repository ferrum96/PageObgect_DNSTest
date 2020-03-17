package ru.aplana.objpajes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.aplana.objpajes.Product;
import ru.aplana.objpajes.TestData;

import java.util.ArrayList;
import java.util.List;

public class ItemPage extends BasePage {

    public static List<Product> products = new ArrayList<>();

    @FindBy(xpath = "//*[contains(@class,'current-price')]")
    WebElement price;

    @FindBy(xpath = "//*[@class='page-title price-item-title']")
    WebElement name;

    @FindBy(xpath = "//*[contains(@class,'btn-cart')]")
    WebElement addToBasket;

    @FindBy(xpath = "//*[@class='form-control select']")
    WebElement selectForm;

    public ItemPage() {
        super();
    }

    public float getPriceAndSave(String key){
        waitElement(price);
        float value = Float.parseFloat(price.getText().replaceAll(" ", ""));
        TestData.putItem(key, value);
        return value;
    }

    public ItemPage chooseGuarantee(int years){
        switch (years){
            case 1:
                new Select(selectForm).selectByVisibleText("1 год");
                break;
            case 2:
                new Select(selectForm).selectByVisibleText("2 года");
                break;
            default:
                System.out.println("Нет такой гарантии");
                break;
        }
        return this;
    }



    public ItemPage addToCart(){
        waitElement(addToBasket);
        products.add(new Product(name.getText(),price.getText(), isElementExists(By.xpath("//*[@class='form-control select']"))));
        addToBasket.click();
        return this;
    }
}
