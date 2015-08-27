package usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usermanagement.dao.AddressDao;
import usermanagement.domain.Address;
import usermanagement.service.AddressService;

import java.util.List;



@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public void editAddress(Address address) {
        addressDao.editAddress(address);
    }

    @Override
    public Address getAddressById(Integer id) {
        return addressDao.getAddressById(id);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }
}
