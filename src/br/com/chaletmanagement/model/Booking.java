package br.com.chaletmanagement.model;

import java.time.LocalDate;

public class Booking
{
    private Integer chaletCode;
    private String status;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer numberGuests;
    private Double discount;
    private Double totalPrice;
	
    public Integer getChaletCode() 
    {
		return chaletCode;
	}
	
    public void setChaletCode(Integer chaletCode) {
		this.chaletCode = chaletCode;
	}
	
    public String getStatus() 
    {
		return status;
	}
	
    public void setStatus(String status) 
    {
		this.status = status;
	}
	
    public LocalDate getCheckInDate() 
    {
		return checkInDate;
	}
	
    public void setCheckInDate(LocalDate checkInDate)
    {
		this.checkInDate = checkInDate;
	}
	
    public LocalDate getCheckOutDate() 
    {
		return checkOutDate;
	}
	
    public void setCheckOutDate(LocalDate checkOutDate)
    {
		this.checkOutDate = checkOutDate;
	}
	
    public Integer getNumberGuests() 
    {
		return numberGuests;
	}
    
	public void setNumberGuests(Integer numberGuests) {
		this.numberGuests = numberGuests;
	}
	
    public Double getDiscount() 
    {
		return discount;
	}
	
    public void setDiscount(Double discount)
    {
		this.discount = discount;
	}

	public Double getTotalPrice()
    {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice)
    {
		this.totalPrice = totalPrice;
	}
}
