package br.udesc.bakeryhub.dao;

import br.udesc.bakeryhub.entidades.Cliente;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoCliente extends Dao {

    public boolean inserir(Cliente c) {
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean excluir(Cliente c) {
        try {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Cliente c) {
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public List<Cliente> listarNome(String nome) {
        return em.createQuery("select c from Cliente c where c.nome = :nome").setParameter("nome", nome).getResultList();
    }

    public List<Cliente> Listar() {
        return em.createQuery("select c from Cliente c").getResultList();
    }
}
