package de.cadentem.repositories;

import de.cadentem.entities.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RarityRepository extends JpaRepository<Rarity, Long> {
    // todo :: generic value repository?
    List<Rarity> findByValue(final String value);
}
