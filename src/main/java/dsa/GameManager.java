package dsa;

import dsa.models.Objeto;
import dsa.models.User;

import java.util.HashMap;
import java.util.List;

public interface GameManager {
    public void addUser(String id, String name, String surname1, String surname2);
    public int size();
    public void clear();
    public User getUser(String p);
    public List<Objeto> getUserBag(String p);
    public void addUserBag(String userId, Objeto objeto);
    public User updateUser(User p);//put

    public Objeto getObjeto(String p);//get

    public List<User> listUser();// Listado de pedidos de un usuario que ya hayan sido realizados
    public HashMap<String, User> hashUsers();// Listado de productos ordenado (descendentemente) por número de ventas
}
