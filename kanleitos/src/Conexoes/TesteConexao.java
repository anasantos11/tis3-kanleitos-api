package Conexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteConexao {

	private static Connection connection;

	public static void main(String[] args) {
		connection = new ConnectionFactory().getConnection();
		System.out.println("Conexão aberta!");

		String sql = "select * from DEV.PACIENTES";
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + " "
						+ result.getString(4) + " " + result.getString(5));
			}

			result.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
