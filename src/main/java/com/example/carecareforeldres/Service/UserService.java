package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.User;
import com.example.carecareforeldres.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService{
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getStaticUser() {
        Optional<User> optionalUser = userRepository.findById(1); // Assuming the ID is of type Long
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            // Handle the case where the user with ID 1 is not found
            throw new EntityNotFoundException("Static user with ID 1 not found!");
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}