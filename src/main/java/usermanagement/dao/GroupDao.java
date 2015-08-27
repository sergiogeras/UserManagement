package usermanagement.dao;

import usermanagement.domain.Group;

import java.util.List;


public interface GroupDao {

    Group getGroupById(Integer id);
    List<Group> getAllGroups();
}
