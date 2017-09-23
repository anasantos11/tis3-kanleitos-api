package Servicos;

import java.sql.SQLException;
import java.time.LocalDate;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.Query;

import Dao.DaoPaciente;
import Dao.PacienteDao;
import Model.Paciente;
import Model.Pacientes;

@Path("/paciente")
public class PacienteResourceE {

	private static Pacientes p;
	@Resource
	private UserTransaction ut;

	@PersistenceContext
	private EntityManager em;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	
	public Pacientes getTodosClientes() throws ServletException {
		System.out.println("teste");

		try {
			ut.begin();
			p = new Pacientes(DaoPaciente.listarPacientes(em));
		} catch (Exception e) {
			throw new ServletException(e);
		}
		return p;
	}

	@POST
	@Path("/cadastro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void cadastrarPaciente() throws ServletException {


		LocalDate d = null;
		d.of(2017, 03, 11);


		Paciente p = new Paciente();
		p.setDataNascimento(d);
		/*p.setGenero(sexo);
		p.setIdade(Integer.parseInt(idade));
		p.setNomeMae(nomeMae);
		p.setNomePaciente(nome);
		p.setNumProntuario(Integer.parseInt(pront));*/

		PacienteDao dao = PacienteDao.getInstance();
		try {
			ut.begin();
			dao.persist(p);
			ut.commit();

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
/*
@QueryParam("nome") String nome, 
@QueryParam("pront") String pront,
@QueryParam("nomeMae") String nomeMae,
@QueryParam("idade") String idade,
@QueryParam("sexo") String sexo*/