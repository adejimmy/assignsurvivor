package com.example.userdetails.services;

import com.example.userdetails.domains.Survivors;
import com.example.userdetails.dto.SurvivorsDto;

import java.util.Collection;
import java.util.List;

public interface SurvivorService {

    SurvivorsDto createUser(SurvivorsDto survivorsDto);


    Iterable<Survivors> saveAll(Collection<Survivors> users);


    SurvivorsDto updateSurvivor(String id, SurvivorsDto survivorsDto);

    SurvivorsDto reportSurvivor(String id, SurvivorsDto survivorsDto);

    Double getPercentageInfected();

    Double getPercentageNotInfected();

    List<Survivors> lookupAllInfected();

    List<Survivors> lookupAllNonInfected();

}
