/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Vinh Dev
 */
public class Product {

    private int id;
    private String name;
    private String image;
    private double price;
    private String title;
    private String description;
    private String model;
    private String color;
    private String delivery;
    private String image2;
    private String image3;
    private String image4;

    public Product(int id, String name, String image, double price, String title, String description, String model,
            String color, String delivery, String image2, String image3, String image4) {

        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.model = model;
        this.color = color;
        this.delivery = delivery;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", title=" + title
                + ", description=" + description + ", model=" + model + ", color=" + color + ", delivery=" + delivery
                + ", image2=" + image2 + ", image3=" + image3 + ", image4=" + image4 + "]";
    }

    public Product(int id, String name, String image, double price, String title) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
    }

}
