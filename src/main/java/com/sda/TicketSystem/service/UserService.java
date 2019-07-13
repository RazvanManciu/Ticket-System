package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.User;
import com.sda.TicketSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public User getById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
