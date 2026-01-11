package com.bloomfilterdemo.controller;

import com.bloomfilterdemo.service.UsernameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/username")
@RequiredArgsConstructor
public class UserNameController {
  private final UsernameService usernameService;

  @PostMapping("/save")
  public ResponseEntity<String> saveUserName(
      @RequestBody String username) throws BadRequestException {
    if (usernameService.isUsernameTaken(username)) {
      logAndThrowUsernameAlreadyTaken(username);
    }
    synchronized (this) {
      // Double check within synchronized block
      if (usernameService.isUsernameTaken(username)) {
        logAndThrowUsernameAlreadyTaken(username);
      }
      usernameService.saveUserName(username);
      log.info("Saved username: {}", username);
    }
    return ResponseEntity.ok("Username saved successfully");
  }

  private static void logAndThrowUsernameAlreadyTaken(String username) throws BadRequestException {
    log.error("Username {} is already taken", username);
    throw new BadRequestException("Username is already taken");
  }
}
