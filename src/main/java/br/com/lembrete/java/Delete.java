package br.com.lembrete.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Delete {

    private static EntityManagerFactory entityManagerFactory;


    public static EntityManager getEntityManager(){
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        }

        return entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) {

        EntityManager em = getEntityManager();

        try {

            Lembrete lembrete = em.find(Lembrete.class, 2L);

            em.getTransaction().begin();
            em.remove(lembrete);
            em.getTransaction().commit();

        }catch (Exception e){
            em.getTransaction().rollback();

            System.out.println("DELETE: " + e.getMessage());
        }finally {
            em.close();
        }

        entityManagerFactory.close();
    }
}
