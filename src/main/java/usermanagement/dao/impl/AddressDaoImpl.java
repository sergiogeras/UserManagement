package usermanagement.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import usermanagement.dao.AddressDao;
import usermanagement.domain.Address;

import java.util.List;


@Repository("addressDao")
@SuppressWarnings("unchecked")
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void editAddress(Address address) {
        sessionFactory.getCurrentSession().update(address);
    }

    @Override
    public Address getAddressById(Integer id) {
        return (Address)sessionFactory.getCurrentSession().get(Address.class, id);
    }

    @Override
    public List<Address> getAllAddresses() {
        return sessionFactory.getCurrentSession().createQuery("from Address ").list();
    }

}
