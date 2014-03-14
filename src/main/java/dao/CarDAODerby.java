package dao;

import store.Car;

import javax.persistence.EntityManager;

/**
 * Created by tanya on 3/9/14.
 */
public class CarDAODerby implements CarDAO {
    private static EntityManager em;

    public CarDAODerby(EntityManager em) {
        this.em = em;
    }

    @Override
    public Car insertCar(String brand, String model, int year) {
        Car car;
        car = new Car(brand, model, year);
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        }
        catch(Exception e){
            car = null;
        }
        finally{

        }
        return car;
    }

    @Override
    public boolean deleteCar(Car car) {
        boolean k = false;
        try {
            em.getTransaction().begin();
            em.remove(car);
            em.getTransaction().commit();
            k = true;
        }
        catch(Exception e){
        }
        finally{

        }
        return k;
    }

}
