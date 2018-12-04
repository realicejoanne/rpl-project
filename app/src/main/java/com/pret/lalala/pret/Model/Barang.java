package com.pret.lalala.pret.Model;

public class Barang {

    //TODO: Tambahin ID pengirim

    public String nama, deskripsi, alamat, userId;
    public int harga;

    public Barang() {

    }

    public Barang(String nama, String deskripsi, String alamat, int harga) {

        this.nama = nama;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
        this.harga = harga;

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
