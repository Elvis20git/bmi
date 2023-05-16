package com.Health.bmi.repository;

import com.Health.bmi.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
    Optional<Data> findById(Integer id);
    Optional<Data> findFirstById(Integer id);

    @Query("SELECT p FROM Data p WHERE CONCAT(p.id,'',p.user_id,'',p.fname,'',p.lname,'',p.gender,'',p.email,'',p.height,'',p.weight) LIKE %?1% ")
    List<Data>search(String keyword);
}
