package com.spoty.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.spoty.app.domain.enumeration.DeviceStatus;

/**
 * A Device.
 */
@Entity
@Table(name = "device")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "port_number")
    private Integer portNumber;

    @Column(name = "emulator_name")
    private String emulatorName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DeviceStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Device name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public Device model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public Device portNumber(Integer portNumber) {
        this.portNumber = portNumber;
        return this;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public String getEmulatorName() {
        return emulatorName;
    }

    public Device emulatorName(String emulatorName) {
        this.emulatorName = emulatorName;
        return this;
    }

    public void setEmulatorName(String emulatorName) {
        this.emulatorName = emulatorName;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public Device status(DeviceStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        return id != null && id.equals(((Device) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Device{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", portNumber=" + getPortNumber() +
            ", emulatorName='" + getEmulatorName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
