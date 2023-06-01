package com.example.javaspringbootstudy.repository;

import com.example.javaspringbootstudy.entity.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orderr, Long> {

}
