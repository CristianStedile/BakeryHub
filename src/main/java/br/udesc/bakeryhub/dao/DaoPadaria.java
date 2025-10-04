package br.udesc.bakeryhub.dao;

import br.udesc.bakeryhub.entidades.Padaria;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoPadaria extends Dao {

    public boolean inserir(Padaria p) {
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

    public boolean excluir(Padaria p) {
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

    public boolean editar(Padaria p) {
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
    
    public List<Padaria> Listar(){
        return em.createQuery("select p from Padaria p").getResultList();
    }
}
