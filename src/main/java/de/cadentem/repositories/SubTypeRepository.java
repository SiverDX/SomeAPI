package de.cadentem.repositories;

import de.cadentem.entities.SubType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubTypeRepository extends JpaRepository<SubType, Long> {
    Optional<SubType> findByValue(final String value);
}
