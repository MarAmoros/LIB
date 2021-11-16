package bbdd.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bbdd.entity.Admin;
import bbdd.util.HibernateUtil;

public class AdminDao {

	public void saveAdmin(Admin admin) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// operation 1
			Object object = session.save(admin);

			// operation 2
			session.get(Admin.class, (Serializable) object);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateAdmin(Admin admin) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the Admin object
			String hql = "UPDATE Admin set Name = :Name " + "WHERE Id = :AdminId";
			Query query = session.createQuery(hql);
			query.setParameter("Name", admin.getName());
			query.setParameter("AdminId", 1);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteAdmin(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a Admin object
			Admin Admin = session.get(Admin.class, id);
			if (Admin != null) {
				String hql = "DELETE FROM Admin " + "WHERE Id = :AdminId";
				Query query = session.createQuery(hql);
				query.setParameter("AdminId", id);
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Admin getAdmin(int id) {

		Transaction transaction = null;
		Admin Admin = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an Admin object
			String hql = " FROM Admin S WHERE S.Id = :AdminId";
			Query query = session.createQuery(hql);
			query.setParameter("AdminId", id);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				Admin = (Admin) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return Admin;
	}

	public List<Admin> getAdmins() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Admin", Admin.class).list();
		}
	}
}