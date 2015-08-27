
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static junit.framework.Assert.*;
import org.junit.Ignore;
import usermanagement.domain.Address;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import usermanagement.service.AddressService;

import java.util.List;


public class AddressServiceTest {

    private static ClassPathXmlApplicationContext context;
    private static AddressService addressService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        context=new ClassPathXmlApplicationContext("/spring/application-context.xml");
        addressService=(AddressService)context.getBean("addressService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        context.close();
    }

    @Test
    public void editAddress(){
        Address address;
        List<Address> addressList=addressService.getAllAddresses();
        int exampleId=addressList.get(addressList.size()-1).getId();
        address=addressService.getAddressById(exampleId);
        address.setCity("SPB");
        address.setCountry("Russia");
        address.setDistrict("Primorsky");
        address.setStreet("Optikov");
        address.setZip(123456);
        addressService.editAddress(address);
        assertEquals("SPB", address.getCity());
        assertEquals("Russia", address.getCountry());
        assertEquals("Primorsky", address.getDistrict());
        assertEquals("Optikov", address.getStreet());
        assertEquals(new Integer(123456), address.getZip());
    }

    @Test
    public void getAddressById(){
        Address address;
        List<Address> addressList=addressService.getAllAddresses();
        Integer exampleId=addressList.get(addressList.size()-1).getId();
        address=addressService.getAddressById(exampleId);
        assertNotNull(address);
        assertEquals(exampleId, address.getId());

    }


    @Test
    public void getAllAddresses(){
        List<Address> addressList= addressService.getAllAddresses();
        assertNotNull(addressList);
        assertTrue(addressList.size()>0);
    }
}
