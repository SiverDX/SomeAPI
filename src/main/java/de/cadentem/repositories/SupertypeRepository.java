package de.cadentem.repositories;

import de.cadentem.entities.SuperType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupertypeRepository extends JpaRepository<SuperType, Long> {
    List<SuperType> findByValue(final String value);
}
