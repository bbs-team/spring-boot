package com.mskim.test.response;

import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Result
 */
@Getter
@Setter
@ToString
public class Result {

  private static final String SUCCESS_MSG = "success";
  private static final String FAIL_MSG = "fail";

  private String status;
  private String message;

  private Result(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public static ResponseEntity<Result> createSuccessResult() {
    return ResponseEntity.ok(new Result(SUCCESS_MSG, ""));
  }

  public static ResponseEntity<Result> createSuccessResult(String message) {
    return ResponseEntity.ok(new Result(SUCCESS_MSG, message));
  }

  public static ResponseEntity<Result> createFailResult(String message) {
    return ResponseEntity.ok(new Result(FAIL_MSG, message));
  }

}