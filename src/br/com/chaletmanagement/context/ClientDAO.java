package br.com.chaletmanagement.context;

import java.util.List;
import br.com.chaletmanagement.model.Client;

public interface ClientDAO
{
	String addClient(Client client);
	String updateClient(Client client);
	String deleteClient(Client client);
	List<Client> getAllClients();
	Client searchById(String id);
}
