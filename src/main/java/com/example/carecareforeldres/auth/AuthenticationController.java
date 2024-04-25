package com.example.carecareforeldres.auth;


import com.example.carecareforeldres.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {


  private final AuthenticationService service;


  @PostMapping("/register")
  public ResponseEntity<?> register(
          @RequestBody User request
  ) {
    var response = service.register(request);
    if (request.isMfaEnabled()) {
      return ResponseEntity.ok(response);
    }
    return ResponseEntity.accepted().build();
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/verify")
  public ResponseEntity<?> verifyCode(
          @RequestBody VerificationRequest verificationRequest
  ) {
    return ResponseEntity.ok(service.verifyCode(verificationRequest));
  }
  @GetMapping("/users/connected/{role}")
  public List<User> getConnectedUsersWithRole(@PathVariable String role) {
    return service.getConnectedUsersWithRole(role);
  }

  @GetMapping("/current/user/{id}/{role}")
  public Optional<?> getCurrentUsersWithRole(@PathVariable Integer id,@PathVariable String role) {
    return service.getCurrentUsersWithRole(id,role);
  }
  @GetMapping("/allUser")
  public List<User> finduserback () {
    List<User> uu = service.findUserback();
    return uu;
  }
  @PutMapping(path = "/update")
  public String update(@RequestBody String userr) throws IOException {
    return service.update(userr);
  }




}
