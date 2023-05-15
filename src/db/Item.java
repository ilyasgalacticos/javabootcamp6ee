package db;

public class Item {

    private int id;
    private String name;
    private int price;
    private Country country;

    public Item(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Item() {
        this.name = "No Name";
        this.price = 0;
    }

    public Item(int id, String name, int price, Country country) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.country = country;
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getData(){
        return this.id + " " + this.name + " " + this.price + " " + this.country;
    }
}
