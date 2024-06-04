package com.medilab.microserviceback.stub;

import com.medilab.microserviceback.model.Patient;

import java.util.ArrayList;
import java.util.List;

public final class DataBaseStub {
    
    private final List<Patient> persons = new ArrayList<>(List.of(
           new Patient("73052162-5644-47c6-a76e-a3e6ee17eedb", "TestNone", "Test", "1966-12-31 ", "F", "1 Brookside St", "100-222-3333"),
           new Patient("90229de5-9024-4cdd-8dec-f7c54669b336", "TestBorderline", "Test", "1945-06-24", "M", "2 High St", "200-333-4444"),
           new Patient("7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd", "TestInDanger", "Test", "2004-06-18", "M", "3 Club Road", "300-444-5555"),
           new Patient("d341589d-906c-4d67-8f24-d3db885efe9b", "TestEarlyOnset", "Test", "2002-06-28", "F", "4 Valley Dr", "400-555-6666")
    ));
    
    public List<Patient> getPatients() {
        return persons;
    }
    
}
