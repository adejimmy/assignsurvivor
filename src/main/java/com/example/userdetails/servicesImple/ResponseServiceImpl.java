package com.example.userdetails.servicesImple;

import com.example.userdetails.domains.Resources;
import com.example.userdetails.domains.Survivors;
import com.example.userdetails.dto.ResourcesDto;
import com.example.userdetails.repositories.ResourcesRepository;
import com.example.userdetails.repositories.SurvivorRepository;
import com.example.userdetails.domains.services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseServiceImpl implements ResourcesService {


    @Autowired
    SurvivorRepository survivorRepository;

    @Autowired
    ResourcesRepository resourcesRepository;

    @Override
    public List<ResourcesDto> getResources(String survivorId) {

        List<ResourcesDto>returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        Survivors survivor = survivorRepository.findByIdsurvivor(survivorId);
        if(survivor==null) {
            return returnValue;
        }
        Iterable<Resources>resources=resourcesRepository.findAllBySurvivors(survivor);

        for(com.example.userdetails.domains.Resources Resources:resources) {
            returnValue.add(modelMapper.map(Resources, ResourcesDto.class));
        }

        return returnValue;
    }


}
