package com.medilab.microservice_backend_assessor.service;

import com.medilab.microservice_backend_assessor.bean.PatientBean;
import com.medilab.microservice_backend_assessor.microservice_client.PatientClient;
import com.medilab.microservice_backend_assessor.model.RiskResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RiskCalculator {
    
    private PatientClient patientClient;
    private TriggerCounter triggerCounter;
    private AgeCalculator ageCalculator;
    
    public RiskResult riskCalculator(String id) {
        
        PatientBean patient = patientClient.getPatientById(id);
        Integer triggerNumber = triggerCounter.triggerCounter(id);
        boolean moreThirty = ageCalculator.moreThirty(patient.birthDate());
        String gender = patient.gender();
        
        log.debug("RiskCalculator - riskCalculator - id parse: " + id);
        log.debug("RiskCalculator - riskCalculator - triggerNumber: " + triggerNumber);
        log.debug("RiskCalculator - riskCalculator - moreThirty: " + moreThirty);
        log.debug("RiskCalculator - riskCalculator - gender: " + gender);
        
        if(triggerNumber <= 1)
            return new RiskResult(Risk.NONE.getRisk());
        
        if(moreThirty) {
            switch(triggerNumber) {
                case 2, 3, 4, 5 -> {
                    return new RiskResult(Risk.BORDERLINE.getRisk());
                }
                case 6, 7 -> {
                    return new RiskResult(Risk.INDANGER.getRisk());
                }
                default -> {
                    return new RiskResult(Risk.EARLYONSET.getRisk());
                }
            }
        } else {
            switch(gender) {
                case ("M"):
                    switch(triggerNumber) {
                        case 3, 4 -> {
                            return new RiskResult(Risk.INDANGER.getRisk());
                        }
                        default -> {
                            return new RiskResult(Risk.EARLYONSET.getRisk());
                        }
                    }
                case ("F"):
                    switch(triggerNumber) {
                        case 4, 5, 6 -> {
                            return new RiskResult(Risk.INDANGER.getRisk());
                        }
                        default -> {
                            return new RiskResult(Risk.EARLYONSET.getRisk());
                        }
                    }
            }
        }
        
        return null;
    }
}
