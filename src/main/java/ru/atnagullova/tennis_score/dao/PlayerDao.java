package ru.atnagullova.tennis_score.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.atnagullova.tennis_score.entity.Player;
import ru.atnagullova.tennis_score.util.SessionFactoryManager;

import java.util.Optional;

public class PlayerDao {

    public Optional<Player> find (String name) {

        try (Session session = SessionFactoryManager.getSessionFactory().openSession();) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            Query<Player> query = session.createQuery("from Player p where p.name=?1", Player.class);
            query.setParameter(1, name);
            Optional<Player> player = query.uniqueResultOptional();

            System.out.println(player);

            transaction.commit();

            return player;
        }
    }

    public Player saveAndReturn(String name) {

        Player player = new Player(name);

        try (Session session = SessionFactoryManager.getSessionFactory().openSession();) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            session.persist(player);

            System.out.println(player);

            transaction.commit();
        }
        return player;
    }

}
