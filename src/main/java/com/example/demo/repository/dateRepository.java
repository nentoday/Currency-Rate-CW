package com.example.demo.repository;

import com.example.demo.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface dateRepository extends JpaRepository<Date, Long> {
}
