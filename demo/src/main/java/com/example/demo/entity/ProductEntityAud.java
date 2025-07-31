package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product_aud")
//@AuditTable(value = "product_aud", schema = "public")
//@Audited
public class ProductEntityAud {

    @Id
    private Integer rev;
    private Integer id;
    private Integer revtype;
    private String name;
    @Column(name = "tariff_id")
    private Integer tariffId;
    private Integer version;
    @Column(name = "time_and_date")
    private Instant timedate;

    public ProductEntityAud() {
    }

    public ProductEntityAud(Integer id, Integer rev, Integer revtype, String name, Integer tariffId, int version, Instant timedate) {
        this.id = id;
        this.rev = rev;
        this.revtype = revtype;
        this.name = name;
        this.tariffId = tariffId;
        this.version = version;
        this.timedate = timedate;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public Integer getRevType() {
        return revtype;
    }

    public void setRevType(Integer revtype) {
        this.revtype = revtype;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }


    public void setTimedate(Instant timedate) {
        this.timedate = timedate;
    }
    public Instant getTimedate() {
        return timedate;
    }
}
