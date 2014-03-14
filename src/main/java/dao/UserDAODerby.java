package dao;

import store.Order;
import store.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
public class UserDAODerby implements UserDAO {
    private static EntityManager em;
    private User curUser;

    public UserDAODerby(EntityManager em) {
        this.em = em;
    }

    @Override
    public int insertUser(String login, String password, String name) {
        User u = findUserByLogin(login);
        if (u != null){
            if (password.equals(u.getPassword())){
                curUser = u;
                return 0;//This user has been already saved
            }
            else{
                curUser = null;
                return -1;//Password is wrong
            }
        }

        User newUser = new User(login, password, name);
        int k = 0;
        try {
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
            curUser = newUser;
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
    public boolean deleteUser(User user) {
        List<Order> userOrders = null;
        Order o = null;

        Query query = em.createQuery("SELECT o FROM Order o " +
                "WHERE o.user = :parUser");
        try{
            query.setParameter("parUser", user);
            userOrders = query.getResultList();
        }
        finally{
        }

        if (userOrders.size() == 0){
            try {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
            }
            catch(Exception e){
            }
            finally{

            }
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        List<User> listU = null;
        User u = null;

        Query query = em.createQuery("SELECT u FROM User u " +
                "WHERE u.login LIKE :stLogin");
        try{
            query.setParameter("stLogin", login + "%");
            listU = query.getResultList();
        }
        finally{
        }
        if (listU != null) {
            if (listU.size() >0 ){
                u = listU.get(0);
            }
        }
        return u;
    }

    @Override
    public boolean updateUser(User user) {
        boolean k = false;
        try {
            em.getTransaction().begin();
            em.merge(user);
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
    public boolean existsUser(User user) {
        User u = null;
        u = em.find(User.class, user);
        if (u != null){
            return true;
        }
        return false;
    }

    @Override
    public User getCurUser() {
        return curUser;
    }

}
