package Servicos;

import java.sql.SQLException;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Dao.PacienteDao;
import Model.Pacientes;


@Path("/paciente")
public class PacienteResource {
	
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Pacientes getTodosClientes() throws SQLException {
		PacienteDao dao = PacienteDao.getInstance();
		Pacientes p = new Pacientes(dao.lista());

		return p;
	}
}
