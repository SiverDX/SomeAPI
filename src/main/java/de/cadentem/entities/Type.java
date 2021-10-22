package de.cadentem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Type implements ValueEntity{
    @Id
    @GeneratedValue
    private long id;

    private String value;

    public Type(final String value) {
        this.value = value;
    }
}
