package com.example.demo.Service;
import com.example.demo.Model.PersonDetails;
import  com.example.demo.dao.personDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final personDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("AlternateFake") personDAO personDAO) {
        this.personDAO = personDAO;
    }
    public int addPerson(PersonDetails person,PersonDetails mailID,PersonDetails password)
    {
        return personDAO.insertPerson(person,mailID,password);
    }
    public List<String> selectPerson(PersonDetails person)
    {
        return personDAO.searchPerson(person);
    }
    public Optional<PersonDetails> selectPersonById(UUID id)
    {
        return personDAO.getPersonbyID(id);
    }
    public int deletePerson(UUID id)
    {
        return personDAO.deletePerson(id);
    }
    public int updatePerson(UUID id,PersonDetails person)
    {
        return  personDAO.updatePerson(id,person);
    }
    public List<PersonDetails> loginCheck(String name,String password)
    {
        return personDAO.loginCheck(name,password);
    }

    public List<String> detailsofPerson(String name,PersonDetails person) {
        return personDAO.detailsofPerson(name,person);
    }

    public int Request(String name, PersonDetails person) {
        return personDAO.Request(name,person);
    }

    public List<ArrayList<String>> ListOfFriends(String name) {
        return personDAO.ListofFriends(name);
    }

    public int Response(String name, String nameFlag) {
        return personDAO.Response(name,nameFlag);

    }
}
