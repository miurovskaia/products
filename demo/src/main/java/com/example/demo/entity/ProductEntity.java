package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.Instant;

@Audited
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
//@AuditTable(value = "product_aud", schema = "public")
//@Audited
public class ProductEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revinfo_seq")
        @SequenceGenerator(name = "revinfo_seq", sequenceName = "product_id_seq") */
        @Column(unique = true, nullable = false)
        private Integer id;
        private String name;

        private Integer tariffId;
        @Version
        @Column(name = "version")
        @Audited
        private int version;
        @Column(name = "time_and_date")
        private Instant timedate;


        public ProductEntity() {
        }

        public ProductEntity(Integer id, String name,String conditions, Integer tariffId, Instant timedate) {
                this.id = id;
                this.name = name;
                this.tariffId = tariffId;
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

        public void setId(Integer id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }



        public Integer getVersion() {
                return version;
        }
        public void setVersion(Integer version) {
                this.version = version;
        }
        public void setTariffId(Integer tariffId) {
                this.tariffId = tariffId;
        }

        public void setTimedate(Instant timedate) {
                this.timedate = timedate;
        }
        public Instant getTimedate() {
                return timedate;
        }
}
