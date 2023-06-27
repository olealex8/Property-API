package com.dev.craniumproperty.controller;

import com.dev.craniumproperty.exception.ResourceNotFoundException;
import com.dev.craniumproperty.entity.AgentEntity;
import com.dev.craniumproperty.entity.AgentRatingEntity;
import com.dev.craniumproperty.repository.AgentRepository;
import com.dev.craniumproperty.util.JwtUtil;
import com.dev.craniumproperty.util.LibraryUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// @RequestMapping("/api")
public class AgentController {
    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private LibraryUtil libraryUtil;

    @Autowired
    private JwtUtil jwtUtil;

    // get all users
    @GetMapping("/agent")
    public List<AgentEntity> getAllUsers(@RequestHeader("Authorization") String bearerToken) throws Exception
    {
        // System.out.println(bearerToken);
        // jwtUtil.validateToken(bearerToken, agentEntity);

        return agentRepository.findAll();
    }

    // create user rest API
    @PostMapping("/agent")
    public Map<String, Boolean> createUser(@RequestBody AgentEntity agent)  {

        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = agentRepository.insert(agent) > 0 ?
                response.put("created", Boolean.TRUE) :
                response.put("created", Boolean.FALSE);

        return response;

    }

    // get user by id rest api
    @GetMapping("/agent/{id}")
    public AgentEntity findUserById(@PathVariable Integer id) {

        AgentEntity agent = agentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        return agent;
    }

   // update user rest api
   @PutMapping("/agent/{id}")
   public Map<String, Boolean> updateUser(@PathVariable Integer id, @RequestBody AgentEntity agentDetails) {

        AgentEntity agent = agentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        
        agentDetails.setId(id);

        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = agentRepository.update(agentDetails) > 0 ?
               response.put("updated", Boolean.TRUE) :
               response.put("updated", Boolean.FALSE);

        return response;
    }

    // delete user rest api
    @DeleteMapping("/agent/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable Integer id) {

        AgentEntity agent = agentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = agentRepository.deleteById(agent.getId()) > 0 ?
                response.put("deleted", Boolean.TRUE) :
                response.put("deleted", Boolean.FALSE);
        return response;
    }

    // get average rating of agent (RETURNS AVG RATING INTEGER)
    @GetMapping("/agent/rating/{id}")
    public int getAverageRating(@PathVariable Integer id)
    {
        AgentEntity agent = agentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        return agentRepository.findAvgRating(agent.getId());
    }

    // // get average rating of agent (RETURNS AGENT DATA + AVG RATING)
    // @GetMapping("/agent/rating/{id}")
    // public AgentRating getAverageRating(@PathVariable Integer id) {

    //     Agent agentId = agentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

    //     // Agent agent = agentRepository.findAvgRating(agentId.getId()).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
    //     AgentRating agent = agentRepository.findAvgRating(agentId.getId());

    //     return agent;
    // }

}
