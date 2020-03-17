package ru.aplana.objpajes;


public class Product {

    private String name;
    private String price;
    private boolean  hasGarranty;

    public Product(String name, String price, boolean hasGarranty){
        this.name = name;
        this.price = price;
        this.hasGarranty = hasGarranty;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public boolean getHasGarranty() {
        return hasGarranty;
    }

    @Override
    public String toString() {
        return getName() + " " + getPrice() + " " + getHasGarranty();
    }
}
