package com.medilab.microservice_backend_note.stub;

import com.medilab.microservice_backend_note.model.Note;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public final class DataBaseStub {
    
   
    private final List<Note> noteBeanList =
            List.of(new Note("39dbeca5-74bc-44ed-973b-03a197d852b4", "90229de5-9024-4cdd-8dec-f7c54669b336",
                            "Le patient déclare avoir fait une Réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être Anormale",
                            LocalDateTime.of(2024, Month.JUNE, 1, 17, 36, 18)),
                    new Note("a98867f3-e962-4058-a0c2-f07a8fddbd96", "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd",
                            "Le patient déclare qu'il Fume depuis peu plus",
                            LocalDateTime.of(2024, Month.JUNE, 1, 17, 40, 26)),
                    new Note("44ede411-e853-4769-9528-792dab40a18f", "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd",
                            "Le patient déclare qu'il est Fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de Cholestérol LDL élevé",
                            LocalDateTime.of(2024, Month.JUNE, 1, 18, 2, 10)),
                    new Note("aaf77e48-df7d-4bc0-a5af-57c95da9010d", "90229de5-9024-4cdd-8dec-f7c54669b336",
                            "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est Anormale dernièrement",
                            LocalDateTime.of(2024, Month.JUNE, 2, 8, 12, 23)),
                    new Note("2945adc7-894b-4d0c-897f-672a3e543dfc", "d341589d-906c-4d67-8f24-d3db885efe9b",
                            "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments",
                            LocalDateTime.of(2024, Month.JUNE, 2, 8, 41, 58)),
                    new Note("5b595c79-6f6b-490b-b98a-00319f4f5afb", "d341589d-906c-4d67-8f24-d3db885efe9b",
                            "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps",
                            LocalDateTime.of(2024, Month.JUNE, 2, 9, 10, 25)),
                    new Note("a9442622-b367-4fe7-9cb5-d5e41312dcde", "d341589d-906c-4d67-8f24-d3db885efe9b",
                            "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé",
                            LocalDateTime.of(2024, Month.JUNE, 2, 9, 36, 30)),
                    new Note("9c77c96c-4228-4abd-9fc2-83763a6e8a95", "d341589d-906c-4d67-8f24-d3db885efe9b",
                            "Taille, Poids, Cholestérol, Vertige et Réaction",
                            LocalDateTime.of(2024, Month.JUNE, 2, 14, 51, 26)),
                    new Note("dce39adb-2221-4f82-b37e-d801e16e073c", "73052162-5644-47c6-a76e-a3e6ee17eedb",
                            "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé",
                            LocalDateTime.of(2024, Month.JUNE, 2, 17, 3, 48)));
    
    public List<Note> getNotes() {
        return noteBeanList;
    }
}
