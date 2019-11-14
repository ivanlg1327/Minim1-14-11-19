package dsa.models;

public class Objeto implements Comparable<Objeto>{
    public String name;
    public double price;// este se borra
    public int count;
    public String description;

    public Objeto() {//necesario para que la api rest funcione

    }

    public Objeto(String name, int count, String description) {
        this.name = name;
        //this.price = price;
        this.count = count;
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(Objeto e2) {
        return this.name.compareTo(e2.name);
    }
    public String toString() {
        return this.name;
    }

    public void numVendes(int q) {
        this.count+= q;
    }
}
