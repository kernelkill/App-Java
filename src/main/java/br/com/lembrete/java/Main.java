package br.com.lembrete.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");


        Lembrete lembrete = new Lembrete();
        lembrete.setTitulo("Dentista");
        lembrete.setDescricao("Dentista marcado para o dia 17/08 as 17:00");

        EntityManager em = entityManagerFactory.createEntityManager();

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
    }
}
