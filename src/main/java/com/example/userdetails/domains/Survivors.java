package com.example.userdetails.domains;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="survivors")
public class Survivors implements Serializable {


    private static final long serialVersionUID = -2591552627963833757L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String idsurvivor;
    @Column
    private String name;
    private Integer age;
    private String gender;
    private Integer reportcount;

    @OneToMany(mappedBy="survivors", cascade=CascadeType.ALL)
    private List<Resources> resources;

    private double latitude;
    private double lonitude;

    @Enumerated(EnumType.STRING )
    private SurvivorStatus status=SurvivorStatus.UNINFECTED;

}
