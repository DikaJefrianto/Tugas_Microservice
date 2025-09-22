package com.dika.peminjaman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "peminjamans")
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long anggotaId;
    private Long bukuId;

    // @Column(name = "tanggal_pinjam")
    private String tanggal_pinjam;
    
}
