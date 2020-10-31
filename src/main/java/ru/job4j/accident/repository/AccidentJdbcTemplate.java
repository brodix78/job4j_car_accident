package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private  final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Accident> getAll() {
        return jdbc.query("SELECT * FROM accident",
                (rs, row) -> {
                    Accident accident = new Accident(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address")
                    );
                    return accident;
                });
    }

    public Accident addAccident(Accident accident) {
        accident.setId(jdbc.update("INSERT INTO accident(name, text, address) values (?,?,?)",
                accident.getName(), accident.getText(), accident.getAddress()));
        return accident;
    }

    public Accident updateAccident(Accident accident) {
        Accident rsl = accident;
        if (jdbc.update("UPDATE accident SET name=?, text=?, address=? WHERE id=?",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getId()) != 1) {
            rsl = null;
        }
        return rsl;
    }

    public Accident getAccidentById(int id) {
        Accident accident = jdbc.queryForObject(
                "SELECT name, text, address FROM accident WHERE id=?",
                (rs, row) -> {
                    Accident acc = new Accident(
                            id,
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address")
                    );
                    return acc;
                }, id);
    return accident;
    }
}
