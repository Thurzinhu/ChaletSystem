package br.com.chaletmanagement.context.DAO;

import java.util.List;
import br.com.chaletmanagement.model.Address;

public interface AddressDAO 
{
    String addAddress(Address address);
    String updateAddress(Address address);
    String deleteAddress(Address address);
    List<Address> getAllAddresses();
    Address searchById(Integer id);
    List<Address> searchByClientId(Integer clientId);
}
