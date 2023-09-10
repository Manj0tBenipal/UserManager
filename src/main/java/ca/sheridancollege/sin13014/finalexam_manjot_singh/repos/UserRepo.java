package ca.sheridancollege.sin13014.finalexam_manjot_singh.repos;



import ca.sheridancollege.sin13014.finalexam_manjot_singh.beans.Programmer;
import ca.sheridancollege.sin13014.finalexam_manjot_singh.beans.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepo {
    private  NamedParameterJdbcTemplate jdbc;

    public UserRepo(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public ArrayList<User> getUsers() {
        String query = "Select * from sec_user ";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }



    public User getUsersByUsername(String username) {
        String query = "SELECT * FROM sec_user WHERE username = :woof";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("woof", username);
        ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    public ArrayList<String> getRolesById(long userId) {
        String query = "select user_role.userId, sec_role.roleName from user_role, sec_role where user_role.roleid=sec_role.roleid and userId=:userId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("userId", userId);
        ArrayList<String> roles = new ArrayList<String>();
        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        for (Map<String, Object> row : rows) {
            roles.add((String) row.get("rolename"));
        }
        return roles;

    }

    public void addUser(String userName, String password) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "insert into SEC_User (userName, encryptedPassword, ENABLED) values(:uname, :pass, TRUE)";
        parameters.addValue("uname", userName);
        parameters.addValue("pass", password);
        jdbc.update(query, parameters);
        ArrayList<User> users = getUsers();
        int prevNumberOfUsers = users.size();

        String addRoleQuery = "insert into user_role(roleId, userId) values(2,:newUid)";
        parameters.addValue("newUid", prevNumberOfUsers);
        jdbc.update(addRoleQuery, parameters);

    }

}

