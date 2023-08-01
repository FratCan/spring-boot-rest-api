package com.springfirat.springbootrestapi.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="Kulllanıcılar")
@Data //@Data ile toplu ekleriz tek tek eklemek istersek örneğin @Setter diyebiliriz
public class User extends BaseEntity{

    @Id
    @SequenceGenerator(name="user_seq_gen",sequenceName = "user_gen",initialValue = 100,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq_gen")
    @Column(name = "ID")
    private long id;
    @Column(name = "ISIM",length = 100)
    private String firstname;
    @Column(name="SOYISIM",length = 100)
    private String lastname;
}
