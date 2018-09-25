package app.bean;

import org.messtin.framework.core.annotation.Bean;

@Bean("AirCondition")
public class AirCondition {

    private int price = 1000; // default price
    private String name = "GeLi"; // defalut name

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AirCondition{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
