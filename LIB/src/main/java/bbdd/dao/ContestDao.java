package bbdd.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bbdd.entity.Contest;
import bbdd.util.HibernateUtil;

public class ContestDao {

	public void saveContest(Contest contest) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// operation 1
			Object object = session.save(contest);

			// operation 2
			session.get(Contest.class, (Serializable) object);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateContest(Contest contest) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the Contest object

			session.saveOrUpdate(contest);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Contest getContest(int id) {

		Transaction transaction = null;
		Contest Contest2 = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an Contest object
			Contest2 = session.get(Contest.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return Contest2;
	}

	public List<Contest> getContests() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Contest", Contest.class).list();
		}
	}
}