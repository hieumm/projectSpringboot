package com.example.projectb2c.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter

@NoArgsConstructor
@Data
public class Chart {
    String nameCate;
    Long counByCate;

    public Chart(String nameCate, Long counByCate) {
        this.nameCate = nameCate;
        this.counByCate = counByCate;
    }
}
