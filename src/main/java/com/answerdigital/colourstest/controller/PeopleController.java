package com.answerdigital.colourstest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.answerdigital.colourstest.dto.PersonUpdateDTO;
import com.answerdigital.colourstest.exception.NotImplementedException;
import com.answerdigital.colourstest.model.Person;
import com.answerdigital.colourstest.repository.PeopleRepository;

@RestController
@RequestMapping("/People")
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRespository;

    @GetMapping
    public ResponseEntity<List<Person>> getPeople() {
        // TODO STEP 1
        //
        // Implement a JSON endpoint that returns the full list
        // of people from the PeopleRepository. If there are zero
        // people returned from PeopleRepository then an empty
        // JSON array should be returned.
        throw new NotImplementedException();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
        // TODO: Step 2
        //
        // Implement a JSON endpoint that returns a single person
        // from the PeopleRepository based on the id parameter.
        // If null is returned from the PeopleRepository with
        // the supplied id then a NotFound should be returned.

        throw new NotImplementedException();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, 
            @RequestBody PersonUpdateDTO personUpdate) {
        // TODO STEP 3
        //
        // Implement an endpoint that recieves a JSON object to
        // update a person using the PeopleRepository based on
        // the id parameter. Once the person has been sucessfullly
        // updated, the person should be returned from the endpoint.
        // If null is returned from the PeopleRepository then a
        // NotFound should be returned.
        throw new NotImplementedException();
    }

}
