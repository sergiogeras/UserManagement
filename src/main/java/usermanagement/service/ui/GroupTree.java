package usermanagement.service.ui;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import usermanagement.domain.*;
import usermanagement.service.GroupService;
import usermanagement.service.UserService;



@Component
public class GroupTree {

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    private TreeNode root;

    //формирование структуры дерева групп
    public TreeNode createTree(){
        TreeNode root=new DefaultTreeNode("root", null);
        for(Group group : groupService.getAllGroups()){
            TreeNode groupNode=new DefaultTreeNode(group, root);
        }
        return root;
    }





    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
