package com.medilab.microservice_backend_assessor.service;

/**
 * Enumeration of all possible return for the risk calculation.
 */
public enum Risk {
    NONE, BORDERLINE, INDANGER, EARLYONSET, ERROR;
    
    public String getRisk() {
        return switch(this) {
            case NONE -> "none";
            case BORDERLINE -> "borderline";
            case INDANGER -> "indanger";
            case EARLYONSET -> "earlyonset";
            case ERROR -> "unevaluated risk";
        };
    }
}