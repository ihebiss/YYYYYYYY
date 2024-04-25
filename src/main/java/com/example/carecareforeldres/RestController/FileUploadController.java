package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Service.FileUploadBrahmi;
import lombok.RequiredArgsConstructor;
import org.cloudinary.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cloud")
@CrossOrigin("*")
public class FileUploadController {

    private final FileUploadBrahmi fileUploadBrahmi;



  /*  @PostMapping("/upload")
    public String uploadFile(@RequestParam("image")MultipartFile multipartFile,
                             Model model) throws IOException {
        String imageURL = fileUploadBrahmi.uploadFile(multipartFile);
        model.addAttribute("imageURL",imageURL);
        return imageURL;
    }*/
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile multipartFile) {
        try {
            String imageURL = fileUploadBrahmi.uploadFile(multipartFile);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("imageURL", imageURL);
            return ResponseEntity.ok(jsonResponse.toString());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Une erreur s'est produite lors du téléchargement de l'image.");
        }
}}
