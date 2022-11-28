package com.example.userdetails.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private double latitude;
    private double lonitude;
    @OneToOne
    @JoinColumn(name="survivors_Id")
    private Survivors survivors;
}
