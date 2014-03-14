package dao;

import store.Car;
import store.Order;

import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
public class CarDAOXML implements CarDAO {
    XMLDAOFactory xmldaoFactory;

    public CarDAOXML(XMLDAOFactory xmldaoFactory) {
        this.xmldaoFactory = xmldaoFactory;
    }


    @Override
    public Car insertCar(String brand, String model, int year) {
        Car car;
        car = new Car(brand, model, year);
        xmldaoFactory.getCars().add(car);
        return car;
    }

    @Override
    public boolean deleteCar(Car car) {
        //Check for orders with this car
        for (Order o: xmldaoFactory.getOrders()){
            if (o.getCar().equals(car)){
                return false;
            }
        }

        for (Car c: xmldaoFactory.getCars()){
            if (c.equals(car)){
                xmldaoFactory.getCars().remove(c);
                return true;
            }
        }

        return false;
    }

}
