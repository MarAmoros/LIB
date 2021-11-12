import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private int Id;
	
	@Column(name="Name")
	private String Name;
	
	@Column(name="Password")
	private String Password;
	
	
	public User() {
		
	}

	public User(String Name, String Password) {
		this.Name = Name;
		this.Password = Password;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	@Override
	public String toString() {
		return "User [id=" + Id + ", Name=" + Name + ", Password=" + Password + "]";
	}
	
}