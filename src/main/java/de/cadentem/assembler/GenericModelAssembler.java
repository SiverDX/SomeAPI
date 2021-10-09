package de.cadentem.assembler;

import de.cadentem.controller.IController;
import de.cadentem.entities.IEntity;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GenericModelAssembler<T extends IEntity> {
    protected EntityModel<T> toModel(final Class<? extends IController<T>> controller, final T entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(controller).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(controller).all()).withRel(entity.getBase()));
    }
}
