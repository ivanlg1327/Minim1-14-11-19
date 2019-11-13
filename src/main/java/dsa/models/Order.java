package dsa.models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Order {
    private double cost=0;
    private String name;
    private Date date;
    private List<LP> lps;

    public Order( String name , Date date) {
        this.name=name;
        this.date=date;
        this.lps = new LinkedList<LP>();
    }

    public List<LP> lps() {
        return this.lps;
    }

    public double getCost() {//creo que ahora mismo no tiene utilidad
        return cost;
    }

/*    public double count(List <dsa.models.Product> productList)
    {
        double aux=0;
        for (dsa.models.Product temp : this.productList)
            aux=aux+temp.price;
        return aux;
    } */

    public String getUser(){
        return this.name;
    }
    public void addUSer(String id){

    }

    public void addLP(int num, String name){
        this.lps.add(new LP(num, name));

    }
    public String toString() {
        return this.name;
    }

    public class LP {
        public int q;
        public String product;

        public LP(int q, String product) {
            this.q = q;
            this.product =product;
        }
    }
}
