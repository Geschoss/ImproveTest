package ru.priceList.models;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Base() {
    }

    public Base(int id){
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
