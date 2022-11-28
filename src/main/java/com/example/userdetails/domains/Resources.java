package com.example.userdetails.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="resources")
public class Resources implements Serializable {


    private static final long serialVersionUID = -2591552627963833757L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String idresource;
    private String resourcesname;




    @ManyToOne
    @JoinColumn(name="survivors_Id")
    @JsonIgnore
    private Survivors survivors;
}
