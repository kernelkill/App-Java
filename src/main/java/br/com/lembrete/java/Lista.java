package br.com.lembrete.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Lista {

    public static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager(){
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        }

        return entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) {

        List<Lembrete> lembretes = null;

        EntityManager em = getEntityManager();

        try{
            lembretes = em.createQuery("from Lembrete").getResultList();

        }catch (Exception e){
            System.out.println("LIST ALL" + e.getMessage());

        }finally {
            em.close();
        }

        if (lembretes != null){
            lembretes.forEach(System.out::println);
        }

    }
}
