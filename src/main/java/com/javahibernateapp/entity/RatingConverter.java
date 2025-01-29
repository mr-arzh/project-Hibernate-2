package com.javahibernateapp.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
 class RatingConverter implements AttributeConverter<Rating, String> {


  @Override
  public String convertToDatabaseColumn(Rating attribute) {
   return attribute.getValue();
  }

  @Override
  public Rating convertToEntityAttribute(String dbData) {
   Rating[] values = Rating.values();
   for (Rating value : values) {
    if (value.getValue().equals(dbData)) {
     return value;
    }
   }
   return null;
  }
 }