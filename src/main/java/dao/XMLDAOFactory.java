package dao;

import store.Car;
import store.Order;
import store.User;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tanya on 3/9/14.
 */
@XmlRootElement(namespace = "store")
@XmlAccessorType(XmlAccessType.FIELD)

public class XMLDAOFactory extends DAOFactory {
    @XmlTransient
    public static XMLBinder xmlBinder;
    @XmlTransient
    public Set<User> users;
    @XmlTransient
    public List<Car> cars;
    @XmlTransient
    public User curUser;

    @XmlElementWrapper(name = "ordersList")
    @XmlElement(name = "order")
    public List<Order> orders;



    public XMLDAOFactory() {
        this.xmlBinder = DAOSingleton.getXmlBinderSingleton();
        XMLBinder.setDirPath(System.getProperty("user.home"));

        users = new HashSet<User>();
        cars = new ArrayList<Car>();
        orders = new ArrayList<Order>();

        XMLDAOFactory xmldaoFactory = null;
        xmldaoFactory = (XMLDAOFactory) xmlBinder.getDataFromXML(this.getClass());

        if (xmldaoFactory != null){
            orders = xmldaoFactory.getOrders();
            if (orders != null){
                users = getUsersFromOrders(orders);
                cars = getCarsFromOrders(orders);
            }
        }

    }

    public List<Order> getOrders() {
        return orders;
    }

    public Set<User> getUsers() {
        return users;
    }

    public List<Car> getCars() {
        return cars;
    }

    public User getCurUser() {
        return curUser;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setCurUser(User curUser) {
        this.curUser = curUser;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOXML(this);
    }

    @Override
    public CarDAO getCarDAO() {
        return new CarDAOXML(this);
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new OrderDAOXML(this);
    }

    @Override
    public void close() {
        xmlBinder.setDataToXML(this);
    }

    private Set<User> getUsersFromOrders(List<Order> orders){
        users = new HashSet<User>();
        User user = null;
        Car car = null;

        for (Order o: orders){
            user = o.getUser();
            if (!users.contains(user)){
                users.add(user);
            }
        }

        return users;
    }

    private List<Car> getCarsFromOrders(List<Order> orders){
        cars = new ArrayList<Car>();
        User user = null;
        Car car = null;

        for (Order o: orders){
            cars.add(o.getCar());
        }

        return cars;
    }


}
