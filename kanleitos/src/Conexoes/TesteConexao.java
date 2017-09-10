package Conexoes;


import java.util.ArrayList;
import java.util.List;

import Bases.Paciente;
import Dao.PacienteDao;


public class TesteConexao {
	public static void main(String[] args) throws Exception {

		PacienteDao dao = new PacienteDao();
		Paciente paciente = new Paciente(572401, "Luiz Henriqiue", 30, "Trans");
		dao.inserirPaciente(paciente);
		System.out.println("Gravado! \n");
		
		
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
