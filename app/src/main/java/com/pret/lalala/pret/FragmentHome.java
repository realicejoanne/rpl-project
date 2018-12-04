package com.pret.lalala.pret;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pret.lalala.pret.Model.Barang;

public class FragmentHome extends Fragment {

    ListView listView;
    FirebaseListAdapter adapter;
    TextView namaBarang;
    TextView deskripsiBarang;
    TextView alamatBarang;
    TextView hargabarang;
    Barang barang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, null);
        listView = root.findViewById(R.id.list_view);

        Query query = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pret-app-35b58.firebaseio.com/barang");
        FirebaseListOptions<Barang> options = new FirebaseListOptions.Builder<Barang>()
                .setLayout(R.layout.list_barang)
                .setQuery(query, Barang.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View root, Object model, int position) {
                namaBarang = root.findViewById(R.id.nama_barang);
                deskripsiBarang = root.findViewById(R.id.deskripsi_barang);
                alamatBarang = root.findViewById(R.id.alamat_barang);
                hargabarang = root.findViewById(R.id.harga_barang);

                barang = (Barang) model;
                namaBarang.setText(barang.getNama());
                deskripsiBarang.setText(barang.getDeskripsi());
                alamatBarang.setText(barang.getAlamat());
                hargabarang.setText(Integer.toString(barang.getHarga()));
            }
        };

        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {

                        Intent intent = new Intent(getActivity(), ViewBarangActivity.class);
                        intent.putExtra("NAMABARANG", barang.getNama());
                        intent.putExtra("DESKRIPSIBARANG", barang.getDeskripsi());
                        intent.putExtra("ALAMATBARANG", barang.getAlamat());
                        intent.putExtra("HARGABARANG", Integer.toString(barang.getHarga()));

                        startActivity(intent);
                    }
                });

        return root;
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
