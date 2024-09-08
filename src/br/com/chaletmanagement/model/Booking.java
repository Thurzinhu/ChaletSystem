package br.com.chaletmanagement.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking
{
	private Integer bookingId;
	private Integer chaletId;
	private Integer clientId;
	private String status;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Integer numberGuests;
	private Double discount;
	private Double totalPrice;
	
	public Integer getBookingId() 
	{
		return bookingId;
	}
	
	public void setBookingId(Integer bookingId) 
	{
		this.bookingId = bookingId;
	}
	
	public Integer getChaletId() {
		return chaletId;
	}
	
	public void setChaletId(Integer chaletId) {
		this.chaletId = chaletId;
	}
	
	public Integer getClientId() {
		return clientId;
	}
	
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	public Integer getNumberGuests() {
		return numberGuests;
	}
	
	public void setNumberGuests(Integer numberGuests) {
		this.numberGuests = numberGuests;
	}
	
	public Double getDiscount() {
		return discount;
	}
	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Double calculateTotalPrice(Chalet chalet)
	{
        long numberOfDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

        Double totalPrice = numberOfDays * chalet.getNormalPrice();

        if (discount != null && discount > 0)
        {
            totalPrice = totalPrice - (totalPrice * discount / 100);
        }

        return totalPrice;
    }
}
