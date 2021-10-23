package de.cadentem.assemblers;

import de.cadentem.controllers.TypeController;
import de.cadentem.entities.SuperType;
import org.springframework.stereotype.Component;

@Component
public class SuperTypeAssembler extends GenericAssembler<SuperType> {
    public SuperTypeAssembler() {
        super(TypeController.class);
    }
}
