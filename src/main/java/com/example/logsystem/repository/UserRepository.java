package com.example.logsystem.repository;

import com.example.logsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Extend the JPA repository
//perform crud operation in the data
//fetch data from the repository

public interface UserRepository extends JpaRepository<User,Long>{



}
