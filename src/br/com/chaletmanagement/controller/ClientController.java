package br.com.chaletmanagement.controller;

import java.util.List;
import br.com.chaletmanagement.context.DAO.ClientDAO;
import br.com.chaletmanagement.context.DAOImplementation.ClientDAOImplementation;
import br.com.chaletmanagement.model.Client;
import br.com.chaletmanagement.model.Address;

public class ClientController
{
	private ClientDAO clientDAO;
	private AddressController addressController;

	public ClientController()
	{
		this.clientDAO = new ClientDAOImplementation();
		this.addressController = new AddressController();
	}

	public String addClient(Client client, Address address)
	{
		String result = clientDAO.addClient(client);
		Integer clientId = clientDAO.searchById(client.getId()).getClientId(); 
		address.setClientId(clientId);
		addressController.addAddress(address);
		return result;
	}
	
	public String updateClient(Client client, Address address)
	{
		String result = clientDAO.updateClient(client);
		Integer clientId = clientDAO.searchById(client.getId()).getClientId();
		address.setClientId(clientId);
		addressController.updateAddress(address);
		return result;
	}

	public String deleteClient(Client client)
	{
		Client clientToBeDeleted = clientDAO.searchById(client.getId());			
		if (clientToBeDeleted == null)
			return "Could Not Delete Client";
		
		List<Address> addresses = addressController.searchByClientId(clientToBeDeleted.getClientId());
		for (Address address : addresses)
		{
			addressController.deleteAddress(address);
		}
		return clientDAO.deleteClient(client);
	}

	public List<Client> getAllClients()
	{
		return clientDAO.getAllClients();
	}

	public Client searchById(String id)
	{
		return clientDAO.searchById(id);
	}

	public List<Address> getAddressesByClientId(Client client)
	{
		Integer clientId = clientDAO.searchById(client.getId()).getClientId();
		return addressController.searchByClientId(clientId);
	}
}
