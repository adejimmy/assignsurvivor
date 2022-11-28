package com.example.userdetails.domains.ResponseModel;


import com.example.userdetails.domains.RequestModel.ResourcesRequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SurvivorResponseModel {

    private String name;
    private Integer age;
    private String gender;




   // private List<ResourcesResponseModel> resources;

    private double latitude;
    private double lonitude;
}
