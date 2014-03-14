package dao;

import store.Car;
import store.Order;
import store.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
public class OrderDAODerby implements OrderDAO {
    private static EntityManager em;

    public OrderDAODerby(EntityManager em) {
        this.em = em;
    }
    @Override
    public int insertOrder(User user, Car car) {
        int k = 0;
        Order order = new Order(user, car);
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
            k = 1;
        }
        catch(Exception e){
            k = -1;
        }
        finally{

        }
        return k;
    }

    @Override
    public boolean deleteOrder(Order order) {
        boolean k = false;
        try {
            em.getTransaction().begin();
            em.remove(order.getCar());
            em.remove(order);
            if (findAllUserOrders(order.getUser()).size() == 0){
                em.remove(order.getUser());
            }
            em.getTransaction().commit();
            k = true;
        }
        catch(Exception e){
        }
        finally{

        }
        return k;
    }

    @Override
    public boolean deleteAllUserOrders(User user) {
        boolean k = false;

        List<Order> ordersU = findAllUserOrders(user);
        for (Order o: ordersU){
            deleteOrder(o);
        }
        k = true;

        return k;
    }

    @Override
    public List<Order> findAllUserOrders(User user) {
        List<Order> userOrders = null;
        Order o = null;

        Query query = em.createQuery("SELECT o FROM Order o " +
                "WHERE o.user.id = :parUser");
        try{
            query.setParameter("parUser", user.getId());
            userOrders = query.getResultList();
        }
        finally{
        }
        return userOrders;
    }

    @Override
    public boolean existsOrder(User user, Car car) {
        return false;
    }
}
