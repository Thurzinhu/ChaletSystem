package br.com.chaletmanagement.context.DAO;

import java.util.List;
import br.com.chaletmanagement.model.Address;

public interface AddressDAO {
	String addAddress(Address address);

	String updateAddress(Address address);

	List<Address> getAllAddresses();

	Address searchById(Integer addressId);

	Address searchByClientId(Integer clientId);
}