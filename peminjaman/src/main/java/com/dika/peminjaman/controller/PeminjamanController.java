package com.dika.peminjaman.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dika.peminjaman.model.Peminjaman;
import com.dika.peminjaman.service.PeminjamanService;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService;

    // Ambil semua data peminjaman
    @GetMapping
    public List<Peminjaman> getAllPeminjamans() {
        return peminjamanService.getAllPeminjamans();
    }

    // Ambil peminjaman berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Peminjaman> getPeminjamanById(@PathVariable Long id) {
        Optional<Peminjaman> peminjaman = peminjamanService.getPeminjamanById(id);
        return peminjaman.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    // Simpan peminjaman baru
    @PostMapping
    public Peminjaman createPeminjaman(@RequestBody Peminjaman peminjaman) {
        return peminjamanService.createPeminjaman(peminjaman);
    }

    // Hapus peminjaman
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeminjaman(@PathVariable Long id) {
        peminjamanService.deletePeminjaman(id);
        return ResponseEntity.ok().build();
    }
}
