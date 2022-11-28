package com.example.userdetails.dto;

import com.example.userdetails.domains.Resources;
import com.example.userdetails.domains.SurvivorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SurvivorsDto implements Serializable {


    private static final long serialVersionUID = -2591552627963833757L;

    public Long id;
    private String idsurvivor;
    private String name;
    private Integer age;
    private String gender;
    private Integer reportcount;
    private List<ResourcesDto> resources;
    private double latitude;
    private double lonitude;
    private SurvivorStatus status;


    public SurvivorsDto(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }
}
