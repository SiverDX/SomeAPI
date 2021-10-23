package de.cadentem.controllers;

import de.cadentem.assemblers.RarityAssembler;
import de.cadentem.assemblers.SubTypeAssembler;
import de.cadentem.assemblers.SuperTypeAssembler;
import de.cadentem.assemblers.TypeAssembler;
import de.cadentem.entities.*;
import de.cadentem.services.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TypeController {
    // todo :: split up?
    private final TypeService typeService;

    private final RarityAssembler rarityAssembler;
    private final SubTypeAssembler subTypeAssembler;
    private final SuperTypeAssembler superTypeAssembler;
    private final TypeAssembler typeAssembler;

    @GetMapping(value = "/subtypes")
    @SuppressWarnings("unchecked")
    public ResponseEntity<CollectionModel<EntityModel<SubType>>> getSubTypes(@RequestParam final Optional<String> value) {
        List<SubType> entities;

        if (value.isPresent()) {
            entities = (List<SubType>) typeService.findByValue(value.get(), ValueType.SUBTYPE);
        } else {
            entities = (List<SubType>) typeService.findAll(ValueType.SUBTYPE);
        }

        return ResponseEntity.ok(subTypeAssembler.toCollectionModel(entities));
    }

    @GetMapping("/subtypes/{id}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<EntityModel<SubType>> getSubType(@PathVariable final long id) {
        Optional<SubType> optional = (Optional<SubType>) typeService.findById(id, ValueType.SUBTYPE);
        SubType entity = optional.orElseThrow(() -> new NoSuchElementException("nope"));

        return ResponseEntity.ok(subTypeAssembler.toModel(entity));
    }

    @GetMapping(value = "/supertypes")
    @SuppressWarnings("unchecked")
    public ResponseEntity<CollectionModel<EntityModel<SuperType>>> getSuperTypes(@RequestParam final Optional<String> value) {
        List<SuperType> entities;

        if (value.isPresent()) {
            entities = (List<SuperType>) typeService.findByValue(value.get(), ValueType.SUPERTYPE);
        } else {
            entities = (List<SuperType>) typeService.findAll(ValueType.SUPERTYPE);
        }

        return ResponseEntity.ok(superTypeAssembler.toCollectionModel(entities));
    }

    @GetMapping("/supertypes/{id}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<EntityModel<SuperType>> getSuperType(@PathVariable final long id) {
        Optional<SuperType> optional = (Optional<SuperType>) typeService.findById(id, ValueType.SUPERTYPE);
        SuperType entity = optional.orElseThrow(() -> new NoSuchElementException("nope"));

        return ResponseEntity.ok(superTypeAssembler.toModel(entity));
    }

    @GetMapping(value = "/rarities")
    @SuppressWarnings("unchecked")
    public ResponseEntity<CollectionModel<EntityModel<Rarity>>> getRarities(@RequestParam final Optional<String> value) {
        List<Rarity> entities;

        if (value.isPresent()) {
            entities = (List<Rarity>) typeService.findByValue(value.get(), ValueType.RARITY);
        } else {
            entities = (List<Rarity>) typeService.findAll(ValueType.RARITY);
        }

        return ResponseEntity.ok(rarityAssembler.toCollectionModel(entities));
    }

    @GetMapping("/rarities/{id}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<EntityModel<Rarity>> getRarity(@PathVariable final long id) {
        Optional<Rarity> optional = (Optional<Rarity>) typeService.findById(id, ValueType.RARITY);
        Rarity entity = optional.orElseThrow(() -> new NoSuchElementException("nope"));

        return ResponseEntity.ok(rarityAssembler.toModel(entity));
    }

    @GetMapping(value = "/types")
    @SuppressWarnings("unchecked")
    public ResponseEntity<CollectionModel<EntityModel<Type>>> getTypes(@RequestParam final Optional<String> value) {
        List<Type> entities;

        if (value.isPresent()) {
            entities = (List<Type>) typeService.findByValue(value.get(), ValueType.TYPE);
        } else {
            entities = (List<Type>) typeService.findAll(ValueType.TYPE);
        }

        return ResponseEntity.ok(typeAssembler.toCollectionModel(entities));
    }

    @GetMapping("/types/{id}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<EntityModel<Type>> getType(@PathVariable final long id) {
        Optional<Type> optional = (Optional<Type>) typeService.findById(id, ValueType.TYPE);
        Type entity = optional.orElseThrow(() -> new NoSuchElementException("nope"));

        return ResponseEntity.ok(typeAssembler.toModel(entity));
    }
}

