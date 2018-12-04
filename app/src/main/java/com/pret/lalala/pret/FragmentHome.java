package com.pret.lalala.pret;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.util.ArrayList;

public class FragmentHome extends Fragment {

    ListView listView;
    FirebaseListAdapter adapter;
    TextView namaBarang;
    TextView deskripsiBarang;
    TextView alamatBarang;
    TextView hargabarang;
    Barang barang;
    ArrayList<Barang> barangList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, null);
        listView = root.findViewById(R.id.list_view);

        barangList = new ArrayList<Barang>();

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

                Barang barangO = new Barang(barang.getNama(), barang.getDeskripsi(),
                        barang.getAlamat(), barang.getHarga(), barang.getPemilikBarang());

                barangList.add(barangO);
            }
        };

        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {

                        Intent intent = new Intent(getActivity(), ViewBarangActivity.class);
                        intent.putExtra("data1", barangList.get(position).getNama());// add the selected String from the ListView
                        intent.putExtra("data2", barangList.get(position).getDeskripsi());// add the selected String from the ListView
                        intent.putExtra("data3", barangList.get(position).getAlamat());// add the selected String from the ListView
                        intent.putExtra("data4", barangList.get(position).getHarga());// add the selected String from the ListView
                        intent.putExtra("data5", barangList.get(position).getPemilikBarang());// add the selected String from the ListView
                        Log.d("ArrayList", "" + barangList.get(position).getNama() + ", " + position);

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
