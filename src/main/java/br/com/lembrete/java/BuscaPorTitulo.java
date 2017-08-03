package br.com.lembrete.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BuscaPorTitulo {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");

        List<Lembrete> lembretes = null;

        EntityManager em = entityManagerFactory.createEntityManager();


        try {
            lembretes = em.createQuery("from Lembrete l where l.descricao LIKE '%comprar%'").getResultList();
        }catch (Exception e){
            System.out.println("LIST ALL: " + e.getMessage());
        }finally {
            em.close();
        }

        if (lembretes != null){
            lembretes.forEach(System.out::println);
        }
    }
}
