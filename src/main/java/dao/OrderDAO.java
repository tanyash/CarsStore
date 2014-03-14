package dao;

import store.Car;
import store.Order;
import store.User;

import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
public interface OrderDAO {
    public int insertOrder(User user, Car car);
    public boolean deleteOrder(Order order);
    public boolean deleteAllUserOrders(User user);
    public List<Order> findAllUserOrders(User user);
    public boolean existsOrder(User user, Car car);
}
