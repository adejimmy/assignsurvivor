package com.example.userdetails.domains.services;

import com.example.userdetails.dto.ResourcesDto;

import java.util.List;

public interface ResourcesService {

    List<ResourcesDto> getResources(String survivorId);


}
