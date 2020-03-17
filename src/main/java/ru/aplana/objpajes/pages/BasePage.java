package ru.aplana.objpajes.pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.objpajes.Init;
import ru.aplana.objpajes.TestData;


public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@class='cart-link__price']")
    WebElement totalPrice;

    @FindBy(xpath = "//*[@class='cart-link__icon']")
    WebElement cartButton;

    public BasePage(){
        this.driver = Init.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 20);
    }

    public float getTotalPrice(){
        waitElement(totalPrice);
        String value = totalPrice.getText();
        return Float.parseFloat(value.replaceAll(" ", ""));
    }

    public WebElement waitElement(By locator){
        wait = new WebDriverWait(driver,10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitClickableElement(WebElement element){
        wait = new WebDriverWait(driver,10);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElement(WebElement element){
        wait = new WebDriverWait(driver,10);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void checkTotalPrice(String ... keys) throws InterruptedException {
        waitClickableElement(totalPrice);
        float expectedTotalSum = 0;
        for (String key: keys){
            expectedTotalSum+=(float)TestData.getItem(key);
        }
        Thread.sleep(1000);
        Assert.assertEquals("Сумма не соотвествует ожидаемой",expectedTotalSum, getTotalPrice(), 0.01);
    }

    public void checkTotalPrice(){
        waitElement(totalPrice);
        float expectedTotalSum = 0;
        for (String key: TestData.map.keySet()){
            expectedTotalSum+=(float)TestData.getItem(key);
        }
        Assert.assertEquals("Сумма не соотвествует ожидаемой",expectedTotalSum, getTotalPrice(), 0.01);
    }

    public void gotoBasket(){
        waitClickableElement(cartButton).click();
    }

    public boolean isElementExists(By locator)
    {
        try
        {
            Init.getDriver().findElement(locator);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

}
