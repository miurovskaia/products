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

        private Instant timeAndDate;


        public ProductEntity() {
        }

        public ProductEntity(Integer id, String name,String conditions, Integer tariffId, Instant timeAndDate) {
                this.id = id;
                this.name = name;
                this.tariffId = tariffId;
                this.timeAndDate = timeAndDate;

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

        public void setTimeAndDate(Instant timeAndDate) {
                this.timeAndDate = timeAndDate;
        }
        public Instant getTimeAndDate() {
                return timeAndDate;
        }
}
