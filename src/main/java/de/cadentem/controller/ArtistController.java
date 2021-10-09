package de.cadentem.controller;

import de.cadentem.assembler.ArtistModelAssembler;
import de.cadentem.entities.Artist;
import de.cadentem.exceptions.ArtistNotFoundException;
import de.cadentem.repositories.ArtistRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ArtistController {
    private final ArtistRepository repository;
    private final ArtistModelAssembler assembler;

    public ArtistController(final ArtistRepository repository, final ArtistModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/artists")
    public CollectionModel<EntityModel<Artist>> all() {
        List<EntityModel<Artist>> artists = repository
                .findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(artists, linkTo(methodOn(ArtistController.class).all()).withSelfRel());
    }

    @GetMapping("artists/{id}")
    public EntityModel<Artist> one(@PathVariable final Long id) throws ArtistNotFoundException {
        Artist artist = repository.findById(id).orElseThrow(() -> new ArtistNotFoundException(id));

        return assembler.toModel(artist);
    }

    @PostMapping("/artists")
    public ResponseEntity<EntityModel<Artist>> newArtist(@RequestBody final Artist newArtist) {
        EntityModel<Artist> artist = assembler.toModel(repository.save(newArtist));

        return ResponseEntity.created(artist.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(artist);
    }

    @PutMapping("/artists/{id}")
    public ResponseEntity<EntityModel<Artist>> replaceArtist(@RequestBody final Artist newArtist, @PathVariable final Long id) {
        Artist updatedArtist = repository.findById(id).map(artist -> {
            artist.setName(artist.getName());
            artist.setBirthYear(artist.getBirthYear());

            return repository.save(artist);
        }).orElseGet(() -> {
            newArtist.setId(id);

            return repository.save(newArtist);
        });

        EntityModel<Artist> artistModel = assembler.toModel(updatedArtist);

        return ResponseEntity.created(artistModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(artistModel);
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable final Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
