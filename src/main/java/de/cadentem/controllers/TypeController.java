package de.cadentem.controllers;

import de.cadentem.assemblers.RarityAssembler;
import de.cadentem.assemblers.SubTypeAssembler;
import de.cadentem.assemblers.SuperTypeAssembler;
import de.cadentem.assemblers.TypeAssembler;
import de.cadentem.entities.*;
import de.cadentem.services.TypeService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.EntityMode;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TypeController {
    private final TypeService typeService;

    private final RarityAssembler rarityAssembler;
    private final SubTypeAssembler subTypeAssembler;
    private final SuperTypeAssembler superTypeAssembler;
    private final TypeAssembler typeAssembler;

    @GetMapping("/subtypes")
    @SuppressWarnings("unchecked")
    public ResponseEntity<CollectionModel<EntityModel<SubType>>> getSubTypes() {
        List<SubType> entities = (List<SubType>) typeService.findAll(ValueType.SUBTYPE);

        return ResponseEntity.ok(subTypeAssembler.toCollectionModel(entities));
    }

    @GetMapping("/subtypes/{value}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<EntityModel<SubType>> getSubType(@PathVariable final String value) {
        Optional<SubType> optional = (Optional<SubType>) typeService.findByValue(value, ValueType.SUBTYPE);
        SubType entity = optional.orElseThrow(() -> new NoSuchElementException("nope"));

        return ResponseEntity.ok(subTypeAssembler.toModel(entity));
    }

    @GetMapping("/supertypes")
    @SuppressWarnings("unchecked")
    public ResponseEntity<CollectionModel<EntityModel<SuperType>>> getSuperTypes() {
        List<SuperType> entities = (List<SuperType>) typeService.findAll(ValueType.SUPERTYPE);

        return ResponseEntity.ok(superTypeAssembler.toCollectionModel(entities));
    }

    @GetMapping("/supertypes/{value}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<EntityModel<SuperType>> getSuperType(@PathVariable final String value) {
        Optional<SuperType> optional = (Optional<SuperType>) typeService.findByValue(value, ValueType.SUPERTYPE);
        SuperType entity = optional.orElseThrow(() -> new NoSuchElementException("nope"));

        return ResponseEntity.ok(superTypeAssembler.toModel(entity));
    }

    @GetMapping("/rarities")
    @SuppressWarnings("unchecked")
    public ResponseEntity<CollectionModel<EntityModel<Rarity>>> getRarities() {
        List<Rarity> entities = (List<Rarity>) typeService.findAll(ValueType.RARITY);

        return ResponseEntity.ok(rarityAssembler.toCollectionModel(entities));
    }

    @GetMapping("/rarities/{value}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<EntityModel<Rarity>> getRarity(@PathVariable final String value) {
        Optional<Rarity> optional = (Optional<Rarity>) typeService.findByValue(value, ValueType.RARITY);
        Rarity entity = optional.orElseThrow(() -> new NoSuchElementException("nope"));

        return ResponseEntity.ok(rarityAssembler.toModel(entity));
    }

    @GetMapping("/types")
    @SuppressWarnings("unchecked")
    public ResponseEntity<CollectionModel<EntityModel<Type>>> getTypes() {
        List<Type> entities = (List<Type>) typeService.findAll(ValueType.TYPE);

        return ResponseEntity.ok(typeAssembler.toCollectionModel(entities));
    }

    @GetMapping("/types/{value}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<EntityModel<Type>> getType(@PathVariable final String value) {
        Optional<Type> optional = (Optional<Type>) typeService.findByValue(value, ValueType.TYPE);
        Type entity = optional.orElseThrow(() -> new NoSuchElementException("nope"));

        return ResponseEntity.ok(typeAssembler.toModel(entity));
    }
}

