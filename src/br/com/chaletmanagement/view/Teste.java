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
		AddressController ac = new AddressController();
		Address a = new Address();
		a.setAddress("VP");
		a.setCity("SP");
		a.setClientId(1);
		a.setState("Brazil");
		System.out.println(ac.addAddress(a));
		

		if(con != null)
		{
			System.out.println("Closing Connection");
			ConnectionFactory.closeConnection(con);
		}
	}
}
