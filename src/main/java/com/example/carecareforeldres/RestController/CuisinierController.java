package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Cuisinier;
import com.example.carecareforeldres.Entity.TypeBadge;
import com.example.carecareforeldres.Repository.CuisinierRepository;
import com.example.carecareforeldres.Service.CuisinierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/cuisinier")
@CrossOrigin("*")
public class CuisinierController {

    CuisinierService cuisinierService;
    CuisinierRepository cuisinierRepository;

    @GetMapping("/chef-stats")
    public Map<String, Integer> getChefStats() {
        return cuisinierService.getChefStats();
    }




    @PostMapping("/add")
    public Cuisinier ajouterCuisinier(@RequestBody Cuisinier res){
        Cuisinier p1=cuisinierService.add(res);
        return p1;
    }

    @GetMapping("/pie")
        public static Map<TypeBadge, Double> calculateBadgePercentage(List<Cuisinier> cuisiniers) {
            Map<TypeBadge, Integer> badgeCounts = new HashMap<>();
            int totalCuisiniers = cuisiniers.size();

            // Calculer le nombre de cuisiniers pour chaque type de badge
            for (Cuisinier cuisinier : cuisiniers) {
                TypeBadge badge = cuisinier.getTypeBadge();
                badgeCounts.put(badge, badgeCounts.getOrDefault(badge, 0) + 1);
            }

            Map<TypeBadge, Double> badgePercentage = new HashMap<>();
            for (TypeBadge badge : badgeCounts.keySet()) {
                int count = badgeCounts.get(badge);
                double percentage = (double) count / totalCuisiniers * 100;
                badgePercentage.put(badge, percentage);
            }

            return badgePercentage;
        }

    @GetMapping("/retrive_all_cuisinier")
    public List<Cuisinier> retrieveCuisinierList(){

        return cuisinierService.getAll();
    }

    @GetMapping("/retrive_cuisinier/{resId}")
    public Cuisinier retrieveCuisinier(@PathVariable("resId") Integer resId){

        return cuisinierRepository.findById(resId).get();
    }

    @PutMapping("/update_cuisinier")
    public Cuisinier updateCuisinier(@RequestBody Cuisinier restaurant){

        return cuisinierService.update(restaurant);
    }

    @DeleteMapping("/delete_cuisinier/{cuisinierId}")
    public void deleteCuisinier(@PathVariable("cuisinierId") Integer cuisinierId){
        cuisinierService.remove(cuisinierId);
    }

}
