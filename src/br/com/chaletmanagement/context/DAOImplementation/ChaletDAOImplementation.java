package br.com.chaletmanagement.context.DAOImplementation;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.chaletmanagement.context.ConnectionFactory;
import br.com.chaletmanagement.context.DAO.ChaletDAO;
import br.com.chaletmanagement.model.Chalet;

public class ChaletDAOImplementation implements ChaletDAO
{
    private String didSQLStamentWork(int n, String successfullMessage, String errorMessage)
    {
        return (n > 0) ? successfullMessage : errorMessage;
    }

    @Override
    public String addChalet(Chalet chalet)
    {
        String sql = "INSERT INTO chalet(chalet_code, location, capacity, peak_season_price, normal_price) VALUES (?, ?, ?, ?, ?)";
        Connection dbConnection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, chalet.getChaletCode());
            statement.setString(2, chalet.getLocation());
            statement.setInt(3, chalet.getCapacity());
            statement.setDouble(4, chalet.getPeakSeasonPrice());
            statement.setDouble(5, chalet.getNormalPrice());
            return didSQLStamentWork(statement.executeUpdate(), "Chalet Inserted", "Could Not Insert Chalet");
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
    public String updateChalet(Chalet chalet)
    {
        String sql = "UPDATE chalet SET location = ?, capacity = ?, peak_season_price = ?, normal_price = ? WHERE chalet_code = ?";
        Connection dbConnection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, chalet.getLocation());
            statement.setInt(2, chalet.getCapacity());
            statement.setDouble(3, chalet.getPeakSeasonPrice());
            statement.setDouble(4, chalet.getNormalPrice());
            statement.setString(5, chalet.getChaletCode());
            return didSQLStamentWork(statement.executeUpdate(), "Chalet Updated", "Could Not Update Chalet");
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
    public String deleteChalet(Chalet chalet)
    {
        String sql = "DELETE FROM chalet WHERE chalet_code = ?";
        Connection dbConnection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, chalet.getChaletCode());
            return didSQLStamentWork(statement.executeUpdate(), "Chalet Deleted", "Could Not Delete Chalet");
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
    public List<Chalet> getAllChalets()
    {
        String sql = "SELECT * FROM chalet";
        Connection dbConnection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            if (set != null)
            {
                return getChaletsListFromSet(set);
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

    private List<Chalet> getChaletsListFromSet(ResultSet s) throws SQLException
    {
        List<Chalet> chalets = new ArrayList<>();
        while (s.next())
        {
            try
            {
                chalets.add(mapResultSetToChalet(s));
            }
            catch (SQLException e)
            {
            	
            }
        }
        return chalets;
    }

    private Chalet mapResultSetToChalet(ResultSet s) throws SQLException
    {
        Chalet chalet = new Chalet();
        chalet.setChaletId(s.getInt("chalet_id"));
        chalet.setChaletCode(s.getString("chalet_code"));
        chalet.setLocation(s.getString("location"));
        chalet.setCapacity(s.getInt("capacity"));
        chalet.setPeakSeasonPrice(s.getDouble("peak_season_price"));
        chalet.setNormalPrice(s.getDouble("normal_price"));
        return chalet;
    }

    @Override
    public Chalet searchByCode(String code)
    {
        String sql = "SELECT * FROM chalet WHERE chalet_code = ?";
        Connection dbConnection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, code);
            ResultSet set = statement.executeQuery();
            if (set.next())
            {
                return mapResultSetToChalet(set);
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
    public Chalet searchById(Integer id)
    {
        String sql = "SELECT * FROM chalet WHERE chalet_id = ?";
        Connection dbConnection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next())
            {
                return mapResultSetToChalet(set);
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
