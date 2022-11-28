package com.example.userdetails.dto;

import com.example.userdetails.domains.Survivors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResourcesDto implements Serializable {


    private static final long serialVersionUID = -2591552627963833757L;

    private Long id;
    private String idresource;
    private String resourcesname;

    private SurvivorsDto survivors;
}
