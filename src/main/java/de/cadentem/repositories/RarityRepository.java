package de.cadentem.repositories;

import de.cadentem.entities.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RarityRepository extends JpaRepository<Rarity, Long> {
    // todo :: generic value repository?
    Optional<Rarity> findByValue(final String value);
}
