package br.com.chaletmanagement.model;

public class Chalet
{
    private String location;
    private int capacity;
    private Double peakSeasonPrice;
    private Double normalPrice;
    private String chaletCode;

	public Chalet()
    {
    	
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public Double getPeakSeasonPrice()
    {
        return peakSeasonPrice;
    }

    public void setPeakSeasonPrice(Double peakSeasonPrice)
    {
        this.peakSeasonPrice = peakSeasonPrice;
    }

    public Double getNormalPrice()
    {
        return normalPrice;
    }

    public void setNormalPrice(Double normalPrice)
    {
        this.normalPrice = normalPrice;
    }
    
    public String getChaletCode()
    {
		return chaletCode;
	}

	public void setChaletCode(String chaletCode)
	{
		this.chaletCode = chaletCode;
	}
}