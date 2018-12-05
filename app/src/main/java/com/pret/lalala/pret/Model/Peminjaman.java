package com.pret.lalala.pret.Model;

public class Peminjaman {

    public String idPeminjaman, idBarang, idPeminjam;
    public int lamaPinjam, hargaBarang;

    public Peminjaman() {

    }

    public Peminjaman(String idPeminjaman, String idBarang, int lamaPinjam, int hargaPinjam,
                      String idPeminjam) {
        this.idPeminjaman = idPeminjaman;
        this.idBarang = idBarang;
        this.lamaPinjam = lamaPinjam;
        this.hargaBarang = hargaPinjam;
        this.idPeminjam = idPeminjam;
    }
}
