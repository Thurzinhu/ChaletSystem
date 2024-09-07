package br.com.chaletmanagement.util;

import java.time.LocalDate;

public class Util
{
	public static int[] mapGUIDateToLocalDate(String dataGui) throws Exception
	{
		int[] dataFormatada = new int[3];
		if (dataGui.length() != 10)
			throw new Exception("Invalid Date");
		dataFormatada[0] = Integer.parseInt(dataGui.substring(0, 2));
		dataFormatada[1] = Integer.parseInt(dataGui.substring(3, 5));
		dataFormatada[2] = Integer.parseInt(dataGui.substring(6, 10));
		return dataFormatada;
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
        int[] formattedDate;
        try 
        {
            formattedDate = Util.mapGUIDateToLocalDate(dateStr);
            if (formattedDate.length != 3) 
            {
                throw new Exception("Invalid " + fieldName + " format. Please use DD-MM-YYYY.");
            }
        } 
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e) 
        {
            throw new Exception("Invalid date format. Please use DD-MM-YYYY.");
        }

        if (formattedDate[1] < 1 || formattedDate[1] > 12) 
        {
            throw new Exception("Invalid month in " + fieldName + ". Must be between 1 and 12.");
        }
        if (formattedDate[0] < 1 || formattedDate[0] > 31) 
        {
            throw new Exception("Invalid day in " + fieldName + ". Must be between 1 and 31.");
        }

        return LocalDate.of(formattedDate[2], formattedDate[1], formattedDate[0]);
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

