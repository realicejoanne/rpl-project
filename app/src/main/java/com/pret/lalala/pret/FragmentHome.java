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

public class FragmentHome extends Fragment {

    ListView listView;
    FirebaseListAdapter adapter;
    DatabaseReference ref;
    ImageView imageBarang;
    TextView namaBarang;
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
                .setLayout(R.layout.list_barang).setQuery(query, Barang.class).build();

//        Log.d("currentUserUname", currentUserUname);

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


//                if (position == 0) {
//                    barangList.add(barangO);
//                } else {
//                    Log.d("ArrayLists", barangO.getNama());
//                    Log.d("ArrayListss", barangList.get(position-1).getNama());
//                    if (barangO.getNama() == barangList.get(position-1).getNama()) {
//                        Log.d("Arraylist", "barang sudah ada");
//                    } else {
//                        barangList.add(barangO);
//                    }
//                }


            }
        };

        listView.setAdapter(adapter);

//        listView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);

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
