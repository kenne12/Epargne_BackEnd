package com.projet.epargne.dao;

import com.projet.epargne.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select max (u.id) from User u")
    public Integer nextValue();
}
