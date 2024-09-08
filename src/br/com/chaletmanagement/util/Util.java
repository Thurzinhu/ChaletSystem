package br.com.chaletmanagement.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util
{

	public static LocalDate mapGUIDateToLocalDate(String dataGui) throws Exception 
	{
	    if (dataGui.length() != 10) {
	        throw new Exception("Invalid Date");
	    }
	    int day = Integer.parseInt(dataGui.substring(0, 2));
	    int month = Integer.parseInt(dataGui.substring(3, 5));
	    int year = Integer.parseInt(dataGui.substring(6, 10));
	    return LocalDate.of(year, month, day);
	}
	
	public static String formatDateToDDMMYYYY(LocalDate date)
	{
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    return date.format(formatter);
	}

	public static String mapLocalDateToGUIDate(LocalDate dataLocalDate)
	{
		StringBuilder dataFormatada = new StringBuilder();
		if (dataLocalDate.getDayOfMonth() < 10)
		{
			dataFormatada.append("0" + dataLocalDate.getDayOfMonth() + "/");
		}
		else
		{
			dataFormatada.append(dataLocalDate.getDayOfMonth() + "/");
		}
		if (dataLocalDate.getMonthValue() < 10)
		{
			dataFormatada.append("0" + dataLocalDate.getMonthValue() + "/");
		} 
		else
		{
			dataFormatada.append(dataLocalDate.getMonthValue() + "/");
		}
		dataFormatada.append(dataLocalDate.getYear());
		return dataFormatada.toString();
	}
	
	public static String validateAndGetString(String value, String fieldName) throws Exception 
    {
        if (value.isEmpty()) 
        {
            throw new Exception(fieldName + " Cannot Be Empty.");
        }
        return value;
    }

	public static LocalDate validateAndGetDate(String dateStr, String fieldName) throws Exception
    {
        LocalDate date;
        try 
        {
            date = Util.mapGUIDateToLocalDate(dateStr);
        } 
        catch (NumberFormatException e) 
        {
            throw new Exception("Invalid date format for " + fieldName + ". Please use DD-MM-YYYY.");
        }

        int day = date.getDayOfMonth();
        int month = date.getMonthValue();

        if (month < 1 || month > 12)
        {
            throw new Exception("Invalid month in " + fieldName + ". Must be between 1 and 12.");
        }
        if (day < 1 || day > 31)
        { 
            throw new Exception("Invalid day in " + fieldName + ". Must be between 1 and 31.");
        }

        return date;
    }


	public static Integer validateAndGetInteger(String value, String fieldName) throws Exception 
    {
        try 
        {
            return Integer.parseInt(value);
        } 
        catch (NumberFormatException e) 
        {
            throw new Exception("Invalid " + fieldName + ". Must be an integer.");
        }
    }

	public static Double validateAndGetDouble(String value, String fieldName) throws Exception 
    {
        try 
        {
            return Double.parseDouble(value);
        } 
        catch (NumberFormatException e) 
        {
            throw new Exception("Invalid " + fieldName + ". Must be a number.");
        }
    }
}

