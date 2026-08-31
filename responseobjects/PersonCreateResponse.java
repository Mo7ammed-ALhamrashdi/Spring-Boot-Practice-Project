package com.cl.demo.responseobjects;

import com.cl.demo.entities.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonCreateResponse {
    String personId;
    String fullName;
    String userName;
    String email;
    String phoneNumber;

    public static PersonCreateResponse convert(Person person) {
        if (person == null || person.getId() == null) {
            return null;
        }
        PersonCreateResponse response = new PersonCreateResponse();
        response.setPersonId(person.getId().toString());
        response.setFullName(person.getName());
        if (person.getUserName() != null) {

            response.setUserName(
                    person.getUserName().getActiveUserName()
            );
        }
        response.setEmail(person.getEmail());
        if (person.getPhoneNumber() != null) {
            response.setPhoneNumber(person.getPhoneNumber().getCountryCode() + " " + person.getPhoneNumber().getPhoneNumber()
            );
        }
        return response;
    }
    public static List<PersonCreateResponse> convert(List<Person> personList) {
        List<PersonCreateResponse> responseList = new ArrayList<>();
        for (Person person : personList) {PersonCreateResponse response = convert(person);
            if (response != null) {responseList.add(response);
            }
        }
        return responseList;
    }
}


