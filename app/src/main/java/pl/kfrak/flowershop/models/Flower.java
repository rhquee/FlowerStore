package pl.kfrak.flowershop.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RENT on 2017-07-12.
 */

public class Flower {

    private String categroy;
    private double price;
    private String instructions;
    private String photo;
    private String name;
    @SerializedName("productId")
    private long id;

    public Flower() {
    }

    public String getCategroy() {
        return categroy;
    }

    public void setCategroy(String categroy) {
        this.categroy = categroy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
