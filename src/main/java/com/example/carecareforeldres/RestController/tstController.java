package com.example.carecareforeldres.RestController;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/tst")
@CrossOrigin("*")
public class tstController {

    @GetMapping("/tr")
    public ResponseEntity<?> getCUisinier(){

        throw new RuntimeException("Vous êtes déjà inscrit à cet événement");
    }
}
