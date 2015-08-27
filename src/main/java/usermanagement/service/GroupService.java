package usermanagement.service;

import usermanagement.domain.Group;

import java.util.List;


public interface GroupService {

    Group getGroupById(Integer id);
    List<Group> getAllGroups();
}
