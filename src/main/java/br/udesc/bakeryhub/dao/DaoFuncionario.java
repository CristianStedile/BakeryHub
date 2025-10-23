package br.udesc.bakeryhub.dao;

import br.udesc.bakeryhub.entidades.Funcionario;
import java.util.List;
import javax.persistence.PersistenceException;

public class DaoFuncionario extends Dao{

        public boolean inserir(Funcionario f) {
            try {
                em.getTransaction().begin();
                em.persist(f);
                em.getTransaction().commit();
                return true;
            } catch (PersistenceException e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                return false;
            }
        }

        public boolean excluir(Funcionario f) {
            try {
                em.getTransaction().begin();
                em.remove(f);
                em.getTransaction().commit();
                return true;
            } catch (PersistenceException e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                return false;
            }
        }

        public boolean editar(Funcionario f) {
            try {
                em.getTransaction().begin();
                em.merge(f);
                em.getTransaction().commit();
                return true;
            } catch (PersistenceException e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                return false;
            }
        }
        
        public List<Funcionario> listarNome(String nome) {
            return em.createQuery("select f from Funcionario f where f.nome = :nome").setParameter("nome", nome).getResultList();
        }

        public List<Funcionario> Listar() {
            return em.createQuery("select f from Funcionario f").getResultList();
        }
  
}
