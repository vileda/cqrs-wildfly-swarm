package donatr.eventstore;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.*;

@Stateless
public class EventRepository {
	@PersistenceContext(unitName = "MyPU", type = PersistenceContextType.TRANSACTION)
	EntityManager entityManager;

	public void save(Object event) {
		try {
			UserTransaction userTxn = getUserTransaction();
			userTxn.begin();
			entityManager.persist(event);
			userTxn.commit();
		} catch (NotSupportedException | SystemException | HeuristicRollbackException
				| HeuristicMixedException | RollbackException | NamingException e) {
			String message = String.format("error persisting %s %s %s", event, e.getMessage(), e.getLocalizedMessage());
			e.printStackTrace();
			throw new RuntimeException(message);
		}
	}

	private UserTransaction getUserTransaction() throws NamingException {
		return (UserTransaction) new InitialContext().lookup("java:jboss/UserTransaction");
	}
}