package br.com.chaletmanagement.context.DAOImplementation;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.chaletmanagement.context.ConnectionFactory;
import br.com.chaletmanagement.context.DAO.BookingDAO;
import br.com.chaletmanagement.model.Booking;

public class BookingDAOImplementation implements BookingDAO {
	private String didSQLStamentWork(int n, String successfullMessage, String errorMessage) {
		return (n > 0) ? successfullMessage : errorMessage;
	}

	@Override
	public String addBooking(Booking booking) {
		String sql = "INSERT INTO booking(chalet_id, client_id, check_in_date, check_out_date, number_guests, discount, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection dbConnection = ConnectionFactory.getConnection();
		try {
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setInt(1, booking.getChaletId());
			statement.setInt(2, booking.getClientId());
			statement.setDate(3, java.sql.Date.valueOf(booking.getCheckInDate()));
			statement.setDate(4, java.sql.Date.valueOf(booking.getCheckOutDate()));
			statement.setInt(5, booking.getNumberGuests());
			statement.setDouble(6, booking.getDiscount());
			statement.setDouble(7, booking.getTotalPrice());
			return didSQLStamentWork(statement.executeUpdate(), "Booking Inserted", "Could Not Insert Booking");
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.closeConnection(dbConnection);
		}
	}

	@Override
	public String updateBooking(Booking booking) {
		String sql = "UPDATE booking SET client_id = ?, status = ?, check_in_date = ?, check_out_date = ?, number_guests = ?, discount = ?, total_price = ? WHERE chalet_id = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		try {
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setInt(1, booking.getChaletId());
			statement.setInt(2, booking.getClientId());
			statement.setDate(3, java.sql.Date.valueOf(booking.getCheckInDate()));
			statement.setDate(4, java.sql.Date.valueOf(booking.getCheckOutDate()));
			statement.setInt(5, booking.getNumberGuests());
			statement.setDouble(6, booking.getDiscount());
			statement.setDouble(7, booking.getTotalPrice());
			statement.setInt(8, booking.getChaletId());
			return didSQLStamentWork(statement.executeUpdate(), "Booking Updated", "Could Not Update Booking");
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.closeConnection(dbConnection);
		}
	}

	@Override
	public String deleteBooking(Booking booking) {
		String sql = "DELETE FROM booking WHERE chalet_id = ? AND client_id = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		try {
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setInt(1, booking.getChaletId());
			statement.setInt(2, booking.getClientId());
			return didSQLStamentWork(statement.executeUpdate(), "Booking Deleted", "Could Not Delete Booking");
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.closeConnection(dbConnection);
		}
	}

	@Override
	public List<Booking> getAllBookings() {
		String sql = "SELECT * FROM booking";
		Connection dbConnection = ConnectionFactory.getConnection();
		try {
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			if (set != null) {
				return getBookingsListFromSet(set);
			}
		} catch (SQLException e) {

		} finally {
			ConnectionFactory.closeConnection(dbConnection);
		}
		return null;
	}

	private List<Booking> getBookingsListFromSet(ResultSet s) throws SQLException {
		List<Booking> bookings = new ArrayList<>();
		while (s.next()) {
			try {
				bookings.add(mapResultSetToBooking(s));
			} catch (SQLException e) {

			}
		}
		return bookings;
	}

	private Booking mapResultSetToBooking(ResultSet s) throws SQLException {
		Booking booking = new Booking();
		booking.setChaletId(s.getInt("chalet_id"));
		booking.setClientId(s.getInt("client_id"));
		booking.setStatus(s.getString("status"));
		booking.setCheckInDate(s.getDate("check_in_date").toLocalDate());
		booking.setCheckOutDate(s.getDate("check_out_date").toLocalDate());
		booking.setNumberGuests(s.getInt("number_guests"));
		booking.setDiscount(s.getDouble("discount"));
		booking.setTotalPrice(s.getDouble("total_price"));
		return booking;
	}

	@Override
	public Booking searchById(Integer id) {
		String sql = "SELECT * FROM booking WHERE chalet_id = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		try {
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				return mapResultSetToBooking(set);
			}
		} catch (SQLException e) {

		} finally {
			ConnectionFactory.closeConnection(dbConnection);
		}
		return null;
	}
}