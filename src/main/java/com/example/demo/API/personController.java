package com.example.demo.API;

import com.example.demo.Model.PersonDetails;
import com.example.demo.Service.PersonService;
import com.example.demo.endPoint.EchoEndPoint;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import static com.example.demo.DemoApplication.demoLog;

@RequestMapping(value = "/API/v1/personDetails")
@RestController
public class personController {
  private final PersonService PersonService;

    @Autowired
    public personController(PersonService personService) {
        this.PersonService = personService;
    }

    @PostMapping(path = "SignUp")
    public void addPerson(@RequestBody PersonDetails person) throws JSONException {

        demoLog.info(person.getName());
        demoLog.info(person.getMailId());
        demoLog.info(person.getPassword());
        PersonService.addPerson(person,person,person);
        return;
    }

    @GetMapping(path = "/User:{name}/Pass:{password}")
    public List<PersonDetails> login(@PathVariable ("name") String name ,@PathVariable ("password") String password)
    {
        return PersonService.loginCheck(name,password);
    }

    @GetMapping(path = "Search")
    public List<String> searchPerson(@RequestBody PersonDetails person)
    {
        return PersonService.selectPerson(person);
    }

    @GetMapping(path = "/User:{name}/Found")
    public List<String> detailsofPerson(@PathVariable ("name") String name,@RequestBody PersonDetails person)
    {
        return PersonService.detailsofPerson(name,person);
    }
    @PostMapping(path = "/User:{name}/FriendRequest")
    public void FriendRequest(@PathVariable ("name") String name,@RequestBody PersonDetails person) throws JSONException {
        PersonService.Request(name,person);
        return;

    }
    @GetMapping(path = "/User:{name}/FriendList")
    public List<ArrayList<String>> FriendsList(@PathVariable ("name") String name) throws JSONException {
        return PersonService.ListOfFriends(name);

    }
    @PostMapping(path = "/User:{name}/FriendResponse" )
    public void Response(@PathVariable ("name") String name,@RequestBody String nameFlag) throws JSONException {
        PersonService.Response(name,nameFlag);
        return;

    }















    @GetMapping(path = "{id}")
    public PersonDetails getPersonById(@PathVariable ("id") UUID id)
    {
        return PersonService.selectPersonById(id).orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable ("id") UUID id)
    {
        PersonService.deletePerson(id);
    }
    @PutMapping(path = "{id}")
    public void  updatePerson(@PathVariable ("id") UUID id,@RequestBody PersonDetails person)
    {
        PersonService.updatePerson(id,person);
    }

}
