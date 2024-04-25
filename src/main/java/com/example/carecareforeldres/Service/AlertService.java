package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Alert;
import com.example.carecareforeldres.Entity.Medecin;
import com.example.carecareforeldres.Entity.Patient;
import com.example.carecareforeldres.Repository.AlertRepository;
import com.example.carecareforeldres.Repository.MedecinRepository;
import com.example.carecareforeldres.Repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudinary.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AlertService implements IserviceAlert {
    ServiceMapBox box;
    AlertRepository alertRepository;
    MedecinRepository medecinRepository;
    PatientRepository patientRepository;

    @Override
    public Alert creerAlerte(Integer idpatient, Integer idMedecin) {
        Medecin medecin = medecinRepository.findById(idMedecin).get();
        Patient patient = patientRepository.findById(idpatient).get();
        if (patient != null && medecin != null) {
            Alert alert = new Alert();
            alert.getMedecinssy().add(medecin);
            alert.setPatient(patient);
            alert.setMessage("je suis un  admin je detect un error de serveur");
            alert.setDatedalert(LocalDate.now());
            return alertRepository.save(alert);
        }
        return null;
    }

    @Override
    public Alert AlertPatient(Integer idpatient) throws IOException {
        Alert alert=new Alert();
        Patient patient = patientRepository.findById(idpatient).get();
        Map<Integer, Float> medecinsDistances = getMedecinsDistance(patient);
        List<Medecin> medecins = new ArrayList<>();
        for (Integer medecinId : medecinsDistances.keySet()) {
            Medecin medecin = medecinRepository.findById(medecinId).orElse(null);
            if (medecin != null) {
                medecins.add(medecin);
            } else {
                System.err.println("Medecin not found with ID: " + medecinId);
            }
        }
        alert.setMessage("Besoin d'assistance médicale urgente");
        alert.setDatedalert(LocalDate.now());
        alert.setPatient(patient);
        alertRepository.save(alert);
        alert.getMedecinssy().addAll(medecins);
        alertRepository.save(alert);
        return alert;

    }

    @Override
    public Map<Integer, Float> getMedecinsDistance(Patient p) throws IOException {
        List<Medecin> medecins = medecinRepository.findAll();
        Map<Integer, Float> medecinsDistances = new HashMap<>();

        for (Medecin medecin : medecins) {
            Map<String, String> infoMap = box.getInfo(String.valueOf(p.getX()), String.valueOf(p.getY()), String.valueOf(medecin.getX()), String.valueOf(medecin.getY()));
            String distanceStr = infoMap.get("Distance");
            if (distanceStr != null) {
                distanceStr = distanceStr.replaceAll("[^0-9.]", "");
                float distance = Float.parseFloat(distanceStr);
                medecinsDistances.put(medecin.getIdMedecin(), distance);
            } else {
                System.err.println("Distance information missing for medecin ID: " + medecin.getIdMedecin());
            }
        }

        // Sort the map entries by value (distance)
        List<Map.Entry<Integer, Float>> sortedEntries = medecinsDistances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        // Create a new map to hold the closest distances (top 3)
        Map<Integer, Float> closestDistances = new HashMap<>();
        int count = 0;
        for (Map.Entry<Integer, Float> entry : sortedEntries) {
            closestDistances.put(entry.getKey(), entry.getValue());
            count++;
            if (count >= 3) {
                break;
            }
        }

        return closestDistances;
    }

   @Override
   public Alert accteptermedecin(Integer idMedecin, Integer idalert) {
       Optional<Medecin> medecinOpt = medecinRepository.findById(idMedecin);
       Optional<Alert> alertOpt = alertRepository.findById(idalert);

       if (medecinOpt.isPresent() && alertOpt.isPresent()) {
           Medecin medecin = medecinOpt.get();
           Alert alert = alertOpt.get();
           alert.getMedecinssy().clear();
           alert.getMedecinssy().add(medecin);

           return alertRepository.save(alert);
       } else {

           if (!medecinOpt.isPresent()) {
               System.err.println("Medecin not found with ID: " + idMedecin);
           }
           if (!alertOpt.isPresent()) {
               System.err.println("Alert not found with ID: " + idalert);
           }
           return null;
       }
   }

    @Override
    public List<Alert> getAlertByMedecin(Integer idMedecin) {
        Medecin m =medecinRepository.findById(idMedecin).get();
        return m.getAlertssy();
    }


}





