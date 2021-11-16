package bbdd.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bbdd.entity.Participant;
import bbdd.util.HibernateUtil;

public class ParticipantDao {

	public void saveParticipant(Participant participant) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// operation 1
			Object object = session.save(participant);

			// operation 2
			session.get(Participant.class, (Serializable) object);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateParticipant(Participant participant) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the User object
			String hql = "UPDATE Participant set Alias = :Alias " + "WHERE Id = :ParticipantId";
			Query query = session.createQuery(hql);
			query.setParameter("UserId", participant.getId());
			query.setParameter("New Alias", participant.getAlias());
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

	public void deleteParticipant(int Id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a User object
			Participant participant = session.get(Participant.class, Id);
			if (participant != null) {
				String hql = "DELETE FROM Participant " + "WHERE Id = :ParticipantId";
				Query query = session.createQuery(hql);
				query.setParameter("ParticipantId", Id);
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

	public Participant getParticipant(int Id) {

		Transaction transaction = null;
		Participant participant = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an User object
			String hql = " FROM Participant S WHERE S.Id = :ParticipantId";
			Query query = session.createQuery(hql);
			query.setParameter("ParticipantId", Id);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				participant = (Participant) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return participant;
	}

	public List<Participant> getParticipants() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Participant", Participant.class).list();
		}
	}
}