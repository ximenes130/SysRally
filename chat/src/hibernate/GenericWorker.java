package hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.exception.ConstraintViolationException;

public class GenericWorker<T, ID extends Serializable> implements
		InterfaceWorker<T, ID> {
	private Session session;
	public Class<T> entidade;

	public GenericWorker(Class<T> entidade) {
		//Abre uma sessão nova.
		session = SessaoFactory.getSessionFactory().openSession();
		//Abre uma sessão existente. Melhor que openSession se já existir.
		//session = SessaoFactory.getSessionFactory().getCurrentSession();
		this.entidade = entidade;
	}

	@Override
	public void finalize()  {
		if (session != null) {
			session.flush();
			session.clear();
			session.close();
		}
		try {
			super.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public boolean inclui(T registro) throws HibernateException,
			ConstraintViolationException {

		Transaction tx = session.beginTransaction();
		try {
			session.save(registro);
			tx.commit();
		} catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			throw new ConstraintViolationException(
					"Falha de inclusão: Objeto já existe.", null,
					"Registro duplicado.");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Falha de inclusão no BD: ", e);
		} finally {
			//session.close();
		}
		return true;
	}

	public boolean exclui(T registro) throws HibernateException,
			ObjectNotFoundException {
		Transaction tx = session.beginTransaction();
		try {
			session.delete(registro);
			tx.commit();
		} catch (ObjectNotFoundException e) {
			if (tx != null)
				tx.rollback();
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado.",
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Falha de exclusão no BD: ", e);
		} finally {
			//session.close();
		}
		return true;
	}

	public boolean altera(T registro) throws HibernateException,
			ObjectNotFoundException {
		Transaction tx = session.beginTransaction();
		try {
			session.update(registro);
			tx.commit();
		} catch (StaleStateException e) {
			if (tx != null)
				tx.rollback();
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado ",
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Falha de alteração no BD: ", e);
		} finally {
			//session.close();
		}
		return true;
	}

	public Object consulta(ID id) throws HibernateException,
			ObjectNotFoundException {
		Object registro;
		try {
			registro = session.get(entidade, id);
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado ",
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			//session.close();
		}
		return registro;
	}

	@SuppressWarnings("unchecked")
	public List<T> listar(int inicio, int quantia) throws HibernateException {
		List<T> listagem;
		try {
			listagem = session.createCriteria(entidade).setMaxResults(quantia)
					.setFirstResult(inicio).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			//session.close();
		}
		return listagem;
	}

	@SuppressWarnings("unchecked")
	public List<T> listar(Criterion clausula[]) throws HibernateException {
		List<T> listagem;
		try {
			switch (clausula.length) {
			case 1:
				listagem = session.createCriteria(this.entidade)
						.add(clausula[0]).list();
				break;
			case 2:
				listagem = session.createCriteria(this.entidade)
						.add(clausula[0]).add(clausula[1]).list();
				break;
			case 3:
				listagem = session.createCriteria(this.entidade)
						.add(clausula[0]).add(clausula[1]).add(clausula[2])
						.list();
				break;
			case 4:
				listagem = session.createCriteria(this.entidade)
						.add(clausula[0]).add(clausula[1]).add(clausula[2])
						.add(clausula[3]).list();
				break;
			case 5:
				listagem = session.createCriteria(this.entidade)
						.add(clausula[0]).add(clausula[1]).add(clausula[2])
						.add(clausula[3]).add(clausula[4]).list();
				break;
			default:
				listagem = session.createCriteria(this.entidade).list();
			}
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			//session.close();
		}
		return listagem;
	}
}
