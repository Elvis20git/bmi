package com.Health.bmi.repository;

//import com.Employee3.model.Employee;
import com.Health.bmi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmailAndPassword(String email, String password );
    Optional<User> findFirstByEmail(String email);
}
