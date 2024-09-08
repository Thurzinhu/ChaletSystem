package br.com.chaletmanagement.context.DAOImplementation;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.chaletmanagement.context.ConnectionFactory;
import br.com.chaletmanagement.context.DAO.AddressDAO;
import br.com.chaletmanagement.model.Address;

public class AddressDAOImplementation implements AddressDAO
{
	private String didSQLStatementWork(int n, String successfulMessage, String errorMessage)
	{
		return (n > 0) ? successfulMessage : errorMessage;
	}

	@Override
	public String addAddress(Address address)
	{
		String sql = "INSERT INTO address(client_id, address, neighborhood, city, state, postal_code) VALUES (?, ?, ?, ?, ?, ?)";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setInt(1, address.getClientId());
			statement.setString(2, address.getAddress());
			statement.setString(3, address.getNeighborhood());
			statement.setString(4, address.getCity());
			statement.setString(5, address.getState());
			statement.setString(6, address.getPostalCode());
			return didSQLStatementWork(statement.executeUpdate(), "Address Inserted", "Could Not Insert Address");
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
	public String updateAddress(Address address)
	{
		String sql = "UPDATE address SET address = ?, neighborhood = ?, city = ?, state = ?, postal_code = ? WHERE client_id = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setString(1, address.getAddress());
			statement.setString(2, address.getNeighborhood());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getState());
			statement.setString(5, address.getPostalCode());
			statement.setInt(6, address.getClientId());
			return didSQLStatementWork(statement.executeUpdate(), "Address Updated", "Could Not Update Address");
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
	public List<Address> getAllAddresses()
	{
		String sql = "SELECT * FROM address";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			if (set != null)
			{
				return getAddressListFromSet(set);
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

	private List<Address> getAddressListFromSet(ResultSet s) throws SQLException
	{
	    List<Address> addresses = new ArrayList<>();
	    while (s.next())
	    {
	    	try
	    	{
	    		addresses.add(mapResultSetToAddress(s));	    		
	    	}
	    	catch (SQLException e)
	    	{
	    		
	    	}
	    }
	    return addresses;
	}

	private Address mapResultSetToAddress(ResultSet s) throws SQLException
	{
		Address address = new Address();
        address.setAddressId(s.getInt("address_id"));
        address.setClientId(s.getInt("client_id"));
        address.setAddress(s.getString("address"));
    	address.setNeighborhood(s.getString("neighborhood"));
    	address.setPostalCode(s.getString("postal_code"));        	
        address.setCity(s.getString("city"));
        address.setState(s.getString("state"));
        return address;
	}

	@Override
	public Address searchById(Integer id)
	{
		String sql = "SELECT * FROM address WHERE address_id = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if (set.next())
			{
				return mapResultSetToAddress(set);
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

	@Override
	public Address searchByClientId(Integer clientId)
	{
		String sql = "SELECT * FROM address WHERE client_id = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		try
		{
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setInt(1, clientId);
			ResultSet set = statement.executeQuery();
			if (set.next())
			{
				return mapResultSetToAddress(set);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			ConnectionFactory.closeConnection(dbConnection);
		}
		
		return null;
	}
}
