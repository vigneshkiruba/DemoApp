package com.example.demo.dao;

import com.example.demo.Model.PersonDetails;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeDAO")
public class Fake implements personDAO{
    private static List<PersonDetails> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, PersonDetails person,PersonDetails mailID,PersonDetails password) {
        DB.add(new PersonDetails(id, person.getName(), mailID.getMailId(), password.getPassword()));
        return 1;
    }

    @Override
    public List<String> searchPerson(PersonDetails person) {
       return  null;
    }

    @Override
    public List<PersonDetails> loginCheck(String name, String password) {
        return null;
    }


    @Override
    public Optional<PersonDetails> getPersonbyID(UUID id) {
        return  DB.stream()
                .filter(personDetails -> personDetails.getId().equals(id))
                .findFirst();

    }

    @Override
    public int deletePerson(UUID id) {
        Optional<PersonDetails> getPersonMaybe = getPersonbyID(id);
        if(getPersonMaybe.isEmpty())
        {
            return 0;
        }
        DB.remove(getPersonMaybe.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id, PersonDetails person) {
        return getPersonbyID(id)
                .map(changes ->
                {
                    int indexOfUpdate = DB.indexOf(changes);
                    if (indexOfUpdate >= 0) {
                        DB.set(indexOfUpdate, new PersonDetails(id, person.getName(), " "," "));
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }

    @Override
    public List<String> detailsofPerson(String name,PersonDetails person) {
        return null;
    }

    @Override
    public int Request(String name, PersonDetails person) {
        return 0;
    }

    @Override
    public List<ArrayList<String>> ListofFriends(String name) {
        return null;
    }

    @Override
    public int Response(String name, String nameFlag) {
        return 0;
    }
}
