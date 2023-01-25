package my.test_task;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import my.test_task.entities.Address;
import my.test_task.entities.Branch;
import my.test_task.entities.Company;

@Stateless
public class AddressDao {

	@PersistenceContext(name="test_task")
	private EntityManager em;
	

	public AddressDao() {

	}

	public List<Address> findAll() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Address> cq = cb.createQuery(Address.class).distinct(true);
		Root<Address> root = cq.from(Address.class);

		Fetch<Address, Company> address = root.fetch("company", JoinType.LEFT);
		Fetch<Address, Branch> branches = root.fetch("branch", JoinType.LEFT);
		
		TypedQuery<Address> q = em.createQuery(cq);
		
		return q.getResultList();
	}
}
