package de.cadentem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class SubType implements ValueEntity{
    @Id
    @GeneratedValue
    private long id;

    private String value;

    public SubType(final String value) {
        this.value = value;
    }
}
