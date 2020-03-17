package ru.aplana.objpajes.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class ResultPage extends BasePage{

    @FindBy(xpath = "//*[contains(@class,'catalog-product__main')]")
    List<WebElement> products;

    public static final By productTitle = By.xpath("//*[contains(@class,'title-link')]/a");

    public ResultPage() {
        super();
    }

    public void chooseItem(final String itemTitle){
        wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
        WebElement item = products.stream()
                .map(element->element.findElement(productTitle))
                .filter(element->element.getText().toLowerCase().contains(itemTitle.toLowerCase()))
                .findFirst().orElseThrow(()->new RuntimeException("no element with title " + itemTitle));
        item.click();
    }
}
