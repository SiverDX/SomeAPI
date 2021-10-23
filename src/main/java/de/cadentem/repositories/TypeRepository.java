package de.cadentem.repositories;

import de.cadentem.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByValue(final String value);
}
