package com.pret.lalala.pret;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewBarangActivity extends AppCompatActivity {

    TextView namaBarang;
    TextView deskripsiBarang;
    TextView alamatBarang;
    TextView hargaBarang;
    TextView pemilikBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_barang);

//        String value = getIntent().getExtras().getString(key);

        namaBarang = findViewById(R.id.nama_barang);
        deskripsiBarang = findViewById(R.id.deskripsi_barang);
        alamatBarang = findViewById(R.id.alamat_barang);
        hargaBarang = findViewById(R.id.harga_barang);
        pemilikBarang = findViewById(R.id.pemilik_barang);

        namaBarang.setText(getIntent().getExtras().getString("data1"));
        deskripsiBarang.setText(getIntent().getExtras().getString("data2"));
        alamatBarang.setText(getIntent().getExtras().getString("data3"));
        hargaBarang.setText("" + getIntent().getExtras().getInt("data4"));
        pemilikBarang.setText(getIntent().getExtras().getString("data5"));
    }
}
