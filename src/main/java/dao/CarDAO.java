package dao;

import store.Car;

import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
public interface CarDAO {
    public Car insertCar(String brand, String model, int year);
    public boolean deleteCar(Car car);

}
