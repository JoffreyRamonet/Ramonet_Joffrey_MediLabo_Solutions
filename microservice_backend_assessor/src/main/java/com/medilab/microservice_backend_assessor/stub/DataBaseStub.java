package com.medilab.microservice_backend_assessor.stub;

import com.medilab.microservice_backend_assessor.bean.NoteBean;
import com.medilab.microservice_backend_assessor.bean.PatientBean;
import com.medilab.microservice_backend_assessor.model.Trigger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public final class DataBaseStub {
    
    private final List<PatientBean> patientBeanList =
            List.of(new PatientBean("73052162-5644-47c6-a76e-a3e6ee17eedb", "TestNone", "Test", "1966-12-31", "F",
                            "1 Brookside St", "100-222-3333"),
                    new PatientBean("7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd", "TestInDanger", "Test", "2004-06-18", "M",
                            "3 Club Road", "300-444-5555"),
                    new PatientBean("90229de5-9024-4cdd-8dec-f7c54669b336", "TestBorderline", "Test", "1945-06-24", "M",
                            "2 High St", "200-333-4444"),
                    new PatientBean("d341589d-906c-4d67-8f24-d3db885efe9b", "TestEarlyOnset", "Test", "2002-06-28", "F",
                            "4 Valley Dr", "400-555-6666"));
    
    private final List<Trigger> triggerList =
            List.of(new Trigger("73052162-5644-47c6-a76e-a3e6ee17eedb", "Hémoglobine A1C"),
                    new Trigger("7e1gu729-f6fd-3574-b8b1-0e3t34bbe6fd", "Réaction"),
                    new Trigger("7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd", "Taille"),
                    new Trigger("7e9gu759-f6dd-4274-b8b3-0e3a34bbe6fd", "Fumeuse"),
                    new Trigger("7e9hd719-f6da-4474-b8p8-0e3a37bbe6md", "Cholestérol"),
                    new Trigger("90228de5-9084-4jkd-8dec-f7c52569b486", "Rechute"),
                    new Trigger("90229de5-9024-4cdd-8dec-f7c54669b336", "Microalbumine"),
                    new Trigger("90289de5-9084-4cdd-8dec-f7c58669b336", "Fumeur"),
                    new Trigger("90289de5-9084-4clp-8dec-f7c58669b875", "Fume"),
                    new Trigger("d341589d-247c-4d67-8f24-d3db247efe9b", "Vertige"),
                    new Trigger("d341589d-906c-4d67-8f24-d3db885efe9b", "Poids"),
                    new Trigger("d342589d-976c-4d67-8f84-d3ob835efe9f", "Anormal"),
                    new Trigger("d342629d-956c-4h67-8f54-d3db832efe9f", "Anticrops"),
                    new Trigger("d492589d-836c-4d67-8f24-d3ob835efe1f", "Anormale"));
    
    private final List<NoteBean> noteBeanList =
            List.of(new NoteBean("39dbeca5-74bc-44ed-973b-03a197d852b4", "90229de5-9024-4cdd-8dec-f7c54669b336",
                            "Le patient déclare avoir fait une Réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être Anormale",
                            LocalDateTime.of(2024, Month.JUNE, 1, 17, 36, 18)),
                    new NoteBean("a98867f3-e962-4058-a0c2-f07a8fddbd96", "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd",
                            "Le patient déclare qu'il Fume depuis peu plus",
                            LocalDateTime.of(2024, Month.JUNE, 1, 17, 40, 26)),
                    new NoteBean("44ede411-e853-4769-9528-792dab40a18f", "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd",
                            "Le patient déclare qu'il est Fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de Cholestérol LDL élevé",
                            LocalDateTime.of(2024, Month.JUNE, 1, 18, 2, 10)),
                    new NoteBean("aaf77e48-df7d-4bc0-a5af-57c95da9010d", "90229de5-9024-4cdd-8dec-f7c54669b336",
                            "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est Anormale dernièrement",
                            LocalDateTime.of(2024, Month.JUNE, 2, 8, 12, 23)),
                    new NoteBean("2945adc7-894b-4d0c-897f-672a3e543dfc", "d341589d-906c-4d67-8f24-d3db885efe9b",
                            "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments",
                            LocalDateTime.of(2024, Month.JUNE, 2, 8, 41, 58)),
                    new NoteBean("5b595c79-6f6b-490b-b98a-00319f4f5afb", "d341589d-906c-4d67-8f24-d3db885efe9b",
                            "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps",
                            LocalDateTime.of(2024, Month.JUNE, 2, 9, 10, 25)),
                    new NoteBean("a9442622-b367-4fe7-9cb5-d5e41312dcde", "d341589d-906c-4d67-8f24-d3db885efe9b",
                            "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé",
                            LocalDateTime.of(2024, Month.JUNE, 2, 9, 36, 30)),
                    new NoteBean("9c77c96c-4228-4abd-9fc2-83763a6e8a95", "d341589d-906c-4d67-8f24-d3db885efe9b",
                            "Taille, Poids, Cholestérol, Vertige et Réaction",
                            LocalDateTime.of(2024, Month.JUNE, 2, 14, 51, 26)),
                    new NoteBean("dce39adb-2221-4f82-b37e-d801e16e073c", "73052162-5644-47c6-a76e-a3e6ee17eedb",
                            "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé",
                            LocalDateTime.of(2024, Month.JUNE, 2, 17, 3, 48)));
    
    
    public List<PatientBean> getPatients() {
        return patientBeanList;
    }
    
    public List<Trigger> getTriggers() {
        return triggerList;
    }
    
    public List<NoteBean> getNotes() {
        return noteBeanList;
    }
}
