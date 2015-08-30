package usermanagement.controller;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import usermanagement.domain.*;
import usermanagement.service.AddressService;

import java.util.HashMap;
import java.util.Map;



@Controller("addressController")
@Scope("session")
public class AddressController {

    @Autowired
    private AddressService addressService;


    private Address address;


    public String createPanel(Integer id){

        address=addressService.getAddressById(id);
        return "";
    }

    public void editAddressDialog(){
        Map<String, Object> props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 260);
        props.put("contentHeight", 255);
        RequestContext.getCurrentInstance().openDialog("editAddress", props, null);
    }

    public void editAddress(){
        addressService.editAddress(address);
        RequestContext.getCurrentInstance().closeDialog(address);
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
