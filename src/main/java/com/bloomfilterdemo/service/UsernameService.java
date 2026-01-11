package com.bloomfilterdemo.service;

import com.google.common.hash.BloomFilter;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsernameService {
  private final BloomFilter<String> usernameBloomFilter;
  private final DbService dbService;

  @PostConstruct
  public void loadUserNames() {
    log.info("Loading existing usernames into Bloom Filter");
    dbService.getAllUserNames().forEach(usernameBloomFilter::put);
  }

  public boolean isUsernameTaken(String username) {
    if (usernameBloomFilter.mightContain(username)) {
      // High chance that username is taken, but some might be false positive also
      // Hit db in case high reliability is required, else it's ok to assume its taken
      return true;
    }
    return false;
  }

  public void saveUserName(String username) {
    dbService.saveUserName(username);
    usernameBloomFilter.put(username);
  }
}
