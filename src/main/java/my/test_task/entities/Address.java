package my.test_task.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Optional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import static javax.persistence.FetchType.LAZY;

@Entity
@NamedQuery(name=Address.FIND_ALL, query = "SELECT DISTINCT a FROM Address a LEFT JOIN FETCH a.company LEFT JOIN FETCH a.branch")
public class Address extends DbEntity implements Serializable {

	public static final String FIND_ALL = "Address.findAll";
	
	private String index;
	private String city;
	private String street;
	private String buildingNo;
	private Branch branch;
	private Company company;
	private static final long serialVersionUID = 1L;

	public Address() {
		super();
	}   
	public String getIndex() {
		return this.index;
	}

	public void setIndex(String index) {
		this.index = index;
	}   
	
	@NotNull
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}   
	
	@NotNull
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	@NotNull
	@Column(name = "building_no")
	public String getBuildingNo() {
		return this.buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	
	@OneToOne(mappedBy = "address", optional = true)
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	@OneToOne(mappedBy = "address", optional = true)
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Transient
	public String getHolderDesc() {
		return Optional.<DbEntity>ofNullable(this.getCompany()).orElse(this.getBranch()).getDesc();
	}
   
}
