package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
