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
		Integer clientId = clientDAO.searchByRG(client.getRG()).getClientId(); 
		address.setClientId(clientId);
		addressController.addAddress(address);
		return result;
	}
	
	public String updateClient(Client client, Address address)
	{
		String result = clientDAO.updateClient(client);
		Integer clientId = clientDAO.searchByRG(client.getRG()).getClientId();
		address.setClientId(clientId);
		addressController.updateAddress(address);
		return result;
	}

	public String deleteClient(Client client)
	{
		return clientDAO.deleteClient(client);
	}

	public List<Client> getAllClients()
	{
		return clientDAO.getAllClients();
	}

	public Client searchByRG(String RG)
	{
		return clientDAO.searchByRG(RG);
	}

	public Client searchById(Integer id)
	{
		return clientDAO.searchById(id);
	}

	public Address getAddressByClientId(Client client)
	{
		Integer clientId = clientDAO.searchByRG(client.getRG()).getClientId();
		return addressController.searchByClientId(clientId);
	}
}
