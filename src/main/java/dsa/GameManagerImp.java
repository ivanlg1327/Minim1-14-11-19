package dsa;

import dsa.models.Order;
import dsa.models.User;
import dsa.models.Objeto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;


public class GameManagerImp implements GameManager {
    private Logger log = LogManager.getLogger(GameManagerImp.class);
    private List<Objeto> objetoList;
    private Queue<Order> orderQueue ;
    private HashMap<String, User> users;

    private static GameManagerImp instance = new GameManagerImp();

    public static GameManagerImp getInstance()
    {
        if(instance==null)
        {
            instance = new GameManagerImp();
        }
        return instance;
    }

    public GameManagerImp() {// List<dsa.models.Product> productList, HashMap<String, dsa.models.User> users,Queue<dsa.models.Order> orderQueue) {
        this.objetoList = new LinkedList<Objeto>();
        this.users = new HashMap<>();
        this.orderQueue= new LinkedList<>();
    }
    @Override
    public int size(){
        int aux=this.objetoList.size();
        return aux;
    }
    @Override
    public void addUser(String id, String name, String surname1, String surname2){
        User result=users.get(id);
        log.info(result);//debe aparecer vacio si no esta en la lista
        User aux;
        if (result==null)
        {
            aux=new User(name, id, surname1,surname2);
            users.put(id,aux);
            log.info("The user "+ aux.name+ " "+aux.surname1+" "+ aux.surname2+" with id '"+aux.id+ "' has been added to the system");
        }
        else
        {
            log.warn("The user " + name +" "+ surname1+ " " +surname2+ " already exists" );
        }
    }
   /* @Override
    public void addProduct(String name, String description,double price ){//cambiar
        Objeto temp = new Objeto(name, price,0, description);
        this.objetoList.add(temp);//entra en bucle entre esta linea y el producto
        log.info(this.objetoList);
    }*/
    @Override
    public User updateUser(User p){
        User t = this.users.get(p.getId());
        if (t!=null) {
            log.info(p+" rebut!!!! ");

            t.setName(p.getName());
            t.setSurname1(p.getSurname1());
            t.setSurname2(p.getSurname2());

            log.info(t+" updated ");
        }
        else {
            log.warn("not found "+p);
        }

        return t;
    }

    @Override
    public Objeto getObjeto(String p) {
        for (Objeto objeto : this.objetoList) {
            if (objeto.name.equals(p)) return objeto;
        }
        return null;
    }
    @Override
    public User getUser(String p){
        return users.get(p);
    }
    @Override
    public List<Objeto> getUserBag(String p){
        User aux=users.get(p);
        log.info(aux.getBag());
        return aux.getBag();

    }
    @Override
    public void addUserBag(String userId, Objeto objeto){
        User aux=users.get(userId);
        aux.addBag(objeto);
        log.info(aux.getBag());
    }

    @Override
    public void clear(){
        instance = null;
        this.objetoList.clear();
        this.orderQueue.clear();
        users.clear();
    }


    @Override
    public List<User> listUser() {
        log.info((List<User>) this.listUsers().values());
        return (List<User>) this.listUsers().values();
    }

    @Override
    public HashMap<String, User> listUsers() {
        return this.users;
    }

}
