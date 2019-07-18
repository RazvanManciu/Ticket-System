package com.sda.TicketSystem.repository;

import com.sda.TicketSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
