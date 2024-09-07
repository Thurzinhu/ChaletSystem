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

}

