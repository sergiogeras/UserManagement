package usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usermanagement.dao.UserDao;
import usermanagement.service.UserService;
import usermanagement.domain.User;
import java.util.Date;
import java.util.List;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> isUsernameDuplicated(String username) {
        return userDao.checkUsername(username);
    }

    @Override
    public List<User> getUserByGroup(Integer id) {
        return userDao.getUserByGroup(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getUsersByFirstName(String str) {
        return userDao.getUsersByFirstName(str);
    }

    @Override
    public List<User> getUsersByLastName(String str) {
        return userDao.getUsersByLastName(str);
    }

    @Override
    public List<User> getUsersByEmail(String str) {
        return userDao.getUsersByEmail(str);
    }

    @Override
    public List<User> getUsersByBirthday(Date date) {
        return userDao.getUsersByBirthday(date);
    }
}
