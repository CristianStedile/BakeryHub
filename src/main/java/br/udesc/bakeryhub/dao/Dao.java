package br.udesc.bakeryhub.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Dao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BakeryHubPU");
    EntityManager em = emf.createEntityManager();
}
