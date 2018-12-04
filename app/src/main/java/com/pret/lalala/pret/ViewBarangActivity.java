package com.pret.lalala.pret;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewBarangActivity extends AppCompatActivity {

    TextView namaBarang;
    TextView deskripsiBarang;
    TextView alamatBarang;
    TextView hargabarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_barang);

//        String value = getIntent().getExtras().getString(key);

        namaBarang = findViewById(R.id.nama_barang);
        deskripsiBarang = findViewById(R.id.deskripsi_barang);
        alamatBarang = findViewById(R.id.alamat_barang);
        hargabarang = findViewById(R.id.harga_barang);

        namaBarang.setText(getIntent().getExtras().getString("NAMABARANG"));
        deskripsiBarang.setText(getIntent().getExtras().getString("DESKRIPSIBARANG"));
        alamatBarang.setText(getIntent().getExtras().getString("ALAMATBARANG"));
        hargabarang.setText(getIntent().getExtras().getString("HARGABARANG"));
    }
}
