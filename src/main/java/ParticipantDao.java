

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ParticipantDao {
	public void saveParticipant(Participant Participant) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(Participant);
			
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
	
	public void insertParticipant() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			String hql = "INSERT INTO Participant (Name, Nickname) "
					+ "SELECT Name, Nickname FROM Participant";
			Query query = session.createQuery(hql);
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

	public void updateParticipant(Participant Participant) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the User object
			String hql = "UPDATE Participant set Name = :Name " + "WHERE Id = :ParticipantId";
			Query query = session.createQuery(hql);
			query.setParameter("Name", Participant.getName());
			query.setParameter("ParticipantId", 1);
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
			Participant Participant = session.get(Participant.class, Id);
			if (Participant != null) {
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
		Participant Participant = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an User object
			String hql = " FROM Participant T WHERE T.Id = :ParticipantId";
			Query query = session.createQuery(hql);
			query.setParameter("ParticipantId", Id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				Participant = (Participant) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return Participant;
	}

	public List<Participant> getParticipants() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Participant", Participant.class).list();
		}
	}
}
