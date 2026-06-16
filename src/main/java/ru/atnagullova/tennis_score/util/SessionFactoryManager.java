package ru.atnagullova.tennis_score.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.atnagullova.tennis_score.entity.Match;
import ru.atnagullova.tennis_score.entity.Player;

@UtilityClass
public class SessionFactoryManager {

    private static final SessionFactory SESSION_FACTORY =
            new Configuration()
                    .configure()
                    .addAnnotatedClass(Player.class)
                    .addAnnotatedClass(Match.class)
                    .buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }


}
