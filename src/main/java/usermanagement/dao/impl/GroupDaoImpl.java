package usermanagement.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import usermanagement.dao.GroupDao;
import usermanagement.domain.Group;

import java.util.List;



@Repository("groupDao")
@SuppressWarnings("unchecked")
public class GroupDaoImpl implements GroupDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Group getGroupById(Integer id) {
        return (Group)sessionFactory.getCurrentSession().get(Group.class, id);
    }

    @Override
    public List<Group> getAllGroups() {
        return sessionFactory.getCurrentSession().createQuery("from Group ").list();
    }
}
