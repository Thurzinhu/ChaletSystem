package br.com.chaletmanagement.controller;

import java.util.List;
import br.com.chaletmanagement.context.DAO.AddressDAO;
import br.com.chaletmanagement.context.DAOImplementation.AddressDAOImplementation;
import br.com.chaletmanagement.model.Address;

public class AddressController
{
	private AddressDAO addressDAO;
	
	public AddressController()
	{
		addressDAO = new AddressDAOImplementation();
	}
	
	public String addAddress(Address address)
	{
		return addressDAO.addAddress(address);
	}
	
	public String updateAddress(Address address)
	{
		return addressDAO.updateAddress(address);
	}

	public List<Address> getAllAddresses()
	{
		return addressDAO.getAllAddresses();
	}

	public Address searchById(Integer id)
	{
		return addressDAO.searchById(id);
	}

	public Address searchByClientId(Integer clientId)
	{
		return addressDAO.searchByClientId(clientId);
	}
}
