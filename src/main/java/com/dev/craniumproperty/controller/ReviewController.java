package com.dev.craniumproperty.controller;

import com.dev.craniumproperty.exception.ResourceNotFoundException;
import com.dev.craniumproperty.entity.ReviewEntity;
import com.dev.craniumproperty.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// @RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    // get all users
    @GetMapping("/review")
    public List<ReviewEntity> getAllUsers()
    {
        return reviewRepository.findAll();
    }

    // create user rest API
    @PostMapping("/review")
    public Map<String, Boolean> createUser(@RequestBody ReviewEntity review)  {

        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = reviewRepository.insert(review) > 0 ?
                response.put("created", Boolean.TRUE) :
                response.put("created", Boolean.FALSE);

        return response;

    }

    // get user by id rest api
    @GetMapping("/review/{id}")
    public ReviewEntity findUserById(@PathVariable Integer id) {

        ReviewEntity review = reviewRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException
                ("User not exist with id :" + id));
        return review;
    }

   // update user rest api
   @PutMapping("/review/{id}")
   public Map<String, Boolean> updateUser(@PathVariable Integer id, @RequestBody ReviewEntity reviewDetails) {

        ReviewEntity review = reviewRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException
               ("User not exist with id :" + id));
               reviewDetails.setId(id);
       Map<String, Boolean> response = new HashMap<>();

       Boolean bool = reviewRepository.update(reviewDetails) > 0 ?
               response.put("updated", Boolean.TRUE) :
               response.put("updated", Boolean.FALSE);

      return response;
    }

    // delete user rest api
    @DeleteMapping("/review/{id}")
    public Map<String, Boolean> deleteUser
               (@PathVariable Integer id) {

                ReviewEntity review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User not exist with id :" + id));

        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = reviewRepository.deleteById(review.getId()) > 0 ?
                response.put("deleted", Boolean.TRUE) :
                response.put("deleted", Boolean.FALSE);
        return response;
    }
}
