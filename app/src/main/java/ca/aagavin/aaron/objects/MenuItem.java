package ca.aagavin.aaron.objects;

/*
 * Created by Aaron
 */

public class MenuItem {

    private String name;

    private double price;

    private int image;

    private String description;

    private String restaurantName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public MenuItem(String name, double price, int image, String description, String restaurantName) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.restaurantName = restaurantName;
    }
}
