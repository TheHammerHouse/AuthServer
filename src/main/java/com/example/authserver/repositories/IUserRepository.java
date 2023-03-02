package com.example.authserver.repositories;

import com.example.authserver.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
}
