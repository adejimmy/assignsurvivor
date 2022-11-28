package com.example.userdetails.repositories;

import com.example.userdetails.domains.SurvivorStatus;
import com.example.userdetails.domains.Survivors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
@Repository
public interface SurvivorRepository extends PagingAndSortingRepository<Survivors, Long>{

    Survivors findByIdsurvivor(String idsurvivor);

    List<Survivors> findByStatus(SurvivorStatus status);

    List<Survivors> findAll();



}
