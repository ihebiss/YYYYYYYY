package com.example.carecareforeldres.RestController;


import com.example.carecareforeldres.DTO.ServiceWithShelterDTO;
import com.example.carecareforeldres.Entity.Service;
import com.example.carecareforeldres.Service.IServiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Service")
public class ServiceController {
    IServiceService iServiceService;
    @GetMapping("/allService")
    public List<Service> getService() {
        List<Service> listServices = iServiceService.retrieveAllService();
        return listServices;
    }

    @GetMapping("/Service/{id}")
    public Service retrieveService(@PathVariable("id") Long id) {
        return iServiceService.retrieveService(id);
    }

    @PostMapping("/addService")
    public Service addEtudiant(@RequestBody Service b) {
        Service Service = iServiceService.addService(b);
        return Service;
    }

    @PutMapping("/UpdateService")
    public Service updateService(@RequestBody Service e) {
        Service Service= iServiceService.updateService(e);
        return Service;
    }

    @DeleteMapping("/RemoveService/{id}")
    public void removeService(@PathVariable("id") Long id) {
        iServiceService.removeService(id);
    }


    @PutMapping("/affect/{idShelter}")
    @ResponseBody
    public List<Service> affectServiceToShelterrrr( @PathVariable("idShelter") Long idShelter) {
        List<Service> services=iServiceService.affectServiceToShelter(idShelter);
        return services;

    }

    @PutMapping("/Service/{idShelter}")
    @ResponseBody
    public Service ajouterEtaffecterServiceToShelter( @PathVariable("idShelter")Long idShelter,@RequestBody Service service ) {

        return iServiceService.addServiceAndAssignToShelter(service,idShelter);
    }
    @GetMapping("/shelter/services/{idShelter}")
    public List<Service> getServicesByShelterId(@PathVariable("idShelter") Long idShelter) {
        return iServiceService.getServicesByShelterId(idShelter);
    }

    @GetMapping("/shelter/AllservicesWithShelter")
    public List<ServiceWithShelterDTO> getAllServicesWithSheltersss() {
        return iServiceService.getAllServicesWithShelters();
    }
    @PutMapping("/Service/updateAllService")
    public void updateAllServicesWithShelters( @RequestBody List<ServiceWithShelterDTO> updatedServices) {

        iServiceService.updateAllServicesWithShelters(updatedServices);
    }
}

