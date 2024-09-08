package br.com.chaletmanagement.context.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.chaletmanagement.context.ConnectionFactory;
import br.com.chaletmanagement.context.DAO.PhoneDAO;
import br.com.chaletmanagement.model.Phone;

public class PhoneDAOImplementation implements PhoneDAO 
{
    private String didSQLStatementWork(int n, String successfulMessage, String errorMessage) 
    {
        return (n > 0) ? successfulMessage : errorMessage;
    }

    @Override
    public String addPhone(Phone phone) 
    {
        String sql = "INSERT INTO phone(client_id, phone_type, phone_number) VALUES (?, ?, ?)";
        Connection dbConnection = ConnectionFactory.getConnection();
        try 
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, phone.getClientId());
            statement.setString(2, phone.getPhoneType());
            statement.setString(3, phone.getPhoneNumber());
            return didSQLStatementWork(statement.executeUpdate(), "Phone Inserted", "Could Not Insert Phone");
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
    public String updatePhone(Phone phone) 
    {
        String sql = "UPDATE phone SET phone_type = ?, phone_number = ? WHERE client_id = ?";
        Connection dbConnection = ConnectionFactory.getConnection();
        try 
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, phone.getPhoneType());
            statement.setString(2, phone.getPhoneNumber());
            statement.setInt(3, phone.getClientId());
            return didSQLStatementWork(statement.executeUpdate(), "Phone Updated", "Could Not Update Phone");
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
    public List<Phone> getAllPhones() 
    {
        String sql = "SELECT * FROM phone";
        Connection dbConnection = ConnectionFactory.getConnection();
        try 
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            if (set != null)
                return getPhoneListFromSet(set);
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

    private List<Phone> getPhoneListFromSet(ResultSet s) throws SQLException
    {
        List<Phone> phones = new ArrayList<>();
        while (s.next()) 
        {
            try 
            {
                phones.add(mapResultSetToPhone(s));
            } 
            catch (SQLException e)
            {
            }
        }
        return phones;
    }

    private Phone mapResultSetToPhone(ResultSet s) throws SQLException
    {
        Phone phone = new Phone();
        phone.setPhoneId(s.getInt("phone_id"));
        phone.setClientId(s.getInt("client_id"));
        phone.setPhoneType(s.getString("phone_type"));
        phone.setPhoneNumber(s.getString("phone_number"));
        return phone;
    }

    @Override
    public Phone searchById(Integer phoneId) 
    {
        String sql = "SELECT * FROM phone WHERE phone_id = ?";
        Connection dbConnection = ConnectionFactory.getConnection();
        try 
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, phoneId);
            ResultSet set = statement.executeQuery();
            if (set.next())
                return mapResultSetToPhone(set);
        
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
    public Phone searchByClientId(Integer clientId) 
    {
        String sql = "SELECT * FROM phone WHERE client_id = ?";
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, clientId);
            ResultSet set = statement.executeQuery();
            if (set.next()) 
            {
                return mapResultSetToPhone(set);
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
