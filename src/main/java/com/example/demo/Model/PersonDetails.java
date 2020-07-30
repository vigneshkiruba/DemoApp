package com.example.demo.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class PersonDetails {


    private final String Name;
    private final UUID id;
    private final String MailID;
    private final String Password;


    public PersonDetails(@JsonProperty("id") UUID id, @JsonProperty("name") String name,@JsonProperty("mail") String mailID,@JsonProperty("password") String password) {
        this.id = id;
        this.Name = name;
        this.MailID = mailID;
        this.Password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getMailId() {
        return MailID;
    }

    public String getPassword() {
        return Password;
    }


}
