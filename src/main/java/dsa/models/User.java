package dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    public String name;
    public String id;
    List<Order> historical;

    public User(String name, String id) {
        this.name = name;
        this.historical = new LinkedList<>();
        this.id= id;
    }

    public void addHistorical(Order order)
    {
        this.historical.add(order);
    }
    public String toString() {
        return this.name;
    }
}
