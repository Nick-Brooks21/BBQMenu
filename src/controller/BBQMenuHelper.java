package controller;

import model.FoodList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BBQMenuHelper {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BBQMenu");

	public void insertItem(FoodList fl) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(fl);
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteItem(FoodList delete) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<FoodList>typedQuery = em.createQuery("select fl from FoodList fl where fl.type = :selectedType and fl.type = :selectedType", FoodList.class);
	
		typedQuery.setParameter("selectedType", delete.getType());
		typedQuery.setParameter("selectedQuantity", delete.getQuantity());
		typedQuery.setMaxResults(1);
		FoodList result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateItem(FoodList edit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(edit);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<FoodList> showAll() {
		EntityManager em = emf.createEntityManager();
		List<FoodList> showAllFood = em.createQuery("SELECT i FROM FoodList i").getResultList();
		return showAllFood;
	}
	
	public List<FoodList>searchByType(String type) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<FoodList>typedQuery = em.createQuery("select fl from FoodList fl where fl.type = :selectedType", FoodList.class);
		typedQuery.setParameter("selectedType", type);
		
		List<FoodList> item = typedQuery.getResultList();
		em.close();
		return item;
	}
	
	public List<FoodList>searchByQuantity(int quantity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<FoodList> typedQuery = em.createQuery("select fl from FoodList fl where fl.quantity = :selectedQuantity", FoodList.class);
		typedQuery.setParameter("selectedQuantity", quantity);
		
		List<FoodList> item = typedQuery.getResultList();
		em.close();
		return item;
	}
	
	public FoodList searchById(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		FoodList found = em.find(FoodList.class,  id);
		em.close();
		return found;
	}
	
	public void cleanUp() {
		emf.close();
	}
}
