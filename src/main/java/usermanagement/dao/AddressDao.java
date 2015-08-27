package usermanagement.dao;

import usermanagement.domain.Address;

import java.util.List;


public interface AddressDao {

    void editAddress(Address address);
    Address getAddressById(Integer id);
    List<Address> getAllAddresses();
}
