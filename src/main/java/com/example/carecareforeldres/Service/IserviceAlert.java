package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Alert;
import com.example.carecareforeldres.Entity.Patient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IserviceAlert {
    Alert creerAlerte(Integer idpatient, Integer idMedecin);
   Alert AlertPatient(Integer idpatient) throws IOException;
   Map<Integer, Float> getMedecinsDistance(Patient p) throws IOException;
   public Alert accteptermedecin(Integer idMedecin, Integer idalert);
   List<Alert> getAlertByMedecin(Integer idMedecin);
}
