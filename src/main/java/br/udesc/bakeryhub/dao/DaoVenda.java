package br.udesc.bakeryhub.dao;

import br.udesc.bakeryhub.entidades.Venda;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class DaoVenda extends Dao {

    public boolean inserir(Venda v) {
        try {
            em.getTransaction().begin();
            em.persist(v);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean excluir(Venda v) {
        try {
            em.getTransaction().begin();
            em.remove(v);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Venda v) {
        try {
            em.getTransaction().begin();
            em.merge(v);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public List<Venda> ListarMesAno(int mes, int ano) {
        TypedQuery<Venda> query = em.createQuery("SELECT v FROM Venda v WHERE FUNCTION('MONTH', v.data) = :mes AND FUNCTION('YEAR', v.data) = :ano", Venda.class);
        query.setParameter("mes", mes);
        query.setParameter("ano", ano);
        return query.getResultList();
    }

    public List<Venda> Listar() {
        return em.createQuery("select v from Venda v").getResultList();
    }
}
