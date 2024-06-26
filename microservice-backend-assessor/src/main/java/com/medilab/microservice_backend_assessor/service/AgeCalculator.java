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

/**
 * The AgeCalculator expose just one public method that return if the birthdate is more or less thirty years old.
 */
@Service
@Slf4j
public class AgeCalculator {
    
    /**
     * Age configured.
     */
    private final int AGE_SEPARATOR = 30;
    
    /**
     * Call ageCalculator method to get the number of years between the birthdate and now and compare it with the value of AGE_SEPARATOR's.
     *
     * @param birthdate - String
     * @return boolean - true if more than the value of AGE_SEPARATOR and false if is not.
     * @see #ageCalculator(String)
     */
    public boolean moreThirty(String birthdate) {
        
        boolean isMoreThirty = ageCalculator(birthdate) >= AGE_SEPARATOR;
        
        log.debug("AgeCalculator - moreThirty - isMoreThirty: " + isMoreThirty);
        
        return isMoreThirty;
    }
    
    /**
     * Calculates the difference in years between the birthdate past and now.
     * Calls the birthdateFormater method to use the birthdate in the right format.
     *
     * @param birthdate - String
     * @return - int
     * @see #birthdateFormater(String)
     */
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
    
    /**
     * Method that get a birthdate and return it with the right format.
     * Gets the birthdate in yyyy-MM-dd and format it like MM/dd/yyyy.
     *
     * @param birthdate - String
     * @return - String
     */
    private String birthdateFormater(String birthdate) {
        
        List<String> array = Arrays.asList(birthdate.split("-"));
        Collections.reverse(array);
        
        String formatedBirthdate = (array.get(1) + "/" + array.get(0) + "/" + array.get(2)).replaceAll(" ", "");
        
        log.debug("AgeCalculator - birthdateFormater: " + formatedBirthdate);
        
        return formatedBirthdate;
    }
}