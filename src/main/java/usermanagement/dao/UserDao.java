package usermanagement.dao;

import java.util.Date;
import java.util.List;
import usermanagement.domain.User;

public interface UserDao {

    void addUser(User user);
    void deleteUser(Integer id);
    void editUser(User user);
    User getUserById(Integer id);
    List<User> checkUsername(String username);
    List<User> getAllUsers();
    List<User> getUserByGroup(Integer id);
    List<User> getUsersByFirstName(String str);
    List<User> getUsersByLastName(String str);
    List<User> getUsersByEmail(String str);
    List<User> getUsersByBirthday(Date date);


}
