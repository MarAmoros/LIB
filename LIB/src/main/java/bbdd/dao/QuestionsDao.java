package bbdd.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bbdd.entity.Questions;
import bbdd.util.HibernateUtil;

public class QuestionsDao {

	public void saveQuestions(Questions questions) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// operation 1
			Object object = session.save(questions);

			// operation 2
			session.get(Questions.class, (Serializable) object);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateQuestions(Questions questions) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the Questions object

			session.saveOrUpdate(questions);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Questions getQuestions(int id) {

		Transaction transaction = null;
		Questions Questions2 = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an Questions object
			Questions2 = session.get(Questions.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return Questions2;
	}

	public List<Questions> getQuestionss() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Questions", Questions.class).list();
		}
	}
}