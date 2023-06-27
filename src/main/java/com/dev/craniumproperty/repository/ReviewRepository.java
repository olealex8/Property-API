package com.dev.craniumproperty.repository;

import java.util.List;
import java.util.Optional;

import com.dev.craniumproperty.entity.ReviewEntity;

public interface ReviewRepository {
    List<ReviewEntity> findAll();

    Optional<ReviewEntity> findById(Integer id);

    int deleteById(Integer id);

    int insert(ReviewEntity review);

    int update(ReviewEntity review);
}
