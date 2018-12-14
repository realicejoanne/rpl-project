package com.pret.lalala.pret;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pret.lalala.pret.Model.Barang;

import java.util.ArrayList;

public class BarangkuActivity extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;
    DatabaseReference ref;
    ImageView imageBarang;
    TextView namaBarang;
    TextView hargabarang;
    Barang barang;
    ArrayList<Barang> barangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barangku);

        listView = findViewById(R.id.listview);

        barangList = new ArrayList<Barang>();

        String currentUserEmail = getSharedPreferences("PREFERENCE_CURRENT_USER",
                MODE_PRIVATE).getString("currentUser", "lala");

        Query query = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://pret-app-35b58.firebaseio.com/barang")
                .orderByChild("pemilikBarang").equalTo(currentUserEmail);
        FirebaseListOptions<Barang> options = new FirebaseListOptions.Builder<Barang>()
                .setLayout(R.layout.list_barang).setQuery(query, Barang.class).build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View root, Object model, int position) {

                imageBarang = root.findViewById(R.id.image_barang);
                namaBarang = root.findViewById(R.id.nama_barang);
                hargabarang = root.findViewById(R.id.harga_barang);

                barang = (Barang) model;
//                Picasso.get().load(barang.getLinkFoto()).resize(50, 50).centerCrop().into(imageBarang);
                namaBarang.setText(barang.getNama());
                hargabarang.setText("Rp. " + Integer.toString(barang.getHarga()));

                Barang barangO = new Barang(barang.getNama(), barang.getDeskripsi(),
                        barang.getAlamat(), barang.getHarga(), barang.getPemilikBarang(),
                        barang.getLinkFoto());

//                barangList.add(barangO);

                if (position == 0) {
                    barangList.add(barangO);
                } else {
                    if (barangO.getNama() == "Sepatu") {
                        // ignore
                    } else {
                        barangList.add(barangO);
                    }
                }
            }
        };

        listView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
