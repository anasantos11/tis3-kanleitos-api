package Conexoes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		/**
		 * Dados necessarios para conectar ao Banco de Dados
		 */
		String hostName = "kanleitos.database.windows.net";
		String dbName = "kanleitos";
		String user = "kanleitosadmin";
		String password = "BLAW@17leitos";
		String url = String.format(
				"jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;"
						+ "encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
				hostName, dbName, user, password);
		try {
			/**
			 * Retorna informacoes da conexao com o banco
			 */
			return DriverManager.getConnection(url);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
}


