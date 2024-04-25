package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.EvennementDto;
import com.example.carecareforeldres.Entity.Evennement;
import com.example.carecareforeldres.Repository.EvennementRepository;
import com.example.carecareforeldres.Repository.UserRepository;
import com.example.carecareforeldres.demo.EmailService;
import com.example.carecareforeldres.mapper.EvennementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.carecareforeldres.Entity.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EvennementService implements IEvennementService{

    private final EvennementRepository evennementRepository;
    private final EvennementMapper evennementMapper;
    private final UserRepository userRepository ;
    private final EmailService emailService;


    ////////////////////////////////////////////////////////////////////////////////
    @Override
    public EvennementDto toDto(Evennement evennement) {
        return evennementMapper.toDto(evennement);
    }
    ////////////////////////////////////////////////////////////////////////////////

    @Override
    public Evennement saveEvennement(EvennementDto evennementDto) {
        // Vérifier si la date est déjà occupée
        if (isDateOccupied(evennementDto.getDate())) {
            throw new RuntimeException("Date occupée");
        }
        Evennement evennement = evennementMapper.toEntity(evennementDto);
        return evennementRepository.save(evennement);
    }
    @Override
    // Méthode pour vérifier si la date est déjà occupée
    public boolean isDateOccupied(LocalDate date) {
        return evennementRepository.findByDate(date).isPresent();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public EvennementDto updateEvennement(EvennementDto evennementDto) {
        // Vérifier si la date est déjà occupée
        if (isDateOccupied(evennementDto.getDate())) {
            throw new RuntimeException("Date occupée");
        }

        //  evennementMapper.updateEntityFromDto(evennementDto, existingEvennement);

        // Enregistrement de l'entité mise à jour
        evennementRepository.save(evennementMapper.toEntity(evennementDto));

        // Conversion de l'entité mise à jour en DTO pour le retour
        return evennementDto;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public EvennementDto getEvennementDtoById(Long id) {
        Evennement event = getEvennementById(id);
        return evennementMapper.toDto(event);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<EvennementDto> getEvennementDtoByDate(Date date) {
        List<Evennement> events = this.evennementRepository.getEvennementByDate(date);
        return evennementMapper.toDtos(events);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<EvennementDto> getAllEvennements() {
        List<Evennement> events = evennementRepository.findAll();

        return evennementMapper.toDtos(events);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void deleteEvennement(Long id) {
        evennementRepository.deleteById(id);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<Object[]> getTopFiveUsersWithMostComments() {
        return evennementRepository.findTopFiveUsersWithMostComments();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Evennement getEvennementById(Long id) {
        return evennementRepository.findById(id).get();
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void registerUserToEvent(Long eventId, Long userId) {
        Evennement event = getEvennementById(eventId);
        User user = userRepository.findById(userId.intValue())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (event.getUsers().contains(user)) {
            throw new RuntimeException("Vous êtes déjà inscrit à cet événement");
        }

        if (event.getPlaceMax() <= 0) {
            throw new RuntimeException("Toutes les places sont occupées pour cet événement");
        }

        event.getUsers().add(user);
        user.getEvennements().add(event);

        event.setPlaceMax(event.getPlaceMax() - 1);
        evennementRepository.save(event);
        userRepository.save(user);

        // Envoi de l'e-mail de confirmation d'inscription
        String userEmail = user.getEmail();
        String subject = "Confirmation d'inscription à l'événement";
        String message = "Vous avez été inscrit avec succès à l'événement. Merci!";
        try {
            emailService.sendEmail(userEmail, subject, message);
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'e-mail de confirmation : " + e.getMessage());
            // Gérer l'erreur d'envoi d'e-mail
        }
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//@Override
//public void registerUserToEvent(Long eventId, Long userId) {
    // Evennement event = getEvennementById(eventId);
    //  User user = userRepository.findById(userId)
    //         .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    //  if (event.getUsers().contains(user)) {
    //   throw new RuntimeException("Vous êtes déjà inscrit à cet événement");
    //  }
    //  if (event.getPlaceMax() <= 0) {
    //     throw new RuntimeException("Toutes les places sont occupées pour cet événement");
    // }
    // event.getUsers().add(user);
    //  event.setPlaceMax(event.getPlaceMax() - 1);
    //  evennementRepository.save(event);
    // Envoi de l'e-mail de confirmation d'inscription
    //  String userEmail = user.getEmail();
    // String subject = "Confirmation d'inscription à l'événement";
    // String message = "Vous avez été inscrit avec succès à l'événement. Merci!";
    // emailService.sendEmail(userEmail, subject, message);
//}
}