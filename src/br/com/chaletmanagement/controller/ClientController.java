package br.com.chaletmanagement.controller;

import java.util.List;

import br.com.chaletmanagement.context.ClientDAO;
import br.com.chaletmanagement.context.ClientDAOImplementation;
import br.com.chaletmanagement.model.Client;

public class ClientController
{
	public String addClient(Client client)
	{
		ClientDAO dao = new ClientDAOImplementation();
		return dao.addClient(client);
	}
	
	public String updateClient(Client client)
	{
		ClientDAO dao = new ClientDAOImplementation();
		return dao.updateClient(client);
	}

	public String deleteClient(Client client)
	{
		ClientDAO dao = new ClientDAOImplementation();
		return dao.deleteClient(client);
	}

	public List<Client> getAllClients()
	{
		ClientDAO dao = new ClientDAOImplementation();
		return dao.getAllClients();
	}

	public Client searchById(String id)
	{
		ClientDAO dao = new ClientDAOImplementation();
		return dao.searchById(id);
	}
}
