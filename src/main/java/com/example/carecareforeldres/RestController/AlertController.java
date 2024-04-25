package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Alert;
import com.example.carecareforeldres.Entity.Patient;
import com.example.carecareforeldres.Repository.PatientRepository;
import com.example.carecareforeldres.Service.IserviceAlert;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/alert")
public class AlertController {
    IserviceAlert iserviceAlert;
    PatientRepository patientRepository;
   /* @PostMapping("/create/{idpatient}/{idMedecin}")
    public ResponseEntity<Alert> creerAlerte(@PathVariable("idpatient") Integer idpatient, @PathVariable("idMedecin") Integer idMedecin, @RequestBody String message) {
        Alert alert = iserviceAlert.creerAlerte(idpatient, idMedecin, message);
        if (alert != null) {
            return ResponseEntity.ok(alert);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }*/
    @PostMapping("/patientcreate/{idpatient}")
    public Alert AlertPatient(@PathVariable("idpatient")Integer idpatient) throws IOException {
        return iserviceAlert.AlertPatient(idpatient);
    }

    @GetMapping("/distance/{patientId}")
    public Map<Integer, Float> getMedecinsDistance(@PathVariable Integer patientId) {
        try {
            Patient patient = patientRepository.findById(patientId).get();
            return iserviceAlert.getMedecinsDistance(patient);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
   // @PutMapping("/putt/{idMedecin}/{idalert}")
    //public Alert accteptermedecin(Integer idMedecin,Integer idalert){
      //  return iserviceAlert.accteptermedecin(idMedecin,idalert);
    //}
    @PutMapping("/accept/{idMedecin}/{idalert}")
    public ResponseEntity<Alert> acceptAlert(@PathVariable Integer idMedecin, @PathVariable Integer idalert) {
        Alert updatedAlert = iserviceAlert.accteptermedecin(idMedecin, idalert);

        if (updatedAlert != null) {
            return ResponseEntity.ok(updatedAlert); // Succès, retourne l'alerte modifiée
        } else {
            return ResponseEntity.notFound().build(); // Échec, retourne une réponse 404
        }
    }
@GetMapping("/getalert/{idMedecin}")
    public List<Alert> getAlertByMedecin(@PathVariable("idMedecin") Integer idMedecin){
        return iserviceAlert.getAlertByMedecin(idMedecin);
    }
}
