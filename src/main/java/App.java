

import java.util.List;

public class App {
	public static void main(String[] args) {
		
		ParticipantDao ParticipantDao = new ParticipantDao();
		Participant Participant = new Participant("Juan", "Nazi");
		ParticipantDao.saveParticipant(Participant);
		
		UserDao UserDao = new UserDao();
		User User = new User("Ramesh", "contraseña12");
		UserDao.saveUser(User);
		
		User User1 = new User("Ram", "password34");
		UserDao.updateUser(User1);	
		
		List<User> Users = UserDao.getUsers();
		Users.forEach(s -> System.out.println(s.getName()));
		
		User User2 = UserDao.getUser(0);
		System.out.println(User2.getName());
		
		//UserDao.deleteUser(1);
	}
}