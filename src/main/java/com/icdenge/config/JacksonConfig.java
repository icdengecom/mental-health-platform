package com.icdenge.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customJackson() {
    return b -> {
      b.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(ofPattern("yyyy-MM-dd HH:mm:ss")));
      b.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(ofPattern("yyyy-MM-dd HH:mm:ss")));

      b.serializerByType(LocalDate.class, new LocalDateSerializer(ofPattern("yyyy-MM-dd")));
      b.deserializerByType(LocalDate.class, new LocalDateDeserializer(ofPattern("yyyy-MM-dd")));

      b.serializerByType(LocalTime.class, new LocalTimeSerializer(ofPattern("HH:mm:ss")));
      b.deserializerByType(LocalTime.class, new LocalTimeDeserializer(ofPattern("HH:mm:ss")));

      b.serializationInclusion(JsonInclude.Include.NON_NULL);
      b.failOnUnknownProperties(false);
      b.indentOutput(true);
      b.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    };
  }
}
