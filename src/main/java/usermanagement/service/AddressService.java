package usermanagement.service;

import usermanagement.domain.Address;

import java.util.List;


public interface AddressService {

    void editAddress(Address address);
    Address getAddressById(Integer id);
    List<Address> getAllAddresses();
}
