package usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usermanagement.dao.GroupDao;
import usermanagement.domain.Group;
import usermanagement.service.GroupService;

import java.util.List;



@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
     GroupDao groupDao;

    @Override
    public Group getGroupById(Integer id) {
        return groupDao.getGroupById(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }
}
