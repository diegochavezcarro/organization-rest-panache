package org.dchavez.restpanache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Organization extends PanacheEntity {
    public String name;

    public Long cuit;

    @OneToOne(cascade = CascadeType.ALL)
    public Contact contact;
}
