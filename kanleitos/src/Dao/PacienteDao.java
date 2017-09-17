package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Bases.Paciente;
import Conexoes.ConnectionFactory;

public class PacienteDao {
	private Connection connection;

	public PacienteDao() {
		connection = new ConnectionFactory().getConnection();
		System.out.println("Conexão aberta!");
	}

	public boolean validarPaciente(Paciente paciente) {
		boolean camposVazios = false;
		if (paciente.getNumProntuario() == -1 || paciente.getNomePaciente() == null || paciente.getIdade() == -1
				|| paciente.getGenero() == null ) {
			camposVazios = true;
		}
		return camposVazios;
	}

	public void inserirPaciente(Paciente paciente) throws Exception {
		String sql = "insert into dev.pacientes" + "(num_prontuario,nome_paciente,idade,genero, nome_mae)" + " values (?,?,?,?,?)";

		if (validarPaciente(paciente)) {
			throw new Exception("Existem campos não preenchidos.");
		}

		try {
			/**
			 * Prepared Statement para inserir dados no Banco
			 */
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, paciente.getNumProntuario());
			stmt.setString(2, paciente.getNomePaciente());
			stmt.setInt(3, paciente.getIdade());
			stmt.setString(4, paciente.getGenero());
			stmt.setString(5, paciente.getNomeMae());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Paciente> getListaPacientes() throws SQLException {
		try {
			List<Paciente> listaPacientes = new ArrayList<Paciente>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from DEV.PACIENTES");
			/**
			 * Objeto do JDBC que permite navegar pelos registros
			 */
			ResultSet result = stmt.executeQuery();

			while (result.next()) {

				Paciente paciente = new Paciente();
				paciente.setIdPaciente(result.getInt("id_paciente"));
				paciente.setNumProntuario(result.getInt("num_prontuario"));
				paciente.setNomePaciente(result.getString("nome_paciente"));
				paciente.setIdade(result.getInt("idade"));
				paciente.setGenero(result.getString("genero"));
				paciente.setNomeMae(result.getString("nome_mae"));

				// adicionando o objeto à lista
				listaPacientes.add(paciente);
			}

			result.close();
			stmt.close();

			return listaPacientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
