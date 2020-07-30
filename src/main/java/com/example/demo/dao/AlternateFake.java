package com.example.demo.dao;

import com.example.demo.Model.PersonDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.*;

import static com.example.demo.DemoApplication.demoLog;

@Repository("AlternateFake")
public class AlternateFake implements personDAO{
    private final JdbcTemplate jdbcTemplate;

    public AlternateFake(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, PersonDetails person,PersonDetails mailID,PersonDetails password) {
        demoLog.info("Inside insertPerson **");
        String sql = "INSERT INTO person (id,personname,MailID,Passwor) VALUES (? ,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{id,person.getName(),mailID.getMailId(),password.getPassword()});
        return 0;
    }

    @Override
    public List<String> searchPerson(PersonDetails person) {
        String fetch = person.getName() + "%";
        String sql = "SELECT personname FROM person where personname LIKE ? ";
        return (List<String>) jdbcTemplate.query(
                sql, new Object[] {fetch},(resultSet,i) -> {
                    String name = resultSet.getString("personname");
                    return name;
                }
        );
    }

    @Override
    public List<PersonDetails> loginCheck(String name, String password) {
        String sql = "SELECT * FROM person where personname = ? AND passwor = ?";
        demoLog.info(sql);
        return  jdbcTemplate.query(sql,
                new Object[]{name,password},
                (resultSet,i) -> {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String loginname = resultSet.getString("personname");
                String mailID = resultSet.getString("MailID");
                String loginpassword = resultSet.getString("Passwor");
                return new PersonDetails(id, loginname, mailID, loginpassword);
                });

    }

    @Override
    public Optional<PersonDetails> getPersonbyID(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deletePerson(UUID id) {
        return 0;
    }

    @Override
    public int updatePerson(UUID id, PersonDetails person) {
        return 0;
    }

    @Override
    public List<String> detailsofPerson(String name,PersonDetails person) {

        final SimpleJdbcCall funcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("FullDetailsFunc");
        final Map<String, Object> params = new HashMap<>();
        params.put("loginUser",name);
        params.put("searchUser", person.getName());
        final Map<String, Object> result = funcCall.execute(params);
        Collection<Object> isoCodes = result.values();
        demoLog.info(isoCodes.toString());
       return Collections.singletonList(isoCodes.toString());
    }

    @Override
    public int Request(String name, PersonDetails person) {
        String sql = "INSERT INTO listoffriends (username,friendlist,requestflag) VALUES (?,?,'PE')";
        jdbcTemplate.update(sql,new Object[]{person.getName(),name});
        return 0;
    }

    @Override
    public List<ArrayList<String>> ListofFriends(String name) {
        String sql = "select * from listoffriends WHERE username = ?";

        List<ArrayList<String>> ListOFfrnds = new ArrayList<>();
        jdbcTemplate.query(sql,new Object[]{name},(resultSet, i)->
        {
            String friendName = resultSet.getString("friendlist");
            String reqFlag = resultSet.getString("requestflag");
            ArrayList<String> friendlist =new ArrayList<>();
            friendlist.add(friendName);
            friendlist.add(reqFlag);
            ListOFfrnds.add(friendlist);
            return friendlist;
        });
        return ListOFfrnds;
    }

    @Override
    public int Response(String name, String nameFlag) {
        demoLog.info(nameFlag+" ***");
        String[] sample = nameFlag.split(" ");
        demoLog.info(sample[0]+" ***");
        demoLog.info(sample[1]+" ***");
        String sql = "UPDATE listoffriends SET requestflag = ? WHERE username = ? and friendlist = ?";
        jdbcTemplate.update(sql,new Object[]{sample[1],name,sample[0]});
        String sql2 = "INSERT INTO listoffriends(username,friendlist,requestflag) VALUES (?,?,?)";
        jdbcTemplate.update(sql2,new Object[]{sample[0],name,sample[1]});

        return 0;
    }
}
