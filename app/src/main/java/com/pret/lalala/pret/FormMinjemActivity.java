package com.pret.lalala.pret;

import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pret.lalala.pret.Model.Peminjaman;

public class FormMinjemActivity extends AppCompatActivity {

    EditText lamaPinjam;
    EditText hargaPinjam;
    TextView namaBarang;
    int lamaPinjamInt;
    int hargaPinjamInt;
    MaterialButton pinjamButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_minjem);

        lamaPinjam = findViewById(R.id.editTextLama);
        hargaPinjam = findViewById(R.id.editText2);
        namaBarang = findViewById(R.id.namaBarang);
        namaBarang.setText(getIntent().getExtras().getString("data6"));

        pinjamButton = findViewById(R.id.button);

        pinjamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lamaPinjamInt = Integer.parseInt(lamaPinjam.getText().toString());
                hargaPinjamInt = Integer.parseInt(hargaPinjam.getText().toString());

                mDatabase = FirebaseDatabase.getInstance().getReference();
                String idPeminjaman = mDatabase.push().getKey();
                String idPeminjam = getSharedPreferences("PREFERENCE_CURRENT_USER_ID", MODE_PRIVATE)
                        .getString("currentUserId", "lala");

                Peminjaman peminjaman = new Peminjaman(
                        idPeminjaman,
                        getIntent().getExtras().getString("data6"),
                        lamaPinjamInt,
                        hargaPinjamInt,
                        idPeminjam,
                        false
                );

                mDatabase.child("peminjaman").child(idPeminjaman).setValue(peminjaman);
                setResult(1);
                finish();
            }
        });
    }
}
