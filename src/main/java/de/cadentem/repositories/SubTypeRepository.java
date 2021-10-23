package de.cadentem.repositories;

import de.cadentem.entities.SubType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTypeRepository extends JpaRepository<SubType, Long> {
    List<SubType> findByValue(final String value);
}
