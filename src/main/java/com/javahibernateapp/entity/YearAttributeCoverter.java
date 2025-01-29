package com.javahibernateapp.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;

@Converter(autoApply = true)
 class YearAttributeConverter implements AttributeConverter<Year, Short> {


    @Override
    public Short convertToDatabaseColumn(Year attribute) {
        if (attribute != null) {
            var value = attribute.getValue();
            return (short) value;
        }
        return null;
    }

    @Override
    public Year convertToEntityAttribute(Short dbData) {
        if (dbData != null){
            return Year.of(dbData);
        }
        return null;
    }
}
