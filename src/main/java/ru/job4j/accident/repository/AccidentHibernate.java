package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Accident> getAll() {
        return tx(session -> session.createQuery("FROM Accident", Accident.class).list());
    }

    public Accident addAccident(Accident accident) {
        return tx(session -> {
            session.save(accident);
            return accident;
        });
    }

    public Accident updateAccident(Accident accident) {
        return tx(session -> {
            session.update(accident);
            return accident;
        });
    }

    public Accident getAccidentById(int id) {
        return tx(session -> session.get(Accident.class, id));
    }
}
