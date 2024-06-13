package com.medilab.microservice_backend_assessor.service;

import com.medilab.microservice_backend_assessor.bean.PatientBean;
import com.medilab.microservice_backend_assessor.microservice_client.PatientClient;
import com.medilab.microservice_backend_assessor.model.RiskResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service class to perform the risk calculation.
 * <p>
 * Requires a PatientClient to obtain patient data which the risk should be calculated.
 *
 * @see PatientClient
 * Requires a TriggerCounter to obtain the number of trigger in the patient's notes.
 * @see TriggerCounter
 * Requires AgeCalculator to know if the patient is more or less than thirty years old.
 * @see AgeCalculator
 * </p>
 */
@Service
@AllArgsConstructor
@Slf4j
public class RiskCalculator {
    
    private final PatientClient patientClient;
    private final TriggerCounter triggerCounter;
    private final AgeCalculator ageCalculator;
    
    /**
     * The method to perform the risk calculation.
     * <p>
     * To calculates the risk the method requires:
     * The gender of the patient
     * @see PatientClient#getPatientById(String)
     * The number of triggers in the patient notes.
     * @see TriggerCounter#triggerCounter(String)
     * If the patient is more or less than thirty years old.
     * @see AgeCalculator#moreThirty(String)
     *
     * @param id - String - the id of the patient which the risk should be calculated.
     * @return - RiskResult
     * </p>
     */
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
