package usermanagement.controller;

import org.primefaces.context.RequestContext;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import usermanagement.domain.Address;
import usermanagement.domain.Group;
import usermanagement.domain.User;
import usermanagement.service.GroupService;
import usermanagement.service.UserSearch;
import usermanagement.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller("userController")
@Scope("session")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressController addressController;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserSearch userSearch;

    private List<User> users;
    private List<User> foundedUsers; //founded users after search

    private User user;
    private Group group;
    private TreeNode selectedNode;

    private User selectedUser; //selected row in user table
    private int groupType;
    private String searchText;
    private String searchType;
    private boolean isSearchMode;


    ResourceBundle bundle;
    Map<String, Object> props; // options for dialog framework

    @PostConstruct
    public void init(){
        bundle= ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        user=new User();
        searchType="firstName";
        setIsSearchMode(false);
        props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 340);
        props.put("contentHeight", 400);
    }


    public void showUsersTable(String status){
        if(status.equals("new")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("added_user")));
        }
        if(status.equals("update")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("updated_user")));
        }
        if(selectedNode!=null){
            group=(Group)selectedNode.getData();
            if(!isSearchMode) {
                createTable(group.getId());
            }
            if(isSearchMode){
                showSearchResults(group.getId());
            }
        }
    }

    public void createTable(Integer id){
        users=userService.getUserByGroup(id);
    }

    public void showSearchResults(Integer id){
        users.clear();
        for(User user:foundedUsers) {
            if (user.getGroup().getId().equals(id)){
                users.add(user);
            }
        }
    }

    public void showAddressPanel() {
        addressController.createPanel(selectedUser.getAddress().getId());
    }


    public void addUserDialog(){
        RequestContext.getCurrentInstance().openDialog("addUser", props, null);
    }


    public void addUser()  {
        user.setCreateTS(new Date());
        user.setLastUpdateTS(new Date());
        user.setGroup(groupService.getGroupById(groupType));
        user.setAddress(new Address());
        userService.addUser(user);
        RequestContext.getCurrentInstance().closeDialog(user);
        clearUser();
    }

    public void editUserDialog(){
        if(selectedUser!=null){
            user=selectedUser;
            setGroupType(user.getGroup().getId());
            RequestContext.getCurrentInstance().openDialog("editUser", props, null);
        }
    }

    public void editUser(){
        user.setLastUpdateTS(new Date());
        user.setGroup(groupService.getGroupById(groupType));
        userService.editUser(user);
        RequestContext.getCurrentInstance().closeDialog(user);
        clearUser();
    }

    public void deleteUser() {
        if(!isSearchMode){
            if (selectedUser != null) {
                userService.deleteUser(selectedUser.getId());
                selectedUser=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("deleted_user")));
            }
        }
        //deleting user in search mode
        if(isSearchMode){
            if (selectedUser != null) {
                foundedUsers.remove(selectedUser);
                selectedUser=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("deleted_user")));
            }
        }
    }

    public void searchUser(){
        foundedUsers=userSearch.searchUser(searchText, searchType);
        if(foundedUsers.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("no_search_result")));
            setIsSearchMode(false);
        }
        if(!foundedUsers.isEmpty()){
            setIsSearchMode(true);
            showUsersTable("");
        }
    }

    public void outOfSearchMode(){
        setIsSearchMode(false);
        setSearchText(null);
        showUsersTable("");
    }


    private void clearUser(){
        user.setFirstName(null);
        user.setLastName(null);
        user.setUsername(null);
        user.setPassword(null);
        user.setEmail(null);
        user.setBirthday(null);
        user.setAddress(null);
        user.setGroup(null);

    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public List<User> getFoundedUsers() {
        return foundedUsers;
    }

    public void setFoundedUsers(List<User> foundedUsers) {
        this.foundedUsers = foundedUsers;
    }

    public boolean isSearchMode() {
        return isSearchMode;
    }

    public void setIsSearchMode(boolean isSearchMode) {
        this.isSearchMode = isSearchMode;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }


}