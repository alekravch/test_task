package my.test_task.entities;

import my.test_task.grid.*;

import java.io.Serializable;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import static javax.persistence.FetchType.LAZY;


@Entity
@NamedQuery(name=Company.FIND_ALL, query = "SELECT DISTINCT c FROM Company c JOIN FETCH c.address LEFT JOIN FETCH c.branches b LEFT JOIN FETCH b.address")
public class Company extends DbEntity implements Serializable {

	public static final String FIND_ALL = "Company.findAll";
	
	private String name;
	private String type;
	private Address address;
	private Set<Branch> branches;
	private static final long serialVersionUID = 1L;

	public Company() {
		super();
	}   
	
	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "company")
	public Set<Branch> getBranches() {
		return branches;
	}
	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}

	@NotNull
	@OneToOne
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	@Transient
	public String getDesc() {
		return String.format("Компания %s (%s)", this.getName(), this.getType());
	}
}
