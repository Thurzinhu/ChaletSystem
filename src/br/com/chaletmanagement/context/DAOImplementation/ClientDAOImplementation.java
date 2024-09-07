package br.com.chaletmanagement.context.DAOImplementation;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.chaletmanagement.context.ConnectionFactory;
import br.com.chaletmanagement.context.DAO.ClientDAO;
import br.com.chaletmanagement.model.Client;

public class ClientDAOImplementation implements ClientDAO
{
	private String didSQLStamentWork(int n, String successfullMessage, String errorMessage)
	{
		return (n > 0) ? successfullMessage : errorMessage;
	}
	
	@Override
	public String addClient(Client client)
	{
		String sql = "INSERT INTO client(id, name, birthday) VALUES (?, ?, ?)";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setString(1, client.getId());
			statement.setString(2, client.getName());
			statement.setObject(3, client.getBirthday());
			return didSQLStamentWork(statement.executeUpdate(), "Client Inserted", "Could Not Insert Client");
		}
		catch (SQLException e)
		{
			return e.getMessage();
		}
		finally
		{
			ConnectionFactory.closeConnection(dbConnection);
		}
	}


	@Override
	public String updateClient(Client client)
	{
		String sql = "UPDATE client SET name = ?, birthday = ? WHERE id = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setString(1, client.getName());
			statement.setObject(2, client.getBirthday());
			statement.setString(3, client.getId());
			return didSQLStamentWork(statement.executeUpdate(), "Client Updated", "Could Not Update Client");
		}
		catch (SQLException e)
		{
			return e.getMessage();
		}
		finally
		{
			ConnectionFactory.closeConnection(dbConnection);
		}
	}

	@Override
	public String deleteClient(Client client)
	{
		String sql = "DELETE FROM client WHERE id = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setString(1, client.getId());
			return didSQLStamentWork(statement.executeUpdate(), "Client Deleted", "Could Not Delete Client");
		}
		catch (SQLException e)
		{
			return e.getMessage();
		}
		finally
		{
			ConnectionFactory.closeConnection(dbConnection);
		}
	}

	@Override
	public List<Client> getAllClients()
	{
		String sql = "SELECT * FROM client";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			if (set != null)
			{
				return getClientsListFromSet(set);
			}
		}
		catch (SQLException e)
		{
			
		}
		finally
		{
			ConnectionFactory.closeConnection(dbConnection);
		}
		
		return null;
	}
	
	private List<Client> getClientsListFromSet(ResultSet s) throws SQLException
	{
	    List<Client> clients = new ArrayList<>();
	    while (s.next())
	    {
	    	try
	    	{
	    		clients.add(mapResultSetToClient(s));	    		
	    	}
	    	catch (SQLException e)
	    	{
	    		
	    	}
	     
	    }
	    return clients;
	}
	
	private Client mapResultSetToClient(ResultSet s) throws SQLException
	{
		Client client = new Client();
		client.setClientId(s.getInt("client_id"));
        client.setId(s.getString("id"));
        client.setName(s.getString("name"));
        client.setBirthday(s.getObject("birthday", LocalDate.class));
        return client;
	}

	@Override
	public Client searchById(String id)
	{
		String sql = "SELECT * FROM client WHERE id = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet set = statement.executeQuery();
			if (set.next())
			{
				return mapResultSetToClient(set);
			}
		}
		catch (SQLException e)
		{
			
		}
		finally
		{
			ConnectionFactory.closeConnection(dbConnection);
		}
		
		return null;
	}
}
