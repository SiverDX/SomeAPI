package de.cadentem.controllers;

import de.cadentem.entities.*;
import de.cadentem.services.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TypeController {
    private final TypeService typeService;

    @GetMapping("/subtypes")
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<SubType>> getSubTypes() {
        List<SubType> entities = (List<SubType>) typeService.findAll(ValueType.SUBTYPE);

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/subtypes/{value}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<SubType> getSubType(@PathVariable final String value) {
        Optional<SubType> entity = (Optional<SubType>) typeService.findByValue(value, ValueType.SUBTYPE);

        return ResponseEntity.of(entity);
    }

    @GetMapping("/supertypes")
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<SuperType>> getSuperTypes() {
        List<SuperType> entities = (List<SuperType>) typeService.findAll(ValueType.SUPERTYPE);

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/supertypes/{value}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<SuperType> getSuperType(@PathVariable final String value) {
        Optional<SuperType> entity = (Optional<SuperType>) typeService.findByValue(value, ValueType.SUPERTYPE);

        return ResponseEntity.of(entity);
    }

    @GetMapping("/rarities")
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<Rarity>> getRarities() {
        List<Rarity> entities = (List<Rarity>) typeService.findAll(ValueType.RARITY);

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/rarities/{value}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<Rarity> getRarity(@PathVariable final String value) {
        Optional<Rarity> entity = (Optional<Rarity>) typeService.findByValue(value, ValueType.RARITY);

        return ResponseEntity.of(entity);
    }

    @GetMapping("/types")
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<Type>> getTypes() {
        List<Type> entities = (List<Type>) typeService.findAll(ValueType.TYPE);

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/types/{value}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<Type> getType(@PathVariable final String value) {
        Optional<Type> entity = (Optional<Type>) typeService.findByValue(value, ValueType.TYPE);

        return ResponseEntity.of(entity);
    }
}

