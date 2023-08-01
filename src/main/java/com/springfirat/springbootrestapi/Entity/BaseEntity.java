package com.springfirat.springbootrestapi.Entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
    //Serializable bir network den taşıyabilme yeteneği veya bir diske yazdırıp okuyabiliyoruz.
    //Bu alanlarımız bütün tablolarımızda ortak olucak bunu da @MappedSuperClass anatosyonudur.
    private Date creDate;
    private String createdBy;
    private Date updateDate;
    private String updateBy;
}
