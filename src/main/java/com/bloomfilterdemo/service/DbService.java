package com.bloomfilterdemo.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DbService {
  private static final Set<String> usernames = new HashSet<>();

  public void saveUserName(String username) {
    usernames.add(username);
  }

  public boolean checkUserNameExists(String username) {
    return usernames.contains(username);
  }

  public Set<String> getAllUserNames() {
    return new HashSet<>(usernames);
  }
}
