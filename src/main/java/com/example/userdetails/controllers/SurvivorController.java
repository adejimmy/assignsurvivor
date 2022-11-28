package com.example.userdetails.controllers;

import com.example.userdetails.domains.RequestModel.SurvivorRequestModel;
import com.example.userdetails.domains.ResponseModel.SurvivorResponseModel;
import com.example.userdetails.dto.SurvivorsDto;
import com.example.userdetails.domains.services.ResourcesService;
import com.example.userdetails.domains.services.SurvivorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/survivors")	//http://localhost:8080/survivors
public class SurvivorController {

    @Autowired
    SurvivorService survivorService;

    @Autowired
    ResourcesService resourcesService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public SurvivorResponseModel createUser(@RequestBody SurvivorRequestModel survivorDetails) throws Exception {

        if (survivorDetails.getName().isEmpty()) throw new
                NullPointerException("The object is null");


        SurvivorResponseModel returnValue = new SurvivorResponseModel();

//		 Copy the sourceObject into a target object:
        ModelMapper modelMapper = new ModelMapper();
        SurvivorsDto survivorDto = modelMapper.map(survivorDetails, SurvivorsDto.class);


        SurvivorsDto createdsurvivor = survivorService.createUser(survivorDto);

        returnValue = modelMapper.map(createdsurvivor, SurvivorResponseModel.class);

        return returnValue;

    }

    @PutMapping(path="/{id}",
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public SurvivorResponseModel updateUser(@PathVariable String id, @RequestBody SurvivorRequestModel survivorsDetails) {

        SurvivorResponseModel returnValue=new SurvivorResponseModel();
        SurvivorsDto survivorsDto=new SurvivorsDto();

        // Copies sourceObject into a target object:
        BeanUtils.copyProperties(survivorsDetails, survivorsDto);

        SurvivorsDto updatedUser=survivorService.updateSurvivor(id, survivorsDto);

        BeanUtils.copyProperties(updatedUser,returnValue );

        return returnValue;
    }


    @PutMapping(path="/report/{id}",
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public SurvivorResponseModel reportSurvivor(@PathVariable String id,@RequestBody SurvivorRequestModel survivorsDetails) {

        SurvivorResponseModel returnValue=new SurvivorResponseModel();
        SurvivorsDto survivorsDto=new SurvivorsDto();

        // Copies sourceObject into a target object:
        BeanUtils.copyProperties(survivorsDetails, survivorsDto);

        SurvivorsDto updatedUser=survivorService.reportSurvivor(id, survivorsDto);



        BeanUtils.copyProperties(updatedUser,returnValue );

        return returnValue;
    }


    @GetMapping("/infected")
    public AbstractMap.SimpleEntry<String, Double> getPercentageInfected() {

        return new AbstractMap.SimpleEntry<String, Double>("percentage", survivorService.getPercentageInfected());
    }

    @GetMapping("/notinfected")
    public AbstractMap.SimpleEntry<String, Double> getPercentageNotInfected() {

        return new AbstractMap.SimpleEntry<String, Double>("percentage", survivorService.getPercentageNotInfected());
    }


    @GetMapping("/allinfected")
    public List<SurvivorsDto> getAllInfected() {

        return survivorService.lookupAllInfected().stream()
                .map(t -> new SurvivorsDto(t.getName(), t.getGender()))
                .collect(Collectors.toList());
    }

    @GetMapping("/allnotinfected")
    public List<SurvivorsDto> getAllNotInfected() {

        return survivorService.lookupAllNonInfected().stream()
                .map(t -> new SurvivorsDto(t.getName(), t.getGender()))
                .collect(Collectors.toList());
    }



    @GetMapping("/getrobots")
    public String request(String endpoint) throws Exception  {

        //if we are reading the json it means it reads one after the other
        //to make all of them to be as one string we use string builder
        StringBuilder sb = new StringBuilder();

        URL url = new URL(endpoint);

        // open a connection to this URL
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            // read in the bytes
            //input stream has methods that read bytes and next set of bytes
            InputStream inputStream = urlConnection.getInputStream();
            //Buffered Input stream extends input stream recieves input stream as a constructer( also argument)
            //reasds into an internal array
            //refill the array as bytes are read
            //mark and resets, for performance reason
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            // read them as characters.
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // read one line at a time.
            String inputLine = bufferedReader.readLine();
            while (inputLine != null) {
                // add this to our output
                sb.append(inputLine);
                // reading the next line
                inputLine = bufferedReader.readLine();
            }
        } finally {
            urlConnection.disconnect();
        }
        return sb.toString();

    }


}

