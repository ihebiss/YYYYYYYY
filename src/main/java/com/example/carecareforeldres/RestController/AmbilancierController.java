package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.DTO.AmbilancierDto;
import com.example.carecareforeldres.Entity.Ambilancier;
import com.example.carecareforeldres.Service.IServiceAmbilancier;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ambulancier")
@CrossOrigin("*")
public class AmbilancierController {
    IServiceAmbilancier serviceAmbilancier;
    @GetMapping("/retrieve-all-ambulancier")

    public List<AmbilancierDto> getAmbilanciers() {
        List<Ambilancier> ambilanciers = serviceAmbilancier.retrieveAllAmbilanciers();
        List<AmbilancierDto>list=ambilanciers.stream().map(ambilancier ->AmbilancierDto.toDto(ambilancier)).toList();
        return list ;

    }

    @GetMapping("/retrieve-ambulancier/{ambulancier-id}")

    public AmbilancierDto retrieveAmbilancier(@PathVariable("ambulancier-id") Integer idAmbilancier) {
        return AmbilancierDto.toDto(serviceAmbilancier.retrieveAmbilancier(idAmbilancier));
    }
    @PostMapping("/add-ambulancier")

    public AmbilancierDto addAmbilancier(@RequestBody AmbilancierDto ambilancier) {
        Ambilancier entity=AmbilancierDto.toEntity(ambilancier);
        return AmbilancierDto.toDto(serviceAmbilancier.addAmbilancier(entity));


    }

    @DeleteMapping("/remove-ambulancier/{ambulancier-id}")
    public void removeAmbilancier(@PathVariable("ambulancier-id") Integer idAmbilancier) {
        serviceAmbilancier.removeAmbilancier(idAmbilancier);
    }
    @PutMapping("/update-ambulancier")

    public AmbilancierDto updateAmbilancier(@RequestBody AmbilancierDto ambilancier) {
        Ambilancier entity=AmbilancierDto.toEntity(ambilancier);
        return AmbilancierDto.toDto(serviceAmbilancier.updateAmbilancier(entity));
    }
    @PutMapping("/ambulancier/{idAmb}")
    @ResponseBody


    public Ambilancier addAmbilancierToAmbulance(@RequestBody AmbilancierDto ambilancier, @PathVariable("idAmb")Long idAmb ) {
        Ambilancier entity=AmbilancierDto.toEntity(ambilancier);
        return serviceAmbilancier.addAmbulancierAndAssignToAmbulance(entity,idAmb);

    }


    @PutMapping("/ambulancier-dessaf/{idAmbilancier}")
    @ResponseBody
    public Ambilancier DesaffecterAmbulanceToEtabliss(@PathVariable("idAmbilancier")Integer idAmbilancier ) {
        return serviceAmbilancier.UnsignAmbulancierfromAmbulance(idAmbilancier);
    }

}
