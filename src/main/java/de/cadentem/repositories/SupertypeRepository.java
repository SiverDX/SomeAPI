package de.cadentem.repositories;

import de.cadentem.entities.SuperType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupertypeRepository extends JpaRepository<SuperType, Long> {
    Optional<SuperType> findByValue(final String value);
}
