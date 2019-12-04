package com.mskim.test.controller;

import java.util.Optional;

import com.mskim.test.entity.User;
import com.mskim.test.repository.UserRepository;
import com.mskim.test.response.Result;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * UserController
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

  private static Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepo;

  @GetMapping()
  public Iterable<User> getUserList() {
    return userRepo.findAll();
  }

  @GetMapping(value="{idx}")
  public Object getUser(@PathVariable int idx) {
    User user = getUserByIdx(idx);
    if (user == null ) {
      return Result.createFailResult("User not exist.");
    }
    return user;
  }
  
  @PostMapping()
  public ResponseEntity<Result> addUser(@RequestBody User user) {
    userRepo.save(user);
    return Result.createSuccessResult("User create success.");
  }
  
  @PutMapping(value="{idx}")
  public ResponseEntity<Result> putMethodName(@PathVariable int idx, @RequestBody User user) {
    log.info("update user idx : {}, new user information : {}", idx, user);

    User oriUser = getUserByIdx(idx);
    if (oriUser == null) {
      return Result.createFailResult("User note exist.");
    }
    
    user.setIdx(idx);
  
    if (Strings.isEmpty(user.getName())) {
      user.setName(oriUser.getName());
    }
  
    if (user.getAge() == 0) {
      user.setAge(oriUser.getAge());
    }

    userRepo.save(user);
    return Result.createSuccessResult("User update success.");
  }

  @DeleteMapping(value="{idx}")
  public ResponseEntity<Result> removeUser(@PathVariable int idx) {
    log.info("delete user idx : {}", idx);
    userRepo.deleteById(idx);
    return Result.createSuccessResult("user delete success.");
  }

  private User getUserByIdx(int idx) {
    Optional<User> user = userRepo.findById(idx);
    return user.isPresent() ? user.get() : null;
  }

}