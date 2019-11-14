import dsa.GameManager;
import dsa.GameManagerImp;
import dsa.models.Objeto;
import dsa.models.Order;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GameManagerTest {
    private GameManager pm = null;

    @Before
    public void setUp() {
        this.pm = GameManagerImp.getInstance();
        this.pm.addUser("11111", "Toni", "Oller", "Arcas");
        this.pm.addUser("22222", "Ivan", "Luque", "Garcia");


        Objeto p= new Objeto("Espasa", 2,"afilada");
        this.pm.addUserBag("11111", p);
        this.pm.size();
    }

    @After
    public void tearDown() {
        this.pm.clear();
    }

    @Test
    public void testing() {
        this.pm.listUser();//algo falla mirar despues


    }

    @Test
    public void verUsuario() {
        Assert.assertEquals("Ver usuario","Ivan",this.pm.getUser("22222").name);
        Assert.assertEquals("Ver usuario",1,this.pm.getUser("11111").getBag().size());

    }

  /*  @Test
    public void servirPedido() {
        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Order p = new Order("22222", date); //, users.get("11111"));
        p.addLP(1, "COCA-ZERO");
        p.addLP(3, "BOCATA");
        this.pm.ped(p);

        //para asegurar que al empezar esta todo a cero
        Assert.assertEquals("ServirPEdido", 0, this.pm.getProduct("BOCATA").getCount());
        Assert.assertEquals("ServirPEdido", 0, this.pm.getProduct("COCA-ZERO").getCount());
        //Assert.assertEquals("ServirPEdido", 2, pm.numOrders());


        Order p1 =this.pm.listActive();

        Assert.assertEquals("ServirPEdido", "11111", p1.getUser());
        Assert.assertEquals("ServirPEdido", 2, p1.lps().get(0).q);
        Assert.assertEquals("ServirPEdido", "COCA-ZERO", p1.lps().get(0).product);

        Assert.assertEquals("ServirPEdido", 1, p1.lps().get(1).q);
        Assert.assertEquals("ServirPEdido", "BOCATA", p1.lps().get(1).product);

        Assert.assertEquals("ServirPEdido", 1, this.pm.getProduct("BOCATA").getCount());
        Assert.assertEquals("ServirPEdido", 2, this.pm.getProduct("COCA-ZERO").getCount());

        //comprobar despues de primera comanda


        p1 = this.pm.listActive();

        Assert.assertEquals("ServirPEdido", 4, this.pm.getProduct("BOCATA").getCount());
        Assert.assertEquals("ServirPEdido", 3, this.pm.getProduct("COCA-ZERO").getCount());
        //comprobar despues de segunda comanda
    }*/
}
