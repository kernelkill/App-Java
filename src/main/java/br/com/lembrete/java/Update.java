package br.com.lembrete.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Update {

    public static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            Lembrete lembrete = em .find(Lembrete.class, 1L);

            lembrete.setTitulo("Comprar Pão");
            lembrete.setDescricao("Troquei o leite pelo pão");

            em.getTransaction().begin();
            em.merge(lembrete);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();

            System.out.println("UPDATE: " + e.getMessage());
        }finally {
            em.close();
        }
    }
}
