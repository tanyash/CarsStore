package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by tanya on 3/9/14.
 */
public class DAOSingleton {
    static EntityManager em = null;
    final static String UNIT_NAME = "CarsStorePersistenceUnit";

    static XMLBinder xmlBinder = null;

    private DAOSingleton(){

    }

    public static EntityManager getEntityManagerSingleton(){
        if (em == null){
            EntityManagerFactory factory;
            factory = Persistence.createEntityManagerFactory(UNIT_NAME);
            em = factory.createEntityManager();
        }
        return em;
    }

    public static XMLBinder getXmlBinderSingleton(){
        if (xmlBinder == null){
            xmlBinder = new XMLBinder();
        }
        return xmlBinder;
    }

}
