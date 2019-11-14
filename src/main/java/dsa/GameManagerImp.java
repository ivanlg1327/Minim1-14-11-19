package dsa;

import dsa.models.Order;
import dsa.models.User;
import dsa.models.Object;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;


public class GameManagerImp implements GameManager {
    private Logger log = LogManager.getLogger(GameManagerImp.class);
    private List<Object> objectList;
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
        this.objectList = new LinkedList<Object>();
        this.users = new HashMap<>();
        this.orderQueue= new LinkedList<>();
    }
    @Override
    public int size(){
        int aux=this.objectList.size();
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
    @Override
    public void addProduct(String name, String description,double price ){//cambiar
        Object temp = new Object(name, price,0, description);
        this.objectList.add(temp);//entra en bucle entre esta linea y el producto
        log.info(this.objectList);
    }
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
    public List<Object> listPrices()//seguramente desaparecera
    {
        Collections.sort(this.objectList, new Comparator<Object>() {
            @Override
            public int compare(Object product, Object t1) {
                return ((int)(product.price-t1.price));
            }
        });
        log.info(this.objectList);
        return this.objectList;
    }

    @Override
    public void ped(Order order) {
        this.orderQueue.add(order);
    }

    @Override
    public Order listActive() {
        Order aux=this.orderQueue.poll();
        log.info(aux);
        User user = users.get(aux.getUser());
        int q;
        String p;
        Object product;
        for (Order.LP lp: aux.lps()) {
            q = lp.q;
            p = lp.product;
            product = this.getProduct(p);
            product.numVendes(q);
        }
        if (user!=null) {
            user.addHistorical(aux);
        }
        else log.error("user no exiteix"+user);

        return aux;
    }
    @Override
    public Object getProduct(String p) {
        for (Object object : this.objectList) {
            if (object.name.equals(p)) return object;
        }
        return null;
    }
    @Override
    public User getUser(String p){
        return users.get(p);
    }
    @Override
    public List<Object> getUserBag(String p){
        User aux=users.get(p);
        log.info(aux.getBag());
        return aux.getBag();

    }
    @Override
    public void addUserBag(String userId, Object object){
        User aux=users.get(userId);
        aux.addBag(object);
        log.info(aux.getBag());
    }

    @Override
    public void deleteProduct(String name){
        Object t = this.getProduct(name);
        if (t==null) {
            log.warn("not found " + t);
        }
        else log.info(t+" deleted ");

        this.objectList.remove(t);
    }
    @Override
    public void clear(){
        instance = null;
        this.objectList.clear();
        this.orderQueue.clear();
        users.clear();
    }


    @Override
    public List<Order> listUser(String idUser) {
        return null;
    }

    @Override
    public HashMap<String, User> listUsers() {
        return this.users;
    }

}
