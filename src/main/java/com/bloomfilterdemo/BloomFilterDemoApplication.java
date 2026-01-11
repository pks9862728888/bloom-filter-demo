package com.bloomfilterdemo;

import com.bloomfilterdemo.controller.UserNameController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BloomFilterDemoApplication implements CommandLineRunner {
  private final UserNameController userNameController;

  public static void main(String[] args) {
    SpringApplication.run(BloomFilterDemoApplication.class, args);
  }

  @Override
  public void run(String... args) {
    saveUserName("testuser");
    saveUserName("testuser");
    saveUserName("testuser2");
  }

  private void saveUserName(String username) {
    try {
      userNameController.saveUserName(username);
    } catch (Exception e) {
    }
  }
}
