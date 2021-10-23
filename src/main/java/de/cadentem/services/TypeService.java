package de.cadentem.services;

import de.cadentem.entities.ValueType;
import de.cadentem.repositories.RarityRepository;
import de.cadentem.repositories.SubTypeRepository;
import de.cadentem.repositories.SupertypeRepository;
import de.cadentem.repositories.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService {
    private final RarityRepository rarityRepository;
    private final SupertypeRepository superTypeRepository;
    private final SubTypeRepository subTypeRepository;
    private final TypeRepository typeRepository;

    public Optional<?> findById(final long id, final ValueType valueType) {
        switch (valueType) {
            case RARITY:
                return rarityRepository.findById(id);
            case TYPE:
                return typeRepository.findById(id);
            case SUBTYPE:
                return subTypeRepository.findById(id);
            case SUPERTYPE:
                return superTypeRepository.findById(id);
        }

        return Optional.empty();
    }

    public List<?> findByValue(final String value, final ValueType valueType) {
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

        // todo :: return null / throw exception?
        return new ArrayList<>();
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
