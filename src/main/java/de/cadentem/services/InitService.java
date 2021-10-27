package de.cadentem.services;

import de.cadentem.entities.*;
import de.cadentem.repositories.RarityRepository;
import de.cadentem.repositories.SubTypeRepository;
import de.cadentem.repositories.SupertypeRepository;
import de.cadentem.repositories.TypeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;

@Service
@AllArgsConstructor
public class InitService {
    // todo :: only one entity with two fields - value and type?
    private static final Logger LOG = LoggerFactory.getLogger(InitService.class);

    private final RarityRepository rarityRepository;
    private final SupertypeRepository superTypeRepository;
    private final SubTypeRepository subTypeRepository;
    private final TypeRepository typeRepository;

    private final WebClient webClient;

    public void initTypes() {
        Mono<ValueData> response = sendRequest("/types");

        processResponse(response, Type.class, typeRepository);
    }

    public void initSubTypes() {
        Mono<ValueData> response = sendRequest("/subtypes");

        processResponse(response, SubType.class, subTypeRepository);
    }

    public void initSuperTypes() {
        Mono<ValueData> response = sendRequest("/supertypes");

        processResponse(response, SuperType.class, superTypeRepository);
    }

    public void initRarities() {
        Mono<ValueData> response = sendRequest("/rarities");

        processResponse(response, Rarity.class, rarityRepository);
    }

    private Mono<ValueData> sendRequest(final String uri) {
        return webClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ValueData.class);
    }

    @SuppressWarnings("rawtypes")
    private void processResponse(final Mono<ValueData> response, final Class<? extends ValueEntity> entity, final JpaRepository repository) {
        response.subscribe(valueWrapper -> {
            // todo :: count amount of entities that were created
            if (valueWrapper != null) {
                for (String value : valueWrapper.getData()) {
                    processValue(value, entity, repository);
                }
            }
        });
    }

    @SuppressWarnings({"unchecked", "rawtypes"}) // todo :: can be handled better? process entire list here?
    private void processValue(final String value, final Class<? extends ValueEntity> entity, final JpaRepository repository) {
        try {
            ValueEntity instance = entity.getDeclaredConstructor(String.class).newInstance(value);

            ValueEntity savedEntity = (ValueEntity) repository.save(instance);

            // fixme :: ids are not separate between the value types
            LOG.info(
                    "ID: " + savedEntity.getId() + " | Value: " + savedEntity.getValue()
                    + " | Type: " + entity + " | Repository: " + repository.getClass()
            );

        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            LOG.warn("Could not save entity", e);
        }
    }
}
