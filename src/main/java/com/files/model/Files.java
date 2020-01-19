package com.files.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EvgenovDS on 22.11.2018.
 */
@Entity
@Table(name = "files")
public class Files implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAddress", referencedColumnName = "id")
    private Address address;

    @Column
    private String name;

    @Column
    private String size;

    public Files() {
    }

    public Files(Address address, String name, String size) {
        this.address = address;
        this.name = name;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public int compareTo(Object o) {
        Files files = (Files) o;
        return this.getId() - files.getId();
    }
}
