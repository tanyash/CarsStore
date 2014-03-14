package dao;

/**
 * Created by tanya on 3/9/14.
 */
public abstract class DAOFactory {
    public static final int DERBY_VALUE = 1;
    public static final int XML_VALUE = 2;

    public abstract UserDAO getUserDAO();
    public abstract CarDAO getCarDAO();
    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory(int whichFactory){
        switch(whichFactory){
            case DERBY_VALUE:
                return new DerbyDAOFactory();
            case XML_VALUE:
                return new XMLDAOFactory();
            default:
                return null;
        }
    }
    public abstract void close();
}


