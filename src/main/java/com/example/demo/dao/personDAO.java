package com.example.demo.dao;
import java.util.*;
import com.example.demo.Model.PersonDetails;

public interface personDAO {


    int insertPerson(UUID id, PersonDetails person,PersonDetails MailID,PersonDetails Password);

    default int insertPerson(PersonDetails person,PersonDetails MailID,PersonDetails Password)
    {
            UUID id = UUID.randomUUID();
            return  insertPerson(id,person,MailID,Password);
    }

    List<String> searchPerson(PersonDetails person);
    List<PersonDetails> loginCheck(String name , String password);
    Optional <PersonDetails> getPersonbyID(UUID id);
    int deletePerson(UUID id);
    int updatePerson(UUID id,PersonDetails person);

    List<String> detailsofPerson(String name,PersonDetails person);

    int Request(String name, PersonDetails person);

    List<ArrayList<String>> ListofFriends(String name);

    int Response(String name, String nameFlag);
}
