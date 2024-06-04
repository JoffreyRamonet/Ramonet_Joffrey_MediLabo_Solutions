package com.medilab.microservice_backend_assessor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class AgeCalculator {
    
    private final int AGE_SEPARATOR = 30;
    
    public boolean moreThirty(String birthdate) {
        
        boolean isMoreThirty = ageCalculator(birthdate) >= AGE_SEPARATOR;
        
        log.debug("AgeCalculator - moreThirty - isMoreThirty: " + isMoreThirty);
        
        return isMoreThirty;
    }
    
    private int ageCalculator(String birthdate) {
        String stringFormat = "MM/dd/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(stringFormat);
        formatter = formatter.withLocale(Locale.ENGLISH);
        LocalDate formatedBirthdate = LocalDate.parse(birthdateFormater(birthdate), formatter);
        LocalDate now = LocalDate.now();
        
        Period period = Period.between(formatedBirthdate, now);
        int age = period.getYears();
        
        log.debug("AgeCalculator - ageCalculator - formatedBirthdate: " + formatedBirthdate);
        log.debug("AgeCalculator - ageCalculator - now: " + now);
        log.debug("AgeCalculator - ageCalculator - age: " + age);
        
        return age;
    }
    
    private String birthdateFormater(String birthdate) {
        
        List<String> array = Arrays.asList(birthdate.split("-"));
        Collections.reverse(array);
        
        String formatedBirthdate = (array.get(1) + "/" + array.get(0) + "/" + array.get(2)).replaceAll(" ", "");
        
        log.debug("AgeCalculator - birthdateFormater: " + formatedBirthdate);
        
        return formatedBirthdate;
    }
}