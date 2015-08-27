import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import usermanagement.domain.Group;
import usermanagement.service.GroupService;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


public class GroupServiceTest {

    private static ClassPathXmlApplicationContext context;
    private static GroupService groupService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        context=new ClassPathXmlApplicationContext("/spring/application-context.xml");
        groupService=(GroupService)context.getBean("groupService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        context.close();
    }

    @Test
    public void getAllGroups(){
        List<Group> groupList=groupService.getAllGroups();
        assertNotNull(groupList);
        assertTrue(groupList.size() > 0);
    }

    @Test
    public void getUserById(){
        Group group;
        List<Group> groupList=groupService.getAllGroups();
        Integer exampleId=groupList.get(groupList.size()-1).getId();
        group=groupService.getGroupById(exampleId);
        assertNotNull(group);
        assertEquals(exampleId, group.getId());
    }

}
