package dao;

import javax.persistence.EntityManager;

/**
 * Created by tanya on 3/9/14.
 */
public class DerbyDAOFactory extends DAOFactory {
    public static EntityManager em;

    public DerbyDAOFactory() {
        em = DAOSingleton.getEntityManagerSingleton();
    }

    public UserDAO getUserDAO(){
        return new UserDAODerby(em);
    }

    public CarDAO getCarDAO(){
        return new CarDAODerby(em);
    }

    public OrderDAO getOrderDAO(){
        return new OrderDAODerby(em);
    }

    public void close(){
        em.close();
    }
}
