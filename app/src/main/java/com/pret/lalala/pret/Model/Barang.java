package com.pret.lalala.pret.Model;

public class Barang {

    //TODO: Tambahin ID pengirim

<<<<<<< HEAD
    public String nama, deskripsi, alamat, userId, link;
=======
    public String nama, deskripsi, alamat, pemilikBarang;
>>>>>>> b8adea7748f0e752e7400fddb2c21803063bb32d
    public int harga;

    public Barang() {

    }

<<<<<<< HEAD
    public Barang(String nama, String deskripsi, String alamat, int harga, String link) {
=======
    public Barang(String nama, String deskripsi, String alamat, int harga, String pemilikBarang) {
>>>>>>> b8adea7748f0e752e7400fddb2c21803063bb32d

        this.nama = nama;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
<<<<<<< HEAD
        this.harga = harga;
        this.link = link;
=======
        this.pemilikBarang = pemilikBarang;
>>>>>>> b8adea7748f0e752e7400fddb2c21803063bb32d

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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
