package br.com.mag.teste;


import javax.persistence.EntityManager;
import br.com.mag.business.Visita;
import br.com.mag.util.JPAUtil;

public class TesteCriacao {

	public static void main(String[] args) {
		

		Visita visita = new Visita();
		
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();


		manager.persist(visita);


		manager.getTransaction().commit();
		manager.close();
	}
	
}
