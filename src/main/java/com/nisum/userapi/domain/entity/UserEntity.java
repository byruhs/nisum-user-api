package com.nisum.userapi.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class UserEntity extends AuditFields implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "is_active")
    private Boolean active;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PhoneEntity> phones = new HashSet<>();

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    public UserEntity() {

    }
}
