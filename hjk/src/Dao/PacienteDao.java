package Dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import Model.Paciente;
 
public class PacienteDao {
         private static PacienteDao instance;
         
     	@Resource
    	private UserTransaction ut;
     	
    	@PersistenceContext
    	protected EntityManager entityManager;
         
         public static PacienteDao getInstance(){
                   if (instance == null){
                            instance = new PacienteDao();
                   }
                   return instance;
         }
 
         private PacienteDao() {
                   entityManager = getEntityManager();
         }
 
         private EntityManager getEntityManager() {
                   EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectTest");
                   if (entityManager == null) {
                            entityManager = factory.createEntityManager();
                   }
 
                   return entityManager;
         }
         
         /**
          * Método para listar todos os pacientes cadastrados
          * @return
          */
         
         public List<Paciente> lista(){
     	    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
     	    CriteriaQuery<Paciente> criteria = builder.createQuery(Paciente.class);
     	    criteria.from(Paciente.class);
     	    return entityManager.createQuery(criteria).getResultList();
     	  }
         
         public Paciente getById(final int idPaciente) {
                   return entityManager.find(Paciente.class, idPaciente);
         }
 
         @SuppressWarnings("unchecked")
         public List<Paciente> findAll() throws NotSupportedException, SystemException {
                   return entityManager.createQuery("FROM " + Paciente.class.getName()).getResultList();
         }
         
         public void persist(Paciente Paciente) {
                   try {
                            entityManager.persist(Paciente);
                            entityManager.getTransaction().commit();
                   } catch (Exception ex) {
                            ex.printStackTrace();
                            entityManager.getTransaction().rollback();
                   }
         }
 
         public void merge(Paciente Paciente) {
                   try {
                            entityManager.getTransaction().begin();
                            entityManager.merge(Paciente);
                            entityManager.getTransaction().commit();
                   } catch (Exception ex) {
                            ex.printStackTrace();
                            entityManager.getTransaction().rollback();
                   }
         }
 
         public void remove(Paciente Paciente) {
                   try {
                            entityManager.getTransaction().begin();
                            Paciente = entityManager.find(Paciente.class, Paciente.getIdPaciente());
                            entityManager.remove(Paciente);
                            entityManager.getTransaction().commit();
                   } catch (Exception ex) {
                            ex.printStackTrace();
                            entityManager.getTransaction().rollback();
                   }
         }
 
         public void removeById(final int idPaciente) {
                   try {
                            Paciente Paciente = getById(idPaciente);
                            remove(Paciente);
                   } catch (Exception ex) {
                            ex.printStackTrace();
                   }
         }
 
}