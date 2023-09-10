package ca.sheridancollege.sin13014.finalexam_manjot_singh.repos;

import ca.sheridancollege.sin13014.finalexam_manjot_singh.beans.Programmer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ProgrammerRepo {
    private NamedParameterJdbcTemplate jdbc;

    public ProgrammerRepo(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public ArrayList<Programmer> getProgrammers() {
        String query = "Select * from programmer ";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        ArrayList<Programmer> programmers = (ArrayList<Programmer>) jdbc.query(query, parameters, new BeanPropertyRowMapper<Programmer>(Programmer.class));
        return programmers;
    }

    public Programmer getProgrammerById(int id) {
        String query = "SELECT * FROM programmer WHERE id = :woof";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("woof", id);
        ArrayList<Programmer> programmers = (ArrayList<Programmer>) jdbc.query(query, parameters, new BeanPropertyRowMapper<Programmer>(Programmer.class));
        if (programmers.size() > 0) {
            return programmers.get(0);
        } else {
            return null;
        }

    }

    public void addProgrammer(Programmer programmer){

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "insert into programmer(name, salary, yearsExperience) values(:name, :salary, :years)";
        parameters.addValue("name", programmer.getName());
        parameters.addValue("salary", programmer.getSalary());
        parameters.addValue("years", programmer.getYearsExperience());
        jdbc.update(query, parameters);

    }

    public void deleteProgrammer(int id){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "delete from programmer where id=:id";
        parameters.addValue("id", id);
        jdbc.update(query, parameters);
    }
}
