package com.medilab.microservice_backend_assessor.controller;

import com.medilab.microservice_backend_assessor.model.RiskResult;
import com.medilab.microservice_backend_assessor.service.RiskCalculator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The AssessorController expose one endpoint.
 * <p>
 * Use GET http request.
 * The default url to call the controller is:
 * {@snippet lang = "Properties"}:
 * "/microservice_backend_accessor/v1/accessor"
 * </p>
 * <p>
 * Request return a RiskResult model.
 *
 * @see RiskResult
 * The request require a patient's id.
 * Require a RiskCalculator to apply business treatments and receive data.
 * @see RiskCalculator
 * </p>
 */
@RestController
@RequestMapping("/microservice_backend_assessor/v1/assessor")
@AllArgsConstructor
@Slf4j
public class AssessorController {
    
    private RiskCalculator riskCalculator;
    
    @GetMapping("/{id}")
    public RiskResult getTriggerResult(
            @PathVariable
            final String id) {
        
        log.debug("AccessorController - getTriggerResult - id parsed: " + id);
        
        return riskCalculator.riskCalculator(id);
    }
}
