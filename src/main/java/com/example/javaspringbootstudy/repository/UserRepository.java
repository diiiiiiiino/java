package com.example.javaspringbootstudy.repository;

import com.example.javaspringbootstudy.entity.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Userr, Long> {
    @Query(value = " select a " +
            " from Userr a")
    List<Userr> findAllUser();

    @Query(value = " select a " +
            " from Userr a" +
            " join fetch a.orderrs ")
    List<Userr> findAllUserJoin();
}
