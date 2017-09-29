package by.bstu.fit.mystore20.entity;

import java.io.Serializable;

/**
 * Created by BNT on 9/25/2017.
 */

public class Item implements Serializable{
    private long id;
    private String name;
    private float price;
    private String pathToPhoto;

    public Item() {
    }

    public Item(long id, String name, float price, String pathToPhoto) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pathToPhoto = pathToPhoto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPathToPhoto(String pathToPhoto) {
        this.pathToPhoto = pathToPhoto;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", pathToPhoto='" + pathToPhoto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (Float.compare(item.price, price) != 0) return false;
        if (!name.equals(item.name)) return false;
        return pathToPhoto != null ? pathToPhoto.equals(item.pathToPhoto) : item.pathToPhoto == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (pathToPhoto != null ? pathToPhoto.hashCode() : 0);
        return result;
    }
}
