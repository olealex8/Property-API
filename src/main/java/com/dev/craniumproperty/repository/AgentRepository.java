package com.dev.craniumproperty.repository;

import com.dev.craniumproperty.entity.AgentEntity;
import java.util.List;
import java.util.Optional;

public interface AgentRepository {
    List<AgentEntity> findAll();

    Optional<AgentEntity> findById(Integer id);

    int deleteById(Integer id);

    int insert(AgentEntity agent);

    int update(AgentEntity agent);

    int findAvgRating(Integer id);

    // Optional<Agent> findAvgRating(Integer id);
    // Agent findAvgRating(Integer id);
}
