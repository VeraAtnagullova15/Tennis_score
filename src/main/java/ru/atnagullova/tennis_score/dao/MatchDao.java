package ru.atnagullova.tennis_score.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.atnagullova.tennis_score.entity.Match;
import ru.atnagullova.tennis_score.entity.Player;
import ru.atnagullova.tennis_score.match_state.ActiveMatch;
import ru.atnagullova.tennis_score.util.SessionFactoryManager;

@AllArgsConstructor
public class MatchDao {

    private PlayerDao playerDao;

    public void save(ActiveMatch activeMatch) {

        try (Session session = SessionFactoryManager.getSessionFactory().openSession();) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            Player player1 = playerDao.findById(activeMatch.getPlayerOneId());
            Player player2 = playerDao.findById(activeMatch.getPlayerTwoId());
            Player winner = playerDao.findById(activeMatch.getWinnerId());

            Match match = new Match(player1, player2, winner);

            session.persist(match);

            transaction.commit();
        }
    }
}
