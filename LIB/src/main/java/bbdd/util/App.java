package bbdd.util;

import bbdd.dao.AdminDao;
import bbdd.dao.UserDao;
import bbdd.entity.Admin;
import bbdd.entity.User;

public class App {
	public static void main(String[] args) {

		// Insert Admins
		AdminDao adminDao = new AdminDao();
		Admin admin = new Admin("Ram", "contraseña12");
		Admin admin1 = new Admin("Marta", "gato1234");
		Admin admin2 = new Admin("Laura", "burro5678");
		Admin admin3 = new Admin("Carmen", "camello0987");
		adminDao.saveAdmin(admin);
		adminDao.saveAdmin(admin1);
		adminDao.saveAdmin(admin2);
		adminDao.saveAdmin(admin3);

		// Insert Users
		UserDao userDao = new UserDao();
		User user = new User("usu1", "contraseña12");
		User user2 = new User("usu2", "gato1234");
		User user3 = new User("usu3", "burro5678");
		User user4 = new User("usu4", "camello0987");
		userDao.saveUser(user4);
		userDao.saveUser(user);
		userDao.saveUser(user3);
		userDao.saveUser(user2);
//
//		ParticipantDao pDao = new ParticipantDao();
//		Participant p = new Participant("Tom");
//		pDao.saveParticipant(p);
		// Login Frame
//		LoginFrame loginFrame = new LoginFrame();
//		loginFrame.setVisible(true);

	}
}