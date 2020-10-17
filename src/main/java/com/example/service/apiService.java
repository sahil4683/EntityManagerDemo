package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entiry.UserEntity;

@Service
@Repository
public class apiService {

	@PersistenceContext
	EntityManager manager;
	
	@Transactional
	public UserEntity save(UserEntity entity) {
		manager.persist(entity);
		return entity;
	}
	@Transactional(readOnly=true)
	public List<UserEntity> findAll() {
		CriteriaBuilder builder=manager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query=builder.createQuery(UserEntity.class);
		Root<UserEntity> root=query.from(UserEntity.class);
		query.select(root);
		try {
			return manager.createQuery(query).getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Transactional(readOnly = true)
	public UserEntity findById(String id) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
		try {
			return manager.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Transactional(readOnly = true)
	public UserEntity findByName(String name) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("Name"), name.trim()));
		try {
			return manager.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public Long totalRecordCount() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		query.select(builder.count(query.from(UserEntity.class)));
		//cq.where(/*your stuff*/);
		return manager.createQuery(query).getSingleResult();	
	}
	
//	@Transactional(readOnly = true)
//	public List<UserEntity> search(String searchValue) {
//		//Get the FullTextEntityManager
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(manager);
// 
//        //Create a Hibernate Search DSL query builder for the required entity
//        org.hibernate.search.query.dsl.QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder()
//                .forEntity(UserEntity.class)
//                .get();
// 
//        //Generate a Lucene query using the builder
//        org.apache.lucene.search.Query query = queryBuilder
//                .keyword().wildcard().onFields("Name").ignoreFieldBridge().matching(searchValue).createQuery();
// 
//        org.hibernate.search.jpa.FullTextQuery fullTextQuery
//                = fullTextEntityManager.createFullTextQuery(query, UserEntity.class);
// 
//        //returns JPA managed entities
//        List<UserEntity> results = fullTextQuery.getResultList();
//        return results;
//	}

}
