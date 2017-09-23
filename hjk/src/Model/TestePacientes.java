package Model;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.transaction.UserTransaction;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import Dao.DaoPaciente;

/**
 * Servlet implementation class Pacientes
 *
 */

@WebServlet("/pacientes")
public class TestePacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Paciente> lista = new ArrayList<Paciente>();
	private static JSONObject jObj = null;

	@Resource
	private UserTransaction ut;

	@PersistenceContext
	private EntityManager em;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		int prontuario = Integer.parseInt(request.getParameter("pront"));
		String nomeMae = request.getParameter("nomeMae");
		int idade = Integer.parseInt(request.getParameter("idade"));
		LocalDate d = null;
		d.of(2017, 03, 11);
		String sexo = request.getParameter("sexo");

		Paciente p = new Paciente();
		p.setDataNascimento(d);
		p.setGenero(sexo);
		p.setIdade(idade);
		p.setNomeMae(nomeMae);
		p.setNomePaciente(nome);
		p.setNumProntuario(prontuario);

		try {
			ut.begin();
			DaoPaciente.cadastrarPaciente(p, em);
			ut.commit();
		} catch (Exception e) {
			throw new ServletException(e);
		}

		try {
			ut.begin();
			lista = DaoPaciente.listarPacientes(em);
		} catch (Exception e) {
			throw new ServletException(e);
		}

		lista.forEach(x -> {
			try {
				response.getWriter().append(x.toString() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}

		/*
		 * Gson gson = new Gson(); gson.fromJson(sb.toString(), Paciente.class);
		 */

		try {
			jObj = new JSONObject(sb.toString());
			Paciente p = new Paciente();
			/*LocalDateTime ldt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").
                    parseLocalDate("1991-02-01T02:00:00.000Z")*/
			
			//Transformando Data
            String data = jObj.getString("DataNascimento");
            String array1[] = new String[1];
            array1 = data.split("T");
            String array2[] = new String[3];
            array2 = array1[0].split("-");
			LocalDate dateNasc = LocalDate.of(Integer.parseInt(array2[0]), Integer.parseInt(array2[1]), Integer.parseInt(array2[2]));
			
			p.setDataNascimento(dateNasc);
			p.setGenero(jObj.getString("Genero"));
			p.setIdade(Integer.parseInt(jObj.getString("Idade")));
			p.setNomeMae(jObj.getString("NomeMae"));
			p.setNomePaciente(jObj.getString("NomePaciente"));
			p.setNumProntuario(Integer.parseInt(jObj.getString("NumProntuario")));

			try {
				ut.begin();
				DaoPaciente.cadastrarPaciente(p, em);
				ut.commit();
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				throw new ServletException(e);
			}

		} catch (JSONException e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		/*request.getSession().setAttribute("sucesso",
                "OK");*/

	}

}

// try {
// ut.begin();
// CriteriaBuilder builder = em.getCriteriaBuilder();
// CriteriaQuery<Paciente> criteria = builder.createQuery(Paciente.class);
// criteria.from(Paciente.class);
// List<Paciente> emps = em.createQuery(criteria).getResultList();
// // Collection emps = em.createQuery("SELECT e FROM PACIENTE
// e").getResultList();
// for (Iterator i = emps.iterator(); i.hasNext();) {
// Paciente e = (Paciente) i.next();
// response.getWriter().append(e.toString() + "/n");
// }
// ut.commit();
// } catch (Exception e) {
// throw new ServletException(e);
// }



/*
 * String nome = request.getParameter("nome"); int prontuario =
 * Integer.parseInt(request.getParameter("pront")); String nomeMae =
 * request.getParameter("nomeMae"); int idade =
 * Integer.parseInt(request.getParameter("idade")); LocalDate d = null;
 * d.of(2017, 03, 11); String sexo = request.getParameter("sexo");
 * 
 * Paciente p = new Paciente(); p.setDataNascimento(d); p.setGenero(sexo);
 * p.setIdade(idade); p.setNomeMae(nomeMae); p.setNomePaciente(nome);
 * p.setNumProntuario(prontuario);
 */

