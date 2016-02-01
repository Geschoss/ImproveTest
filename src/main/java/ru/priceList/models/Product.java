package ru.priceList.models;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Класс описывающий товар в базе данных
 * @author pe.kolomnikov
 */
@Entity
@Table(name="prod")
public class Product extends Base{

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @JoinColumn(name="cat_id",referencedColumnName = "id")
    private Category category;

    public Product() {
        super();
    }


    public Product( int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product [" +
                "name=" + name +
                ", price=" + price +
                ", category=" + category +
                ']';
    }
}
