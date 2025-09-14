package com.dika.buku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dika.buku.model.Buku;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Long> {

}