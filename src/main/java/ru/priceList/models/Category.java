package ru.priceList.models;

import javax.persistence.*;

/**
 *  ласс описывающий категорию товара в базе данных
 *@author pe.kolomnikov
 */
@Entity
@Table(name="cat")
public class Category extends Base {

    @Column(name = "name")
    private String name;

    public Category(int id) {
        super(id);
    }
    public Category() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
