package com.dika.pengembalian_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dika.pengembalian_service.model.Pengembalian;

public interface PengembalianRepository extends JpaRepository<Pengembalian, Long> {
}
