package br.com.lembrete.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Update {

    private static EntityManagerFactory entityManagerFactory;

    public static  EntityManager getEntityManager(){

        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) {


        EntityManager em = getEntityManager();

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

        entityManagerFactory.close();
    }
}
