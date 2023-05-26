package com.example.demo.repository;

import com.example.demo.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<Date, Long> {
}
