import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="Participant")
public class Participant {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int Id;
	
	@Column(name="Name")
	private String Name;
	
	@Column(name="Nickname")
	private String Nickname;
	
	public Participant() {
		
	}
	
	public Participant(String firstName, String Nickname) {
		this.Name = firstName;
		this.Nickname = Nickname;
	}

	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	@Override
	public String toString() {
		return "Participant [id=" + Id + ", Name=" + Name + ", lastName=" + Nickname + "]";
	}
}
