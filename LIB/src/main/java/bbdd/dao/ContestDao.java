package bbdd.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

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

//	public void updateContest(Contest contest) {
//		Transaction transaction = null;
//		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//			// start a transaction
//			transaction = session.beginTransaction();
//
//			// save the User object
//			String hql = "UPDATE Participant set Alias = :Alias " + "WHERE Id = :ParticipantId";
//			Query query = session.createQuery(hql);
//			query.setParameter("UserId", contest.getId());
//			query.setParameter("New Alias", contest.getAlias());
//			int result = query.executeUpdate();
//			System.out.println("Rows affected: " + result);
//
//			// commit transaction
//			transaction.commit();
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		}
//	}

	public void deleteContest(int Id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a User object
			Contest participant = session.get(Contest.class, Id);
			if (participant != null) {
				String hql = "DELETE FROM Contest " + "WHERE Id = :ContestId";
				Query query = session.createQuery(hql);
				query.setParameter("ContestId", Id);
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

	public Contest getContest(int Id) {

		Transaction transaction = null;
		Contest contest = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an User object
			String hql = " FROM Contest S WHERE S.Id = :ContestId";
			Query query = session.createQuery(hql);
			query.setParameter("ParticipantId", Id);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				contest = (Contest) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return contest;
	}

	public List<Contest> getContests() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Contest", Contest.class).list();
		}
	}
}