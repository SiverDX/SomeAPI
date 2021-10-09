package de.cadentem.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface IController<T> {
    CollectionModel<EntityModel<T>> all();

    EntityModel<T> one(Long id);
}
