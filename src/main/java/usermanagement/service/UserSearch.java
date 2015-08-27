package usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import usermanagement.domain.User;


@Service
public class UserSearch {

    @Autowired
    private UserService userService;

    private List<User> users;

    public List<User> searchUser(String searchText, String searchType){
        if(searchType.equals("firstName")){
            users=userService.getUsersByFirstName(searchText);
        }
        if(searchType.equals("lastName")){
            users=userService.getUsersByLastName(searchText);
        }
        if(searchType.equals("email")){
            users=userService.getUsersByEmail(searchText);
        }
        if(searchType.equals("birthday")){
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date date = formatter.parse(searchText);
                users=userService.getUsersByBirthday(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    return users;
    }
}
