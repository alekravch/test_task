package my.test_task.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@NamedQuery(name=Branch.FIND_ALL, query = "SELECT DISTINCT b FROM Branch b JOIN FETCH b.address JOIN FETCH b.company c JOIN FETCH c.address")
public class Branch extends DbEntity implements Serializable {

	public static final String FIND_ALL = "Branch.findAll";
	
	private String name;
	private Address address;
	private Company company;
	private static final long serialVersionUID = 1L;

	public Branch() {
		super();
	}
	
	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	
	@NotNull
	@OneToOne
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}   
	
	@NotNull
	@ManyToOne(optional = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	@Transient
	public String getDesc() {
		return String.format("Филиал %s копании %s (%s)", this.getName(), this.getCompany().getName(), this.getCompany().getType());
	}
   
}
