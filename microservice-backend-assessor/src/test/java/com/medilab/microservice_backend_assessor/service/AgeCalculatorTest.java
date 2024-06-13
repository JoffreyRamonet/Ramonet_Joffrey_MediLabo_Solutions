package com.medilab.microservice_backend_assessor.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgeCalculatorTest {
    
    private final String LESS_THIRTY = "2020-12-31";
    private final String MORE_THIRTY = "1966-12-31";
    
    
    private final AgeCalculator ageCalculator = new AgeCalculator();
    
    @Test
    void moreThirtyShouldReturnFalseWhenTheAgeIsLessOfThirtyTest() {
        boolean result = ageCalculator.moreThirty(LESS_THIRTY);
        
        assertFalse(result);
    }
    
    @Test
    void moreThirtyShouldReturnTrueWhenTheAgeIsMoreThirtyTest() {
        boolean result = ageCalculator.moreThirty(MORE_THIRTY);
        
        assertTrue(result);
    }
}
