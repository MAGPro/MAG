package br.com.mag.business.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javassist.SerialVersionUID;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.mag.business.AbstractEntity;
import br.com.mag.util.JPAUtil;

public abstract class GenericDAO<T extends AbstractEntity> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	private Class<T> persistentClass;

	public GenericDAO() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		persistentClass = (Class<T>) type.getActualTypeArguments()[0];
		entityManager = new JPAUtil().getEntityManager();
	}

	public void salvar(T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}
	
	public void editar(T entity){
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}
	
	public void excluir(T entity){
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.getReference(persistentClass, entity.getId()));
		entityManager.getTransaction().commit();
	}
	
	public List<T> listarTodos(){
		Query query = entityManager.createQuery("Select t from " + persistentClass.getName() + " t");
		List<T> lista = query.getResultList();
		return lista;
	}
	
	public T getPrimaryKey(T entity){
		return entityManager.find(persistentClass, entity.getId());
	}
	
	/*public T getPrimaryKey(K key){
		return entityManager.find(persistentClass, key);
	}*/

	public EntityManager getEntityManager(){
		return entityManager;
	}
}
