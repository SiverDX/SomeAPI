package de.cadentem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDateTime birthYear;

    public Card(final String name, final LocalDateTime birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }
}
