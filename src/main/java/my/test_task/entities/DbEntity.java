package my.test_task.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Objects;

import javax.persistence.*;


@MappedSuperclass
public class DbEntity implements Serializable {

	   

	private Integer id;
	private static final long serialVersionUID = 1L;

	public DbEntity() {
		super();
	}
	
	@Id
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Transient
	public String getDesc() {
		return "";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DbEntity other = (DbEntity) obj;
		return Objects.equals(id, other.id);
	}
   
}
