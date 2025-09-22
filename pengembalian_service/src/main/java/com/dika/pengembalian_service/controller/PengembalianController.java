package com.dika.pengembalian_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dika.pengembalian_service.model.Pengembalian;
import com.dika.pengembalian_service.service.PengembalianService;
import com.dika.pengembalian_service.vo.ResponseTemplate;

@RestController
@RequestMapping("/api/pengembalian")
public class PengembalianController {

    @Autowired
    private PengembalianService pengembalianService;

    // ✅ Ambil semua pengembalian (tanpa detail anggota, buku & peminjaman)
    @GetMapping
    public List<Pengembalian> getAllPengembalian() {
        return pengembalianService.getAllPengembalian();
    }

    // ✅ Ambil 1 pengembalian lengkap dengan detail anggota, buku & peminjaman
    // URL: http://localhost:8083/api/pengembalian/1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplate> getPengembalianWithDetailsById(@PathVariable Long id) {
        ResponseTemplate response = pengembalianService.getPengembalianWithDetailsById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Buat pengembalian baru
    @PostMapping
    public Pengembalian createPengembalian(@RequestBody Pengembalian pengembalian) {
        return pengembalianService.createPengembalian(pengembalian);
    }

    // ✅ Hapus pengembalian
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePengembalian(@PathVariable Long id) {
        pengembalianService.deletePengembalian(id);
        return ResponseEntity.ok().build();
    }
}
