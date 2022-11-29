package com.example.brookwood;

public class item {

    String head,cost,image;

    public item() {
        }

    public item(String head, String cost, String image) {
        this.head = head;
        this.cost = cost;
        this.image = image;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
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
