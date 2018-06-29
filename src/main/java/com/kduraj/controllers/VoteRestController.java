package com.kduraj.controllers;

import com.kduraj.models.ClientDomain;
import com.kduraj.models.VoteDomain;
import com.kduraj.repositories.ClientRepository;
import com.kduraj.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Written by Kevin Duraj
 *
 */
@RestController
@RequestMapping("/vote")
public class VoteRestController {

    @Autowired
    private VoteRepository repository;

    @Autowired
    private ClientRepository clientRepository;


    /**
     * List all candidates
     * Example: http://localhost:8080/vote
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<VoteDomain>> getAllCandidates() {
        // return new ResponseEntity<>((Collection<VoteDomain>) repository.findAll(), HttpStatus.OK);
        // return new ResponseEntity<>( repository.findAllByOrderByCreation(), HttpStatus.OK);
        return new ResponseEntity<>( repository.findAllByOrderByCountDesc(), HttpStatus.OK);

    }


    /**
     * View the total vote count for a given object
     * Example: http://localhost:8080/vote/4
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<VoteDomain> getCandidateWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    /**
     * Create a new candidate
     * Example: http://localhost:8080/vote?add=Beatles
     * @param candidate
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, params = {"add"})
    public ResponseEntity<Collection<VoteDomain>> crateCandidate(@RequestParam(value = "add") String candidate) {

        List<VoteDomain> voteResult = repository.findByCandidate(candidate);
        if (voteResult.size() == 0) {
            repository.save(new VoteDomain(candidate, 0, new Date()));
        }

        return new ResponseEntity<>(repository.findByCandidate(candidate), HttpStatus.OK);

    }


    /**
     *  Add vote for an existing candidate, if does not exist add a new candidate
     *  Example: http://localhost:8080/vote?up=Galantis&client=frank
     * @param candidate
     * @param client
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, params = {"up","client"})
    public ResponseEntity<Collection<VoteDomain>> voteForCandidateUp (
            @RequestParam(value = "up") String candidate,
            @RequestParam(value = "client") String client
    ) {

        // Allow Client to vote only once
        List<ClientDomain> clientDomain =  clientRepository.findByClientId(client);

        if (clientDomain.size() == 0) {
            addVoteForCandidate(candidate, 1);
            clientRepository.save(new ClientDomain(client));
        }

        return new ResponseEntity<>(repository.findByCandidate(candidate), HttpStatus.OK);

    }

    /**
     * Add vote for an existing candidate, if does not exist add a new candidate
     * Example: http://localhost:8080/vote?down=Galantis&client=frank
     * @param candidate
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, params = {"down","client"})
    public ResponseEntity<Collection<VoteDomain>> voteForCandidateDown(
            @RequestParam(value = "down") String candidate,
            @RequestParam(value = "client") String client
    ) {

        // Allow Client to vote only once
        List<ClientDomain> clientDomain =  clientRepository.findByClientId(client);

        if (clientDomain.size() == 0) {
            addVoteForCandidate(candidate, -1);
            clientRepository.save(new ClientDomain(client));
        }

        return new ResponseEntity<>(repository.findByCandidate(candidate), HttpStatus.OK);

    }

    /**
     * Delete Candidate
     * Example: http://localhost:8080/vote?del=Galantis
     * @param candidate
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, params = {"del"})
    public ResponseEntity<Collection<VoteDomain>> delCandidate(@RequestParam(value = "del") String candidate) {

        List<VoteDomain> voteResult = repository.findByCandidate(candidate);
        repository.delete(voteResult.get(0).getId());

        return new ResponseEntity<>((Collection<VoteDomain>) repository.findAll(), HttpStatus.OK);
    }

    /**
     * Create a new candidate using POST method
     * @param input
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCandidate(@RequestBody VoteDomain input) {
        return new ResponseEntity<>(repository.save(input), HttpStatus.CREATED);
    }

    /**
     * Vote Up or Down for particular candidate
     * @param candidate
     * @param count
     */
    private void addVoteForCandidate(String candidate, Integer count) {

        Integer counter;
        List<VoteDomain> voteResult = repository.findByCandidate(candidate);

        if (voteResult.size() > 0) {
            counter = voteResult.get(0).getCount() + count;

            VoteDomain candidateDto = repository.findOne(voteResult.get(0).getId());
            candidateDto.setCount(counter);
            candidateDto.setCreation(new Date());
            repository.save(candidateDto);

        } else {
            counter = 1;
            repository.save(new VoteDomain(candidate, counter, new Date()));
        }

    }

}
