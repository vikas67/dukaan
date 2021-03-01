package com.example.mydukaan.modal;

public class OrderHeaderList {
    int id;
    String name;


    public OrderHeaderList(int id, String name) {
        this.id = id;
        this.name = name;
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


    @Override
    public String toString() {
        return "OrderHeaderList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
