package br.com.lembrete.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager getEntityManager(){
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) {


        Lembrete lembrete = new Lembrete();
        lembrete.setTitulo("Restaurante");
        lembrete.setDescricao("Ir ao restaurante pagar a conta");

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(lembrete);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();

            System.out.println("INSERT: " + e.getMessage());
        }finally {
            em.close();
        }

        entityManagerFactory.close();
    }
}
