package com.dika.pengembalian_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dika.pengembalian_service.model.Pengembalian;
import com.dika.pengembalian_service.repository.PengembalianRepository;
import com.dika.pengembalian_service.vo.Anggota;
import com.dika.pengembalian_service.vo.Buku;
import com.dika.pengembalian_service.vo.Peminjaman;
import com.dika.pengembalian_service.vo.ResponseTemplate;

@Service
public class PengembalianService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private PengembalianRepository pengembalianRepository;

    @Autowired
    private RestTemplate restTemplate;

    // ✅ Simpan pengembalian
    public Pengembalian createPengembalian(Pengembalian pengembalian) {
        return pengembalianRepository.save(pengembalian);
    }

    // ✅ Ambil semua pengembalian tanpa detail
    public List<Pengembalian> getAllPengembalian() {
        return pengembalianRepository.findAll();
    }

    // ✅ Ambil 1 pengembalian by ID
    public Pengembalian getPengembalianById(Long id) {
        return pengembalianRepository.findById(id).orElse(null);
    }

    // ✅ Ambil 1 pengembalian lengkap dengan peminjaman, anggota, dan buku
    public ResponseTemplate getPengembalianWithDetailsById(Long id) {
        Pengembalian pengembalian = getPengembalianById(id);
        if (pengembalian == null) {
            return null;
        }

        // --- Ambil data Peminjaman ---
        List<ServiceInstance> peminjamanInstances = discoveryClient.getInstances("PEMINJAMAN");
        if (peminjamanInstances.isEmpty()) {
            throw new IllegalStateException("Service PEMINJAMAN tidak tersedia");
        }
        Peminjaman peminjaman = restTemplate.getForObject(
                peminjamanInstances.get(0).getUri() + "/api/peminjaman/" + pengembalian.getPeminjamanId(),
                Peminjaman.class);

        // --- Ambil data Anggota ---
        Anggota anggota = null;
        List<ServiceInstance> anggotaInstances = discoveryClient.getInstances("ANGGOTA");
        if (!anggotaInstances.isEmpty() && peminjaman != null && peminjaman.getAnggotaId() != null) {
            anggota = restTemplate.getForObject(
                    anggotaInstances.get(0).getUri() + "/api/anggota/" + peminjaman.getAnggotaId(),
                    Anggota.class);
        }

        // --- Ambil data Buku ---
        Buku buku = null;
        List<ServiceInstance> bukuInstances = discoveryClient.getInstances("BUKU");
        if (!bukuInstances.isEmpty() && peminjaman != null && peminjaman.getBukuId() != null) {
            buku = restTemplate.getForObject(
                    bukuInstances.get(0).getUri() + "/api/buku/" + peminjaman.getBukuId(),
                    Buku.class);
        }

        // --- Bungkus ke ResponseTemplate ---
        return new ResponseTemplate(peminjaman, anggota, buku, pengembalian);
    }

    // ✅ Ambil semua pengembalian lengkap dengan detail
    public List<ResponseTemplate> getAllPengembalianWithDetails() {
        List<ResponseTemplate> responseList = new ArrayList<>();
        List<Pengembalian> pengembalianList = pengembalianRepository.findAll();

        for (Pengembalian pengembalian : pengembalianList) {
            ResponseTemplate response = getPengembalianWithDetailsById(pengembalian.getId());
            if (response != null) {
                responseList.add(response);
            }
        }

        return responseList;
    }

    // ✅ Hapus pengembalian
    public void deletePengembalian(Long id) {
        pengembalianRepository.deleteById(id);
    }
}
