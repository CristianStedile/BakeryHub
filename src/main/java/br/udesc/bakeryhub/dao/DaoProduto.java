package br.udesc.bakeryhub.dao;

import br.udesc.bakeryhub.entidades.Produto;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoProduto extends Dao {

    public boolean inserir(Produto p) {
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean excluir(Produto p) {
        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Produto p) {
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public List<Produto> listarNome(String nome) {
        return em.createQuery("select p from Produto p where p.nome = :nome").setParameter("nome", nome).getResultList();
    }

    public List<Produto> Listar() {
        return em.createQuery("select p from Produto p").getResultList();
    }
}
