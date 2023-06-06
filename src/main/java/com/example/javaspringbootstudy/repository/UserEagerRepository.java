package com.example.javaspringbootstudy.repository;

import com.example.javaspringbootstudy.entity.UserEager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserEagerRepository extends JpaRepository<UserEager, Long> {

    @EntityGraph(value = "UserEager.withOrder")
    @Query(value = " select a" +
            " from UserEager a ")
    List<UserEager> findAll();
}
