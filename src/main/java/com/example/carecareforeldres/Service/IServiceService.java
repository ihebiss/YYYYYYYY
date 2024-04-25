package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.ServiceWithShelterDTO;
import com.example.carecareforeldres.Entity.Service;

import java.util.List;

public interface IServiceService {
    List<Service> retrieveAllService();

    Service addService(Service s);

    Service updateService(Service s);

    Service retrieveService(Long idService);

    void removeService(Long idService);

    public List<Service>  affectServiceToShelter(Long idShelter) ;

    public Service addServiceAndAssignToShelter (Service service, Long idShelter);
    public List<Service> getServicesByShelterId(Long idShelter);

    public List<ServiceWithShelterDTO> getAllServicesWithShelters() ;
    public void updateAllServicesWithShelters(List<ServiceWithShelterDTO> updatedServices);
}

