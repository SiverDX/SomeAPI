package de.cadentem.assemblers;

import de.cadentem.controllers.TypeController;
import de.cadentem.entities.Rarity;
import org.springframework.stereotype.Component;

@Component
public class RarityAssembler extends GenericAssembler<Rarity> {
    public RarityAssembler() {
        super(TypeController.class);
    }
}
