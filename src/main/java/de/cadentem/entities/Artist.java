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
public class Artist implements IEntity {
    public static final String BASE = "artists";

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDateTime birthYear;

    public Artist(final String name, final LocalDateTime birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getBase() {
        return Artist.BASE;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
