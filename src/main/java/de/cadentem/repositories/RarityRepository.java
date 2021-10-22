package de.cadentem.repositories;

import de.cadentem.entities.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RarityRepository extends JpaRepository<Rarity, Long> {
}
