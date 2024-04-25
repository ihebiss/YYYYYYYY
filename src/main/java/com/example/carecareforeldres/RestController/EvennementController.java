package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.DTO.EvennementDto;
import com.example.carecareforeldres.Entity.Evennement;
import com.example.carecareforeldres.Service.IEvennementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/evennements")
public class EvennementController {

    private final IEvennementService evennementService;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/ajouterevent")
    public ResponseEntity<?> addEvennement(@RequestBody EvennementDto evennementDto) {
        try {
            Evennement savedEvennement = evennementService.saveEvennement(evennementDto);
            return new ResponseEntity<>(savedEvennement, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/date/{date}")
    public ResponseEntity<List<EvennementDto>> getEvennementByDate(
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<EvennementDto> evennements = evennementService.getEvennementDtoByDate(date);

        if (evennements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(evennements, HttpStatus.OK);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/verifierDateOccupee/{date}")
    public ResponseEntity<Boolean> verifierDateOccupee(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        boolean isDateOccupied = evennementService.isDateOccupied(date);
        return ResponseEntity.ok(isDateOccupied);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/allevent")
    public List<EvennementDto> getAllEvennements() {
        List<EvennementDto> evennements = evennementService.getAllEvennements();
        return evennements;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/{id}")
    public ResponseEntity<EvennementDto> getEvennementById(@PathVariable("id") Long id) {
        try {
            EvennementDto evennementDto = evennementService.getEvennementDtoById(id);
            return new ResponseEntity<>(evennementDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////


    @PutMapping("/updateevent")
    public ResponseEntity<?> updateEvennement(@RequestBody EvennementDto evennementDto) {
        try {
            EvennementDto updatedEvennement = evennementService.updateEvennement(evennementDto);
            return new ResponseEntity<>(updatedEvennement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////


    @DeleteMapping("/{id}")
    public void deleteEvennement(@PathVariable("id") Long id) {
        evennementService.deleteEvennement(id);

    }

    @GetMapping("/top-five-users-comments")
    public ResponseEntity<List<Object[]>> getTopFiveUsersWithMostComments() {
        List<Object[]> topFiveUsers = evennementService.getTopFiveUsersWithMostComments();
        return new ResponseEntity<>(topFiveUsers, HttpStatus.OK);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    // Ajout de la méthode pour enregistrer un utilisateur à un événement
    @PostMapping("/{eventId}/register/{userId}")
    public ResponseEntity<?> registerUserToEvent(@PathVariable Long eventId, @PathVariable Long userId) {
        try {
            evennementService.registerUserToEvent(eventId, userId);
            return ResponseEntity.ok("Inscription réussie");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    // @PostMapping("/evennements/{eventId}/inscrire/{userId}")
    //  public ResponseEntity<?> registerUserToEvent(@PathVariable Long eventId, @PathVariable Long userId) {
    ///    try {
    //     evennementService.registerUserToEvent(eventId, userId);
    //      return new ResponseEntity<>("Inscription réussie", HttpStatus.OK);
    //   } catch (RuntimeException e) {
    //     return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    //  }
    //    }

}