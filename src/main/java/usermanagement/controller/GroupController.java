package usermanagement.controller;

import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import usermanagement.service.ui.GroupTree;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;



@Controller
@Scope("session")
public class GroupController {

    private ResourceBundle bundle;
    private TreeNode root;




    @Autowired
    private GroupTree groupTree;

    @PostConstruct
    public void init(){
        bundle = ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        root= groupTree.createTree();

    }


    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }


}
