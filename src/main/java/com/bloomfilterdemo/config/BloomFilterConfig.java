package com.bloomfilterdemo.config;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class BloomFilterConfig {

  public static final int EXP_INSERTIONS_BLOOM_FILTER = 1000000;
  public static final double FPP = 0.01;

  @Bean
  public BloomFilter<String> usernameBloomFilter() {
    return BloomFilter.create(
        Funnels.stringFunnel(StandardCharsets.UTF_8),
        EXP_INSERTIONS_BLOOM_FILTER, // expected insertions
        FPP      // false positive probability
    );
  }
}
