package com.example.brookwood;

public class ModelCart {

    String id,name,cost,image;

    public ModelCart(){

    }

    public ModelCart(String id, String name, String cost, String image) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
