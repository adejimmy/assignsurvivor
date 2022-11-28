package com.example.userdetails.repositories;

import com.example.userdetails.domains.Resources;
import com.example.userdetails.domains.Survivors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResourcesRepository extends CrudRepository<Resources, Long> {

    List<Resources> findAllBySurvivors(Survivors survivors);

    Resources findByIdresource(String idresource);

}
