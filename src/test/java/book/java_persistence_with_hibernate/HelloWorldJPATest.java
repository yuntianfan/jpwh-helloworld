package book.java_persistence_with_hibernate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import book.java_persistence_with_hibernate.model.Message;

public class HelloWorldJPATest {
	
private EntityManagerFactory emf;
private EntityManager em;
private EntityTransaction transaction;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("HelloWorldPU");
		em = emf.createEntityManager();
		transaction = em.getTransaction();
		transaction.begin();
	}
	
	@Test
	public void test() {
		Message message = new Message();
		message.setText("Hello World!");
		em.persist(message);
		em.flush();
		em.clear();
		List<Message> messages = em.createQuery("select m from Message m", Message.class).getResultList();
		assertEquals(1, messages.size());
		assertEquals("Hello World!", messages.get(0).getText());
		messages.get(0).setText("Take me to your leader!");
	}
	
	@After
	public void tearDown() {
		transaction.commit();
		em.close();
		emf.close();
	}
}
