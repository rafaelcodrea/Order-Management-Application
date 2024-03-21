package model;

public class Product {
    private int id;
    private String name;
    private int price;
    private int supply;

    public Product(int id, String name, int price, int supply) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.supply = supply;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }
}
