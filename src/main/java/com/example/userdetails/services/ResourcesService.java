package com.example.userdetails.services;

import com.example.userdetails.dto.ResourcesDto;

import java.util.List;

public interface ResourcesService {

    List<ResourcesDto> getResources(String survivorId);


}
