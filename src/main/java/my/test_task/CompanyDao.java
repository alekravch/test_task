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
public class CompanyDao {

	@PersistenceContext(name="test_task")
	private EntityManager em;
	

	public CompanyDao() {

	}

	public List<Company> findAll() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Company> cq = cb.createQuery(Company.class).distinct(true);
		Root<Company> root = cq.from(Company.class);

		Fetch<Company, Address> address = root.fetch("address", JoinType.INNER);
		Fetch<Company, Branch> branches = root.fetch("branches", JoinType.LEFT);
		Fetch<Branch, Address> branchAddresses = branches.fetch("address", JoinType.LEFT);

		
		TypedQuery<Company> q = em.createQuery(cq);
		
		return q.getResultList();
	}
}
