package usermanagement.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import usermanagement.dao.UserDao;
import usermanagement.domain.User;
import java.util.Date;
import java.util.List;



@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        sessionFactory.getCurrentSession().delete(getUserById(id));
    }

    @Override
    public void editUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public User getUserById(Integer id) {
        return (User)sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> checkUsername(String username) {
        return sessionFactory.getCurrentSession().createQuery("from User where username=:username").setParameter("username", username).list();
    }

    public List<User> getUserByGroup(Integer id) {
        return sessionFactory.getCurrentSession().createQuery("from User where group.id=:id").setParameter("id", id).list();
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public List<User> getUsersByFirstName(String str) {
        return sessionFactory.getCurrentSession().createQuery("from User where firstName like :str").setParameter("str", "%" + str + "%").list();
    }

    @Override
    public List<User> getUsersByLastName(String str) {
        return sessionFactory.getCurrentSession().createQuery("from User where lastName like :str").setParameter("str", "%"+str+"%").list();
    }

    @Override
    public List<User> getUsersByEmail(String str) {
        return sessionFactory.getCurrentSession().createQuery("from User where email like :str").setParameter("str", "%"+str+"%").list();
    }

    @Override
    public List<User> getUsersByBirthday(Date date) {
        return sessionFactory.getCurrentSession().createQuery("from User where birthday=:date").setParameter("date", date).list();
    }
}
