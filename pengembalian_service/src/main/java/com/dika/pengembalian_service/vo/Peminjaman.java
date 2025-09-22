package com.dika.pengembalian_service.vo;

public class Peminjaman {
    private Long id;
    private Long anggotaId;
    private Long bukuId;
    private String tanggal_pinjam;

    public Peminjaman(Long id, Long anggotaId, Long bukuId, String tanggal_pinjam) {
        this.id = id;
        this.anggotaId = anggotaId;
        this.bukuId = bukuId;
        this.tanggal_pinjam = tanggal_pinjam;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnggotaId(Long anggotaId) {
        this.anggotaId = anggotaId;
    }

    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }

    public void setTanggal_pinjam(String tanggal_pinjam) {
        this.tanggal_pinjam = tanggal_pinjam;
    }

    public Long getId() {
        return id;
    }

    public Long getAnggotaId() {
        return anggotaId;
    }

    public Long getBukuId() {
        return bukuId;
    }

    public String getTanggal_pinjam() {
        return tanggal_pinjam;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
