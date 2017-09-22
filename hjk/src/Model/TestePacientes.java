package Model;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import com.sun.xml.internal.stream.Entity;

/**
 * Servlet implementation class Pacientes
 */
@WebServlet("/pacientes")
public class TestePacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource
	private UserTransaction ut;

	@PersistenceContext
	private EntityManager em;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Paciente p = new Paciente();
		p.setDataNascimento(null);
		p.setGenero("Feminino");
		p.setIdade(21);
		p.setNomeMae("Maria do Rosário");
		p.setNomePaciente("Ana Paula dos Santos");
		p.setNumProntuario(570000);

		try {
			ut.begin();
			em.persist(p);
			ut.commit();
		} catch (Exception e) {
			throw new ServletException(e);
		}
		response.getWriter().append("Paciente criado com id = " + p.getIdPaciente() + "\n" + p.toString());
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
