package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Colours")
public class ColoursController {

    @Autowired
    private ColoursRepository coloursRepository;

    @GetMapping
    public ResponseEntity<List<Colour>> getColours() {
        return new ResponseEntity(coloursRepository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Colour> newColour(@RequestBody Colour colour) {
        // OPTIONAL - A JSON endpoint that creates a new colour in the ColoursRepository.
        // Relevant tests added to ColoursControllerTest
        return new ResponseEntity(coloursRepository.save(colour), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Colour> getColour(@PathVariable("id") long id) {
        // OPTIONAL - A JSON endpoint that returns a single colour from the ColoursRepository based on the id parameter. 
        // If null is returned from the ColoursRepository with the supplied id then a not found error should be returned.
        Optional<Colour> colour = coloursRepository.findById(id);
        return (colour.isPresent()) ?
            new ResponseEntity<>(colour.get(), HttpStatus.OK):
            new ResponseEntity<>(HttpStatus.NOT_FOUND);      
    }   
}