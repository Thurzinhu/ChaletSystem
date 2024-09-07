package br.com.chaletmanagement.view;
import java.sql.Connection;
import java.time.LocalDate;

import br.com.chaletmanagement.context.ConnectionFactory;
import br.com.chaletmanagement.model.*;
import br.com.chaletmanagement.controller.*;

public class Teste
{
	public static void main(String[] args)
	{
		Connection con = ConnectionFactory.getConnection();
		BookingController bc = new BookingController();
		Booking b = new Booking();
		b.setChaletId(1);
		b.setClientId(1);
		b.setCheckInDate(LocalDate.now());
		b.setCheckOutDate(LocalDate.now().plusDays(7));
		b.setDiscount(12.00);
		b.setNumberGuests(2);
		b.setStatus("Reserved");
		b.setTotalPrice(600.0);

		System.out.println(bc.addBooking(b));
		for (Booking bo : bc.getAllBookings())
		{
			System.out.println(bo.getNumberGuests());
		}
		

		if(con != null)
		{
			System.out.println("Closing Connection");
			ConnectionFactory.closeConnection(con);
		}
	}
}
