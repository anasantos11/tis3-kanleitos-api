package Conexoes;


import java.util.ArrayList;
import java.util.List;

import Modelos.Paciente;
import Util.Metodos;
import Dao.PacienteDao;


public class TesteConexao {
	private static Metodos metodos = Metodos.getInstance();
	
	public static void main(String[] args) throws Exception {
		
		PacienteDao dao = new PacienteDao();
		/**
		 * Teste para inserir paciente
		 */
		/*Paciente paciente = new Paciente();
		paciente.setNomePaciente("Wellington Polt");
		paciente.setNumProntuario(572444);
		paciente.setNomeMae("Maria de Souza");
		paciente.setGenero("Masculino");
		paciente.setIdade(25);
		paciente.setDataNascimento(metodos.stringParseCalendar("01/08/1992"));
		dao.inserirPaciente(paciente);
		System.out.println("Gravado! \n");*/
		
		/**
		 * Teste para listar pacientes cadastrados
		 */
		
		List<Paciente> listaPacientes = new ArrayList<Paciente>();
		listaPacientes = dao.getListaPacientes();
		
		System.out.println("Lista de pacientes cadastrados: \n");
		listaPacientes.forEach(x -> {
			System.out.println(x.toString());
			
		});
		
	
		
		/*String sql = "select * from DEV.PACIENTES";
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
		}*/

	}

}
