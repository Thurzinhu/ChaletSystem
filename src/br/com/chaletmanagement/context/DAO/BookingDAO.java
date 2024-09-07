package br.com.chaletmanagement.context.DAO;

import java.util.List;

import br.com.chaletmanagement.model.Booking;

public interface BookingDAO
{
	String addBooking(Booking booking);
	String updateBooking(Booking booking);
	String deleteBooking(Booking booking);
	List<Booking> getAllBookings();
	Booking searchById(Integer id);
}
