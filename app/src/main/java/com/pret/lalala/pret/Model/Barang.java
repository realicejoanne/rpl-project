package com.pret.lalala.pret.Model;

public class Barang {

    //TODO: Tambahin ID pengirim

    public String nama, deskripsi, alamat, pemilikBarang, linkFoto;
    public int harga;

    public Barang() {

    }

    public Barang(String nama, String deskripsi, String alamat, int harga, String pemilikBarang,
                  String linkFoto) {

        this.nama = nama;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
        this.harga = harga;
        this.pemilikBarang = pemilikBarang;
        this.linkFoto = linkFoto;

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

    public String getPemilikBarang() {
        return pemilikBarang;
    }

    public void setPemilikBarang(String pemilikBarang) {
        this.pemilikBarang = pemilikBarang;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
