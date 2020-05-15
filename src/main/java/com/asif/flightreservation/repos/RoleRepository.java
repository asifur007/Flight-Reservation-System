package com.asif.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asif.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
