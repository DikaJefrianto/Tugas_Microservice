package com.dika.peminjaman.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dika.peminjaman.model.Peminjaman;
import com.dika.peminjaman.repository.PeminjamanRepository;

@Service
public class PeminjamanService {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    // Simpan data peminjaman baru
    public Peminjaman createPeminjaman(Peminjaman peminjaman) {
        return peminjamanRepository.save(peminjaman);
    }

    // Ambil semua data peminjaman (tanpa detail anggota & buku)
    public List<Peminjaman> getAllPeminjamans() {
        return peminjamanRepository.findAll();
    }

    // Ambil peminjaman berdasarkan id
    public Optional<Peminjaman> getPeminjamanById(Long id) {
        return peminjamanRepository.findById(id);
    }

    // Hapus peminjaman
    public void deletePeminjaman(Long id) {
        peminjamanRepository.deleteById(id);
    }
}
