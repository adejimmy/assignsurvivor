package com.example.userdetails.domains.RequestModel;

import com.example.userdetails.domains.Resources;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SurvivorRequestModel {

    private String name;
    private Integer age;
    private String gender;




    private List<ResourcesRequestModel> resources;

    private double latitude;
    private double lonitude;
}
