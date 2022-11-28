package com.example.userdetails.servicesImple;

import com.example.userdetails.controllers.InitResult;
import com.example.userdetails.controllers.SurvivorNotFoundException;
import com.example.userdetails.domains.Resources;
import com.example.userdetails.domains.SurvivorStatus;
import com.example.userdetails.domains.Survivors;
import com.example.userdetails.dto.ResourcesDto;
import com.example.userdetails.dto.SurvivorsDto;
import com.example.userdetails.exceptions.ErrorMessages;
import com.example.userdetails.exceptions.SurvivorServiceException;
import com.example.userdetails.helper.PersonGenerator;
import com.example.userdetails.helper.Util;
import com.example.userdetails.repositories.SurvivorRepository;
import com.example.userdetails.services.SurvivorService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.github.dozermapper.core.Mapper;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class SurvivorServiceImpl implements SurvivorService {
    private final Logger logger = LoggerFactory.getLogger(SurvivorServiceImpl.class);

    @Autowired
    Util utils;

    @Autowired
    SurvivorRepository surRepository;

    public Iterable<Survivors> saveAll(Collection<Survivors> users){


        return null;
    }



 

    @Override
    public SurvivorsDto createUser(SurvivorsDto survivor) {

        for(int i=0; i<survivor.getResources().size();i++) {
            ResourcesDto resourc = survivor.getResources().get(i);
            resourc.setIdresource(utils.generateResourceId(30));
            resourc.setSurvivors(survivor);
            survivor.getResources().set(i, resourc);
        }

        ModelMapper modelMapper = new ModelMapper();
        Survivors survivors =modelMapper.map(survivor, Survivors.class);
        survivors.setIdsurvivor(utils.generateSurvivorId(30));
        survivors.setStatus(SurvivorStatus.UNINFECTED);
        survivors.setReportcount(0);
        Survivors storedSurvDetails=surRepository.save(survivors);

        SurvivorsDto returnValue=modelMapper.map(storedSurvDetails, SurvivorsDto.class);


        return returnValue;
    }


    @Override
    public SurvivorsDto updateSurvivor(String id, SurvivorsDto survive) {

        SurvivorsDto returnValue= new SurvivorsDto();

        Survivors surviver = surRepository.findByIdsurvivor(id);
        if (surviver == null) {
            throw new SurvivorServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        surviver.setLatitude(survive.getLatitude());
        surviver.setLonitude(survive.getLonitude());

        Survivors updatedSurDetails = surRepository.save(surviver);

        BeanUtils.copyProperties(updatedSurDetails, returnValue);

        return returnValue;
    }



    public Double getPercentageInfected()  throws NoSuchElementException {

        List<Survivors> survivors = surRepository.findAll();

      //  List<TourRating> ratings = tourRatingRepository.findByTourId(verifyTour(tourId).getId());
        double average = survivors.stream()
                .mapToInt(obj -> obj.getStatus().equals(SurvivorStatus.INFECTED) ? 1 : 0)
                .summaryStatistics()
                .getAverage();
        double percentage=average*100;
        return percentage;
    }


    public Double getPercentageNotInfected()  throws NoSuchElementException {

        List<Survivors> survivors = surRepository.findAll();

        //  List<TourRating> ratings = tourRatingRepository.findByTourId(verifyTour(tourId).getId());
        double average = survivors.stream()
                .mapToInt(obj -> obj.getStatus().equals(SurvivorStatus.UNINFECTED) ? 1 : 0)
                .summaryStatistics()
                .getAverage();
        double percentage=average*100;
        return percentage;
    }


    public List<Survivors> lookupAllInfected()  {

        return surRepository.findByStatus(SurvivorStatus.INFECTED);
    }

    public List<Survivors> lookupAllNonInfected()  {

        return surRepository.findByStatus(SurvivorStatus.UNINFECTED);
    }

    public SurvivorsDto reportSurvivor(String id, SurvivorsDto survive) {

        SurvivorsDto returnValue= new SurvivorsDto();

        Survivors surviver = surRepository.findByIdsurvivor(id);
        if (surviver == null) {
            throw new SurvivorServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        if (surviver.getReportcount()>1){
            surviver.setStatus(SurvivorStatus.INFECTED);
        }
        Integer counts=surviver.getReportcount();
        Integer totcounts=counts+1;
        surviver.setReportcount(totcounts);

        Survivors updatedSurDetails = surRepository.save(surviver);

        BeanUtils.copyProperties(updatedSurDetails, returnValue);

        return returnValue;
    }













}
