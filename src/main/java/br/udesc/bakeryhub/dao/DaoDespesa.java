package br.udesc.bakeryhub.dao;

import br.udesc.bakeryhub.entidades.Despesa;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class DaoDespesa extends Dao {

    public boolean inserir(Despesa d) {
        try {
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean excluir(Despesa d) {
        try {
            em.getTransaction().begin();
            em.remove(d);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Despesa d) {
        try {
            em.getTransaction().begin();
            em.merge(d);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public List<Despesa> ListarMesAno(int mes, int ano) {
        TypedQuery<Despesa> query = em.createQuery("SELECT d FROM Despesa d WHERE FUNCTION('MONTH', d.data) = :mes AND FUNCTION('YEAR', d.data) = :ano", Despesa.class);
        query.setParameter("mes", mes);
        query.setParameter("ano", ano);
        return query.getResultList();
    }

    public List<Despesa> Listar() {
        return em.createQuery("select d from Despesa d").getResultList();
    }
}
