package ru.aplana.objpajes.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Поиск среди более 100 000 товаров']")
    public WebElement searchTextInput;

    @FindBy(xpath = "/html/body/header/nav/div/form/div/div[2]/span[2]")
    public WebElement searchButton;

    public SearchPage() {
        super();
    }

    public SearchPage searhItems(String name){
        searchTextInput.clear();
        searchTextInput.sendKeys(name);
        searchButton.click();
        return this;
    }
}
