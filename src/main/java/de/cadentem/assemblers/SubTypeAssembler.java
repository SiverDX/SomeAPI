package de.cadentem.assemblers;

import de.cadentem.controllers.TypeController;
import de.cadentem.entities.SubType;
import org.springframework.stereotype.Component;

@Component
public class SubTypeAssembler extends GenericAssembler<SubType> {
    public SubTypeAssembler() {
        super(TypeController.class);
    }
}
