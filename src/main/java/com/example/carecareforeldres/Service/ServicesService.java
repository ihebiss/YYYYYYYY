package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.ServiceWithShelterDTO;
import com.example.carecareforeldres.Entity.Service;
import com.example.carecareforeldres.Entity.Shelter;
import com.example.carecareforeldres.Repository.ServiceRepository;
import com.example.carecareforeldres.Repository.ShelterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
@Slf4j
@AllArgsConstructor
public class ServicesService implements IServiceService {
    ServiceRepository serviceRepository;
    ShelterRepository shelterRepository;


    @Override
    public List<Service> retrieveAllService() {
        return serviceRepository.findAll();
    }

    @Override
    public Service addService(Service s) {
        return serviceRepository.save(s);
    }

    @Override
    public Service updateService(Service s) {
        return serviceRepository.save(s);
    }

    @Override
    public Service retrieveService(Long idService) {
        return serviceRepository.findById(idService).get();
    }

    @Override
    public void removeService(Long idService) {
        serviceRepository.deleteById(idService);
    }



    @Override
    public List<Service>  affectServiceToShelter(Long idShelter) {
        Shelter shelter = shelterRepository.findById(idShelter).get();
        List<Service> s1=serviceRepository.findAll();
        for (int i=0;i<s1.size();i++){
            s1.get(i).setShelter(shelter);
            serviceRepository.save(s1.get(i));

        }
        return s1;
    }

    @Override
    public Service addServiceAndAssignToShelter(Service service, Long idShelter) {
        Shelter shelter = shelterRepository.findById(idShelter).get();
        service.setShelter(shelter);
        serviceRepository.save(service);
        return service;
    }

    @Override
    public List<Service> getServicesByShelterId(Long idShelter) {
        Shelter shelter = shelterRepository.findById(idShelter).orElse(null);
        if (shelter != null) {
            return serviceRepository.findByShelter(shelter);
        } else {
            // Gérer le cas où le shelter avec l'ID spécifié n'existe pas
            return Collections.emptyList();
        }
    }
    @Override
    public List<ServiceWithShelterDTO> getAllServicesWithShelters() {
        List<Service> services = serviceRepository.findAll();
        List<ServiceWithShelterDTO> serviceDTOs = new ArrayList<>();

        for (Service service : services) {
            ServiceWithShelterDTO serviceDTO = new ServiceWithShelterDTO();
            serviceDTO.setIdService(service.getIdService());
            serviceDTO.setNomService(service.getNomService());
            serviceDTO.setDescription(service.getDescription());
            serviceDTO.setTypeService(service.getTypeService());

            Shelter shelter = service.getShelter();
            if (shelter != null) {
                serviceDTO.setIdShelter(shelter.getIdShelter());
                serviceDTO.setNomShelter(shelter.getNomShelter());
            }

            serviceDTOs.add(serviceDTO);
        }

        return serviceDTOs;
    }

    @Override
    public void updateAllServicesWithShelters(List<ServiceWithShelterDTO> updatedServices) {
        for (ServiceWithShelterDTO updatedService : updatedServices) {
            // Récupérez le service à partir de la base de données
            Service service = serviceRepository.findById(updatedService.getIdService()).orElse(null);

            // Vérifiez si le service existe
            if (service != null) {
                // Mettez à jour les champs du service avec les nouvelles valeurs
                service.setNomService(updatedService.getNomService());
                service.setDescription(updatedService.getDescription());
                service.setTypeService(updatedService.getTypeService());

                // Récupérez l'abri associé à partir de la base de données
                Shelter shelter = shelterRepository.findById(updatedService.getIdShelter()).orElse(null);

                // Vérifiez si l'abri existe
                if (shelter != null) {
                    // Associez le service à l'abri
                    service.setShelter(shelter);
                }

                // Enregistrez les modifications dans la base de données
                serviceRepository.save(service);
            }
        }
    }
}
