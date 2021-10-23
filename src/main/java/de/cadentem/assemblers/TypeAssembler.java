package de.cadentem.assemblers;

import de.cadentem.controllers.TypeController;
import de.cadentem.entities.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeAssembler extends GenericAssembler<Type> {
    public TypeAssembler() {
        super(TypeController.class);
    }
}
