package com.pret.lalala.pret;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ViewBarangActivity extends AppCompatActivity {

    TextView namaBarang;
    TextView deskripsiBarang;
    TextView alamatBarang;
    TextView hargaBarang;
    TextView pemilikBarang;
    static final int FORM_REQUEST_CODE = 0;
    MaterialButton pinjamButton;
    View mView;

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
        pinjamButton = findViewById(R.id.pinjam_button);

        namaBarang.setText(getIntent().getExtras().getString("data1"));
        deskripsiBarang.setText(getIntent().getExtras().getString("data2"));
        alamatBarang.setText(getIntent().getExtras().getString("data3"));
        hargaBarang.setText("Rp. " + getIntent().getExtras().getInt("data4"));
        pemilikBarang.setText(getIntent().getExtras().getString("data5"));

        pinjamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView = v;
                Intent intent = new Intent(ViewBarangActivity.this,
                        FormMinjemActivity.class);
                intent.putExtra("namaBarang",
                        getIntent().getExtras().getString("data6"));
                intent.putExtra("idBarang",
                        getIntent().getExtras().getString("data7"));
//                startActivity(intent);
                startActivityForResult(intent, FORM_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case FORM_REQUEST_CODE:
                if (resultCode == 1) {
                    Snackbar.make(mView, "Request peminjaman telah dikirimkan", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
        }
    }
}
