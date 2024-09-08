package br.com.chaletmanagement.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String driver = "org.postgresql.Driver";

	public static Connection getConnection() {
		String user = "postgres";
		String senha = "1234";
		String url = "jdbc:postgresql://127.0.0.1:5432/chalet_heaven";
		Connection dbConnection = null;
		try {
			Class.forName(driver);
			dbConnection = (Connection) DriverManager.getConnection(url, user, senha);
		} catch (ClassNotFoundException e) {
			System.err.print(e.getMessage());
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}
		return dbConnection;
	}

	public static void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			System.err.print("Could not close database connection");
		}
	}
}
