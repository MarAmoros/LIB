public class App {
	public static void main(String[] args) {
		UserDao UserDao = new UserDao();
		User User1 = new User("Ram", "password34");
		UserDao.saveUser(User1);
	}
}