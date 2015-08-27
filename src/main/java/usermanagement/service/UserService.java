package usermanagement.service;

import java.util.Date;
import java.util.List;
import usermanagement.domain.User;


public interface UserService {

    void addUser(User user);
    void deleteUser(Integer id);
    void editUser(User user);
    User getUserById(Integer id);
    List<User> isUsernameDuplicated(String username);
    List<User> getUserByGroup(Integer id);
    List<User> getAllUsers();
    List<User> getUsersByFirstName(String str);
    List<User> getUsersByLastName(String str);
    List<User> getUsersByEmail(String str);
    List<User> getUsersByBirthday(Date date);
}
