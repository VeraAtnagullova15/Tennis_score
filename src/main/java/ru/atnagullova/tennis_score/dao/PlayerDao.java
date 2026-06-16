package ru.atnagullova.tennis_score.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.atnagullova.tennis_score.entity.Player;
import ru.atnagullova.tennis_score.util.SessionFactoryManager;

import java.util.List;

public class PlayerDao {

    public boolean isPlayerExist(String name) {

        try (Session session = SessionFactoryManager.getSessionFactory().openSession();) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            Query<Player> query = session.createQuery("from Player p where p.name=?1", Player.class);
            query.setParameter(1, name);
            List<Player> players = query.getResultList();

            transaction.commit();

            if (!players.isEmpty()) {
                return true;
            }
            return false;
        }
    }

    public void persistPlayer(String name) {

        Player player = new Player(name);

        try (Session session = SessionFactoryManager.getSessionFactory().openSession();) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            session.persist(player);

            System.out.println(session.createQuery("from Player", Player.class).list());
            transaction.commit();
        }
    }
}
