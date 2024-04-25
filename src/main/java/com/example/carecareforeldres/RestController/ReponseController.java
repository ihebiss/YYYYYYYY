package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Reponse;
import com.example.carecareforeldres.Service.IReponseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/reponses")

public class ReponseController {
         IReponseService reponseService;

        @GetMapping
        public List<Reponse> getAllReponses() {
            List<Reponse> reponses = reponseService.getAllReponses();
            return  reponses;
        }


        @GetMapping("/{id}")
        public Reponse getReponseById(@PathVariable("id") Long id) {
            Reponse reponses = reponseService.getReponseById(id);
           return reponses ;
        }



        @PostMapping("/add-reponse")
        public Reponse createReponse(@RequestBody Reponse reponse) {

            return reponseService.saveReponse(reponse);
        }


    @PutMapping("/{id}")
        public Reponse updateReponse(@RequestBody Reponse reponse) {
            return reponseService.updateReponse(reponse);

            }


        @DeleteMapping("/{id}")
        public void  deleteReponse(@PathVariable("id") Long id) {
            reponseService.deleteReponse(id);

        }
}

