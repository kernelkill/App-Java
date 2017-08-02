package br.com.lembrete.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BuscaID {

    public static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");

        EntityManager em = entityManagerFactory.createEntityManager();

        Lembrete lembretes = null;

        try {
            lembretes = em.find(Lembrete.class, 2L);

            System.out.println(lembretes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }
}
