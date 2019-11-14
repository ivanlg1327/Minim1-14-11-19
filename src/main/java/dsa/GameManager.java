package dsa;

import dsa.models.Object;
import dsa.models.User;

import dsa.models.Order;

import java.util.HashMap;
import java.util.List;

public interface GameManager {
    public void addUser(String id, String name, String surname1, String surname2);
    public int size();
    public void clear();
    public User getUser(String p);
    public List<Object> getUserBag(String p);
    public void addUserBag(String userId, Object object);
    public User updateUser(User p);//put
    public void addProduct(String name, String description, double price);//post
    public Object getProduct(String p);//get
    public void deleteProduct(String name);//delete
    public List<Object> listPrices();//Listado de productos ordenado (ascendentemente) por precio
    public void ped(Order order);//Realizar un pedido (que puede estar formado por diferentes productos y en diferentes cantidades) por parte de un usuario identificado
    public Order listActive();//Servir un pedido. Los servicios se realizan en orden de llegadas
    public List<Order> listUser(String idUser);// Listado de pedidos de un usuario que ya hayan sido realizados
    public HashMap<String, User> listUsers();// Listado de productos ordenado (descendentemente) por número de ventas
}
