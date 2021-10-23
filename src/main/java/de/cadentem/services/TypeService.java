package de.cadentem.services;

import de.cadentem.entities.ValueType;
import de.cadentem.repositories.RarityRepository;
import de.cadentem.repositories.SubTypeRepository;
import de.cadentem.repositories.SupertypeRepository;
import de.cadentem.repositories.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService {
    private final RarityRepository rarityRepository;
    private final SupertypeRepository superTypeRepository;
    private final SubTypeRepository subTypeRepository;
    private final TypeRepository typeRepository;

    public Optional<?> findByValue(final String value, final ValueType valueType) {
        switch (valueType) {
            case RARITY:
                return rarityRepository.findByValue(value);
            case TYPE:
                return typeRepository.findByValue(value);
            case SUBTYPE:
                return subTypeRepository.findByValue(value);
            case SUPERTYPE:
                return superTypeRepository.findByValue(value);
        }

        return Optional.empty();
    }

    public List<?> findAll(final ValueType valueType) {
        switch (valueType) {
            case RARITY:
                return rarityRepository.findAll();
            case TYPE:
                return typeRepository.findAll();
            case SUBTYPE:
                return subTypeRepository.findAll();
            case SUPERTYPE:
                return superTypeRepository.findAll();
        }

        return null;
    }
}
