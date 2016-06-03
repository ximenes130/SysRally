package hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface InterfaceWorker<T, ID extends Serializable> {
	public boolean inclui(T registro);

	public boolean exclui(T registro);

	public boolean altera(T registro);

	public Object consulta(ID id);

	public List<T> listar(Criterion clausula[]);
}
