package br.com.chaletmanagement.controller;

import java.util.List;

import br.com.chaletmanagement.context.DAO.BookingDAO;
import br.com.chaletmanagement.context.DAOImplementation.BookingDAOImplementation;
import br.com.chaletmanagement.model.Booking;

public class BookingController
{
    private BookingDAO bookingDAO;

    public BookingController()
    {
        bookingDAO = new BookingDAOImplementation();
    }

    public String addBooking(Booking booking)
    {
        return bookingDAO.addBooking(booking);
    }
    
    public String updateBooking(Booking booking)
    {
        return bookingDAO.updateBooking(booking);
    }

    public String deleteBooking(Booking booking)
    {
        return bookingDAO.deleteBooking(booking);
    }

    public List<Booking> getAllBookings()
    {
        return bookingDAO.getAllBookings();
    }

    public Booking searchById(Integer id)
    {
        return bookingDAO.searchById(id);
    }
}