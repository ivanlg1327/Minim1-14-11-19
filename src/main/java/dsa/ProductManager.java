package dsa;

import dsa.models.Product;

import java.util.List;

public interface ProductManager {
    public void addUser(String id, String name);
    public int size();
    public void clear();
    public Product updateProduct(Product p);//put
    public void addProduct(String name, String description, double price);//post
    public Product getProduct(String p);//get
    public void deleteProduct(String name);//delete
    public List<Product> listPrices();//Listado de productos ordenado (ascendentemente) por precio
   // public void ped(Order order);//Realizar un pedido (que puede estar formado por diferentes productos y en diferentes cantidades) por parte de un usuario identificado
  //  public Order listActive();//Servir un pedido. Los servicios se realizan en orden de llegadas
    //public List<Order> listUser(String idUser);// Listado de pedidos de un usuario que ya hayan sido realizados
    public List<Product> listSales();// Listado de productos ordenado (descendentemente) por número de ventas
}
