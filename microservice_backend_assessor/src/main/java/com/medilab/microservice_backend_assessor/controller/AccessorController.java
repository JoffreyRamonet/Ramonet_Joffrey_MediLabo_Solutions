package com.medilab.microservice_backend_assessor.controller;

import com.medilab.microservice_backend_assessor.model.RiskResult;
import com.medilab.microservice_backend_assessor.service.RiskCalculator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/microservice_backend_accessor/v1/accessor")
@AllArgsConstructor
@Slf4j
public class AccessorController {
    
    private RiskCalculator riskCalculator;
    
    @GetMapping("/{id}")
    public RiskResult getTriggerResult(
            @PathVariable
            final String id) {
        
        log.debug("AccessorController - getTriggerResult - id parsed: " + id);
        
        return riskCalculator.riskCalculator(id);
    }
}
