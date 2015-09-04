

import org.junit.AfterClass;
import org.junit.BeforeClass;
import static junit.framework.Assert.*;

import org.junit.Ignore;
import usermanagement.domain.Address;
import usermanagement.domain.Group;
import usermanagement.domain.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import usermanagement.service.GroupService;
import usermanagement.service.UserService;

import java.util.Date;
import java.util.List;

public class UserServiceTest {

    private static ClassPathXmlApplicationContext context;
    private static UserService userService;
    private static GroupService groupService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        context=new ClassPathXmlApplicationContext("/spring/application-context.xml");
        userService=(UserService)context.getBean("userService");
        groupService=(GroupService)context.getBean("groupService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        context.close();
    }


    @Test
    public void addUser(){
        User user=new User("Name", "LastName", "Username", "Pass",
                "mail@mail.com", new Date(), new Date(), new Date());
        List<Group> allGroups= groupService.getAllGroups();
        //int exampleId=allGroups.get(allGroups.size()-1).getId();
        int exampleId=allGroups.get((int) (Math.random()*allGroups.size()-1)).getId();
        user.setGroup(groupService.getGroupById(exampleId));
        user.setAddress(new Address());
        userService.addUser(user);
        assertNotNull(user.getId());

    }
    //@Ignore
    @Test
    public void editUser(){
        User user;
        List<User> userList=userService.getAllUsers();
        Integer exampleId=userList.get(userList.size()-1).getId();
        user=userService.getUserById(exampleId);
        List<Group> allGroups= groupService.getAllGroups();
        int groupId=allGroups.get(allGroups.size()-1).getId();
        user.setFirstName("Sergei");
        Date date=new Date();
        user.setLastUpdateTS(date);
        Group group= groupService.getGroupById(groupId);
        user.setGroup(group);
        userService.editUser(user);
        assertEquals("Sergei", user.getFirstName());
        assertEquals(date, user.getLastUpdateTS());
        assertEquals(group, user.getGroup());
    }

    //@Ignore
    @Test
    public void deleteUser(){
        List<User> userList=userService.getAllUsers();
        int beforeValue=userList.size();
        userService.deleteUser(userList.get(beforeValue - 1).getId());
        int afterValue=userList.size();
        assertEquals(beforeValue, afterValue);
    }

    //@Ignore
    @Test
    public void getUserById(){
        User user;
        List<User> userList=userService.getAllUsers();
        Integer exampleId=userList.get(userList.size()-1).getId();
        user=userService.getUserById(exampleId);
        assertNotNull(user);
        assertEquals(exampleId, user.getId());
    }

    //@Ignore
    @Test
    public void getAllUsers(){
        List<User> userList=userService.getAllUsers();
        assertNotNull(userList);
        assertTrue(userList.size() > 0);
    }

    //@Ignore
    @Test
    public void getUsersByFirstName(){
        List<User> user;
        List<User> userList=userService.getAllUsers();
        String exampleFirstName=userList.get(userList.size()-1).getFirstName();
        user=userService.getUsersByFirstName(exampleFirstName);
        assertNotNull(user);
    }

    //@Ignore
    @Test
    public void getUsersByLastName(){
        List<User> user;
        List<User> userList=userService.getAllUsers();
        String exampleLastName=userList.get(userList.size()-1).getLastName();
        user=userService.getUsersByLastName(exampleLastName);
        assertNotNull(user);
    }

    //@Ignore
    @Test
    public void getUsersByEmail(){
        List<User> user;
        List<User> userList=userService.getAllUsers();
        String exampleEmail=userList.get(userList.size()-1).getEmail();
        user=userService.getUsersByEmail(exampleEmail);
        assertNotNull(user);
    }

    //@Ignore
    @Test
    public void getUsersByBirthday(){
        List<User> user;
        List<User> userList=userService.getAllUsers();
        Date exampleBirthday=userList.get(userList.size()-1).getBirthday();
        user=userService.getUsersByBirthday(exampleBirthday);
        assertNotNull(user);
    }

}
