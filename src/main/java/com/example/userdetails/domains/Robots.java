package com.example.userdetails.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Robots {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    private String model;
    private String serialNumber;
    private Timestamp manufacturedDate;
    private String category;
}
