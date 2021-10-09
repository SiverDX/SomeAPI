package de.cadentem.assembler;

import de.cadentem.controller.ArtistController;
import de.cadentem.entities.Artist;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ArtistModelAssembler extends GenericModelAssembler<Artist> implements RepresentationModelAssembler<Artist, EntityModel<Artist>> {
    @Override
    public EntityModel<Artist> toModel(final Artist artist) {
        return toModel(ArtistController.class, artist);
    }
}
