package de.cadentem.assembler;

import de.cadentem.controller.ArtistController;
import de.cadentem.entities.Artist;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArtistModelAssembler implements RepresentationModelAssembler<Artist, EntityModel<Artist>> {
    @Override
    public EntityModel<Artist> toModel(final Artist artist) {
        return EntityModel.of(artist,
                linkTo(methodOn(ArtistController.class).one(artist.getId())).withSelfRel(),
                linkTo(methodOn(ArtistController.class).all()).withRel("artists"));
    }
}
