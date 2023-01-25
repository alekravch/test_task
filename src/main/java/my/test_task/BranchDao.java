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
public class BranchDao {

	@PersistenceContext(name="test_task")
	private EntityManager em;
	

	public BranchDao() {

	}

	public List<Branch> findAll() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Branch> cq = cb.createQuery(Branch.class).distinct(true);
		Root<Branch> root = cq.from(Branch.class);

		Fetch<Branch, Address> address = root.fetch("address", JoinType.INNER);
		Fetch<Branch, Company> company = root.fetch("company", JoinType.INNER);
		
		TypedQuery<Branch> q = em.createQuery(cq);
		
		return q.getResultList();
	}
}
