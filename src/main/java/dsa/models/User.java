package dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    public String name;
    public String surname1;
    public String surname2;
    public String id;
    List<Objeto> bag;

    public User(String name, String id,  String surname1,  String surname2) {
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.bag = new LinkedList<>();
        this.id= id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public List<Objeto> getBag() {
        return bag;
    }

    public void setBag(List<Objeto> bag) {
        this.bag = bag;
    }

    public void addBag(Objeto objeto)
    {
        this.bag.add(objeto);
    }
    public int compareTo(User e2) {
        return this.name.compareTo(e2.name);
    }
    public String toString() {
        return this.name;
    }
}
