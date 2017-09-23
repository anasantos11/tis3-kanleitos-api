package Dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.transaction.UserTransaction;

import Model.Paciente;

public class DaoPaciente{
	private static 	List<Paciente> emps = new ArrayList<Paciente>();
	
	
	public static void cadastrarPaciente(Paciente p, EntityManager em) throws ServletException {
		try {      
			em.persist(p);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	public static List<Paciente> listarPacientes(EntityManager em) throws ServletException {
		try {
			//ut.begin();
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Paciente> criteria = builder.createQuery(Paciente.class);
			criteria.from(Paciente.class);
			emps = em.createQuery(criteria).getResultList();
			//ut.commit();
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		return emps;
	}
}
