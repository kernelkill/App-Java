package br.com.lembrete.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BuscaPorTitulo {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager getEntityManager(){
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) {

        List<Lembrete> lembretes = null;

        EntityManager em = getEntityManager();


        try {
            lembretes = em.createQuery("from Lembrete l where l.descricao LIKE '%restaurante%'").getResultList();
        }catch (Exception e){
            System.out.println("LIST ALL: " + e.getMessage());
        }finally {
            em.close();
        }

        if (lembretes != null){
            lembretes.forEach(System.out::println);
        }

        entityManagerFactory.close();
    }
}
