package dao;

import store.User;

import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
public interface UserDAO {
    public int insertUser(String login, String password, String name);
    public boolean deleteUser(User user);
    public User findUserByLogin(String login);
    public boolean updateUser(User user);
    public boolean existsUser(User user);
    public User getCurUser();
}
