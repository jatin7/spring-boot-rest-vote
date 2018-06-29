package com.kduraj.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ClientDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String clientId;


    /**
     * Client Domain
     * @param clientId
     */
    public ClientDomain(String clientId) {

        this.setClientId(clientId);
    }

    /**
     * Java Persistence API
     */
    public ClientDomain() {}


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
