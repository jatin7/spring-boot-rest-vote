package com.kduraj.repositories;

import com.kduraj.models.VoteDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

/**
 * Written by: Kevin Duraj
 */
public interface VoteRepository extends CrudRepository<VoteDomain, Long> {

    List<VoteDomain> findByCandidate(String candidate);

    Collection<VoteDomain>  findAllByOrderByCreation();

    Collection<VoteDomain>  findAllByOrderByCountDesc();
}
