package model;

import java.io.Serializable;

/**
 * Created by rabiu on 24/10/2017.
 */

public class Currency implements Serializable{

    private String name;
    private String  price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
