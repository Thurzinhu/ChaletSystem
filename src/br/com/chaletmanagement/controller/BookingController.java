package br.com.chaletmanagement.controller;

import java.util.List;

import br.com.chaletmanagement.context.DAO.BookingDAO;
import br.com.chaletmanagement.context.DAOImplementation.BookingDAOImplementation;
import br.com.chaletmanagement.model.Booking;
import br.com.chaletmanagement.model.Chalet;

public class BookingController {
	private BookingDAO bookingDAO;
	private ChaletController chaletController;

	public BookingController() {
		bookingDAO = new BookingDAOImplementation();
		chaletController = new ChaletController();
	}

	public String addBooking(Booking booking) {
		Chalet chalet = getChaletByChaletId(booking.getChaletId());
		Double totalPrice = booking.calculateTotalPrice(chalet);
		booking.setTotalPrice(totalPrice);
		return bookingDAO.addBooking(booking);
	}

	public String updateBooking(Booking booking) {
		Chalet chalet = getChaletByChaletId(booking.getChaletId());
		Double totalPrice = booking.calculateTotalPrice(chalet);
		booking.setTotalPrice(totalPrice);
		return bookingDAO.updateBooking(booking);
	}

	public String deleteBooking(Booking booking) {
		return bookingDAO.deleteBooking(booking);
	}

	public List<Booking> getAllBookings() {
		return bookingDAO.getAllBookings();
	}

	public Booking searchById(Integer id) {
		return bookingDAO.searchById(id);
	}

	public Chalet getChaletByCode(String chaletCode) {
		return chaletController.searchByCode(chaletCode);
	}

	public Chalet getChaletByChaletId(Integer chaletId) {
		return chaletController.searchById(chaletId);
	}
}