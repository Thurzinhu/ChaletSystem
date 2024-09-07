package br.com.chaletmanagement.view;
import java.sql.Connection;

import br.com.chaletmanagement.context.ConnectionFactory;
import br.com.chaletmanagement.model.*;
import br.com.chaletmanagement.controller.*;

public class Teste
{
	public static void main(String[] args)
	{
		Connection con = ConnectionFactory.getConnection();
		//ClientController controller = new ClientController();
		ChaletController c = new ChaletController();
		Chalet h = new Chalet();
		h.setCapacity(12);
		h.setChaletCode("AABB");
		h.setLocation("SP-BU");
		h.setNormalPrice(123.00);
		h.setPeakSeasonPrice(200.00);
		
		System.out.println(c.updateChalet(h));
		
		for (Chalet ho : c.getAllChalets())
		{
			System.out.println(ho.getChaletCode());
		}
		// System.out.println(c.deleteChalet(h));
		if(con != null)
		{
			System.out.println("Closing Connection");
			ConnectionFactory.closeConnection(con);
		}
	}
}
