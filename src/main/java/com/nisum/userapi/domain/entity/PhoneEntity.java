package com.nisum.userapi.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "phones")
public class PhoneEntity extends AuditFields  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @Column(name = "number")
    private String number;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "country_code")
    private String countryCode;

    public PhoneEntity() {
    }

    public PhoneEntity(UserEntity user, String number, String cityCode, String countryCode, LocalDateTime created) {
        this.user = user;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.setCreated(created);
    }

}
