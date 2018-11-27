package com.pret.lalala.pret;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pret.lalala.pret.Model.Barang;


public class FragmentAdd extends Fragment {

    TextInputLayout namaBarangInput;
    TextInputLayout deskripsiBarangInput;
    TextInputLayout alamatBarangInput;
    TextInputLayout hargaBarangInput;
    TextInputEditText namaBarang;
    TextInputEditText deskripsiBarang;
    TextInputEditText alamatBarang;
    TextInputEditText hargaBarang;
    MaterialButton pinjamkanButton;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add, null);

        namaBarangInput = rootView.findViewById(R.id.namaTextInputlayout);
        deskripsiBarangInput = rootView.findViewById(R.id.deskripsiTextInputlayout);
        alamatBarangInput = rootView.findViewById(R.id.alamatTextInputlayout);
        hargaBarangInput = rootView.findViewById(R.id.hargaTextInputlayout);

        namaBarang = rootView.findViewById(R.id.namaEditText);
        deskripsiBarang = rootView.findViewById(R.id.deskripsiEditText);
        alamatBarang = rootView.findViewById(R.id.alamatEditText);
        hargaBarang = rootView.findViewById(R.id.hargaEditText);
        pinjamkanButton = rootView.findViewById(R.id.pinjamkan_button);

        pinjamkanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimData();
            }
        });

        return rootView;
    }

    void kirimData() {

        if (TextUtils.isEmpty(namaBarang.getText())) {

            namaBarangInput.setError("Nama barang harus diisi");
            namaBarangInput.requestFocus();

        } else if (TextUtils.isEmpty(deskripsiBarang.getText())) {

            deskripsiBarangInput.setError("Deskripsi barang harus diisi");
            deskripsiBarangInput.requestFocus();

        } else if (TextUtils.isEmpty(alamatBarang.getText())) {

            alamatBarangInput.setError("Alamat barang harus diisi");
            alamatBarangInput.requestFocus();

        } else {

            mDatabase = FirebaseDatabase.getInstance().getReference();

            //Getting Values
            String nama = namaBarang.getText().toString();
            String deskripsi = deskripsiBarang.getText().toString();
            String alamat = alamatBarang.getText().toString();
            int harga = Integer.parseInt(hargaBarang.getText().toString());

//            int umurAngka = Integer.parseInt(umur.getText().toString());
//            String jkText = spinJK.getSelectedItem().toString();
//            String pendidikanText = spinPendidikan.getSelectedItem().toString();
//            String pekerjaanText = spinPekerjaan.getSelectedItem().toString();
//            String emailText = email.getText().toString();

            //Creating new barang node
            String barangId = mDatabase.push().getKey();

            //Creating barang Object
            Barang barang = new Barang(nama, deskripsi, alamat, harga);

            //Pushing user to 'barang node using barangID
            mDatabase.child("barang").child(barangId).setValue(barang);

//            Intent intent = new Intent(FormActivity.this, MainActivity.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
//            finish();

        }

    }
}
