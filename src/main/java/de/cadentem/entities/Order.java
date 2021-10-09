package de.cadentem.entities;

import de.cadentem.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "CUSTOMER_ORDER")
public class Order implements IEntity {
    public static final String BASE = "orders";

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private Status status;

    public Order(final String description, final Status status) {
        this.description = description;
        this.status = status;
    }

    public String getBase() {
        return Order.BASE;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
