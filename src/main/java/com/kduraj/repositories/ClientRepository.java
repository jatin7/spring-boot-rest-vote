package com.kduraj.repositories;

import com.kduraj.models.ClientDomain;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Written by: Kevin Duraj
 */
public interface ClientRepository extends CrudRepository<ClientDomain, Long> {

    List<ClientDomain> findByClientId(String clientId);


}